package com.broughty.ttlptwit.repository;

import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY;
import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY_TWEET;

import com.broughty.ttlptwit.aggregation.ListeningPartyTweetDto;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweetRecord;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class ListeningPartyRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListeningPartyRepository.class);

  private final DSLContext dsl;

  public ListeningPartyRepository(final DSLContext dsl) {
    this.dsl = dsl;
  }

  public void batchUpdate(@NonNull final List<? extends ListeningPartyRecord> list) {
    LOGGER.info("Writing {} Listening Party records", list.size());
    dsl.batchStore(list).execute();
  }

  public void batchUpdateTweets(@NonNull final List<? extends ListeningPartyTweetRecord> tweetRecords) {
    LOGGER.info("Writing {} Listening Party Tweet records", tweetRecords.size());
    dsl.batchStore(tweetRecords).execute();
  }

  public List<ListeningPartyRecord> getListeningPartyList(final int start, final int pageSize) {
    LOGGER.debug("Reading from start=[{}] page size=[{}]", start, pageSize);
    return dsl
        .selectFrom(LISTENING_PARTY)
        .orderBy(LISTENING_PARTY.TTLP_NO)
        .seek(start)
        .limit(pageSize)
        .fetch();
  }

  public List<ListeningPartyRecord> getListeningPartyUpdateList(final int start, final int pageSize) {
    LOGGER.debug("Reading from start=[{}] page size=[{}]", start, pageSize);
    return dsl
        .selectFrom(LISTENING_PARTY)
        .where(LISTENING_PARTY.REQUIRES_UPDATE.isTrue())
        .and(LISTENING_PARTY.COLLECTION_LINK.contains("timelines"))
        .orderBy(LISTENING_PARTY.TTLP_NO)
        .seek(start)
        .limit(pageSize)
        .fetch();
  }

  public ListeningPartyRecord selectByTtlpId(final Integer ttlpId) {
    return dsl.selectFrom(LISTENING_PARTY)
              .where(LISTENING_PARTY.TTLP_NO.eq(ttlpId))
              .fetchAny();
  }

  public List<ListeningPartyTweetDto> getListeningPartyTweets(final Integer ttlpId) {
    return
        dsl.select(LISTENING_PARTY_TWEET.LISTENING_PARTY_ID,
                   LISTENING_PARTY.ARTIST,
                   LISTENING_PARTY.ALBUM,
                   LISTENING_PARTY_TWEET.TWEET_ID,
                   LISTENING_PARTY_TWEET.TEXT,
                   LISTENING_PARTY_TWEET.AUTHOR,
                   LISTENING_PARTY_TWEET.GEO,
                   LISTENING_PARTY_TWEET.LIKES,
                   LISTENING_PARTY_TWEET.RETWEET,
                   LISTENING_PARTY_TWEET.QUOTE,
                   LISTENING_PARTY_TWEET.REPLY,
                   LISTENING_PARTY_TWEET.IN_REPLY_TO_USERID,
                   LISTENING_PARTY_TWEET.CREATED_AT)
           .from(LISTENING_PARTY_TWEET)
           .innerJoin(LISTENING_PARTY)
           .on(LISTENING_PARTY.TTLP_NO.eq(LISTENING_PARTY_TWEET.LISTENING_PARTY_ID))
           .where(LISTENING_PARTY.TTLP_NO.eq(ttlpId)).fetch().into(ListeningPartyTweetDto.class);
  }


  public int update(final ListeningPartyRecord listeningPartyRecord) {
    return dsl.update(LISTENING_PARTY).set(listeningPartyRecord)
              .where(LISTENING_PARTY.TTLP_NO.eq(listeningPartyRecord.getTtlpNo()))
              .execute();
  }


  public Map<ListeningPartyRecord, List<ListeningPartyTweetDto>> getListeningPartiesTweets(final List<ListeningPartyRecord> listeningParties) {

    List<Integer> partyIds = listeningParties.stream().map(ListeningPartyRecord::getTtlpNo).toList();

    List<ListeningPartyTweetDto> tweetDtoList = dsl.select(LISTENING_PARTY_TWEET.LISTENING_PARTY_ID,
                                                           LISTENING_PARTY.ARTIST,
                                                           LISTENING_PARTY.ALBUM,
                                                           LISTENING_PARTY_TWEET.TWEET_ID,
                                                           LISTENING_PARTY_TWEET.TEXT,
                                                           LISTENING_PARTY_TWEET.AUTHOR,
                                                           LISTENING_PARTY_TWEET.GEO,
                                                           LISTENING_PARTY_TWEET.LIKES,
                                                           LISTENING_PARTY_TWEET.RETWEET,
                                                           LISTENING_PARTY_TWEET.QUOTE,
                                                           LISTENING_PARTY_TWEET.REPLY,
                                                           LISTENING_PARTY_TWEET.IN_REPLY_TO_USERID,
                                                           LISTENING_PARTY_TWEET.CREATED_AT
                                                   )
                                                   .from(LISTENING_PARTY_TWEET)
                                                   .innerJoin(LISTENING_PARTY)
                                                   .on(LISTENING_PARTY.TTLP_NO.eq(LISTENING_PARTY_TWEET.LISTENING_PARTY_ID))
                                                   .where(LISTENING_PARTY.TTLP_NO.in(partyIds)).fetch().into(ListeningPartyTweetDto.class);

    return tweetDtoList.stream()
                       .collect(Collectors.groupingBy(ListeningPartyTweetDto::ttlpNo)).entrySet().stream()
                       .collect(
                           Collectors.toMap(entry -> listeningParties.stream()
                                                                     .filter(lp -> lp.getTtlpNo().equals(entry.getKey()))
                                                                     .findFirst()
                                                                     .orElse(null),
                                            Entry::getValue));


  }
}
