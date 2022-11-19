package com.broughty.ttlptwit.batch;

import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY_TWEET;
import static org.jooq.tools.StringUtils.isBlank;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweetRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.collections.CollectionsResponse;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import liquibase.repackaged.org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ListeningPartyTweetWriter implements ItemWriter<ListeningPartyRecord> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListeningPartyTweetWriter.class);

  private final ListeningPartyRepository repository;
  private final TwitterClient            twitterClient;

  public ListeningPartyTweetWriter(final ListeningPartyRepository repository, final TwitterClient twitterClient) {
    this.repository    = repository;
    this.twitterClient = twitterClient;
  }

  @Override
  public void write(final List<? extends ListeningPartyRecord> list) {
    list.forEach(lp -> {
      lp.setRequiresUpdate(false);
      String collectionLink = ListeningParty.twitterCollection(lp.getCollectionLink());
      if (!isBlank(collectionLink)) {
        LOGGER.info("Request to write to tweet table for ttlp id = [{}] with collection id [{}]", lp.getTtlpNo(), collectionLink);
        List<String> tweetIds  = getTweetIdsFromCollection(collectionLink);
        TweetList    tweetList = buildTweetList(tweetIds);
        if (tweetList.getData() != null) {
          List<ListeningPartyTweetRecord> tweetRecords = convert(tweetList, lp.getTtlpNo());
          repository.batchUpdateTweets(tweetRecords);
        } else {
          LOGGER.warn("Collection-id = [{}] for ttlp id [{}] is empty", collectionLink, lp.getTtlpNo());
        }
      } else {
        LOGGER.warn("Issue retrieving collection link for ttlp id = [{}]", lp.getTtlpNo());
        lp.setRequiresUpdate(true);
      }
    });

    LOGGER.info("Updating list of parties to indicate that the tweet write was a success");

    // finally update them all to indicate update not required
    repository.batchUpdate(list);
  }


  private TweetList buildTweetList(List<String> tweetIds) {
    LOGGER.info("Build Tweet List from {} tweets", tweetIds.size());
    if (tweetIds.isEmpty()) {
      LOGGER.warn("empty tweet list passed");
      return new TweetList();
    }
    TweetList          tweetList           = null;
    List<List<String>> partitionedTweetIds = ListUtils.partition(tweetIds, 100);
    for (List<String> partitionedTweetId : partitionedTweetIds) {
      if (tweetList == null) {
        tweetList = twitterClient.getTweets(partitionedTweetId);
      } else {
        TweetList newTweetList = twitterClient.getTweets(partitionedTweetId);
        tweetList.getData().addAll(newTweetList.getData());
      }
    }
    return tweetList;
  }

  private List<String> getTweetIdsFromCollection(final String collectionLink) {
    CollectionsResponse collectionsResponse =
        twitterClient.collectionsEntries(collectionLink, 500, null, null);
    return collectionsResponse.getResponse().getTimeline()
                              .stream()
                              .map(timelineTweet -> timelineTweet.getTweet().getId())
                              .filter(str -> !isBlank(str))
                              .toList();
  }

  private List<ListeningPartyTweetRecord> convert(final TweetList tweetList, final Integer ttlpNo) {
    return tweetList.getData().stream()
                    .map(tweetData -> new ListeningPartyTweetRecord(null, tweetData.getId(), ttlpNo, tweetData.getText(), tweetData.getAuthorId(),
                                                                    tweetData.getInReplyToUserId(), tweetData.getCreatedAt(),
                                                                    tweetData.getPublicMetrics().getLikeCount(),
                                                                    tweetData.getPublicMetrics().getQuoteCount(),
                                                                    tweetData.getPublicMetrics().getReplyCount(),
                                                                    tweetData.getPublicMetrics().getRetweetCount(),
                                                                    tweetData.getGeo() != null ? tweetData.getGeo().getPlaceId() : null,
                                                                    tweetData.getAttachments() != null && tweetData.getAttachments()
                                                                                                                   .getMediaKeys() != null
                                                                    ? Arrays.stream(tweetData.getAttachments()
                                                                                             .getMediaKeys())
                                                                            .toList()
                                                                            .toString()
                                                                    : null, true, LocalDateTime.now()
                    ))
                    .peek(lpr -> lpr.changed(LISTENING_PARTY_TWEET.ID, false))
                    .collect(Collectors.toList());
  }
}
