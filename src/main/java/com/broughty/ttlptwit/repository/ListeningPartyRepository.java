package com.broughty.ttlptwit.repository;

import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY;
import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY_TWEET;
import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY_USER;
import static com.broughty.ttlptwit.database.jooq.data.Tables.MISSING_USER;

import com.broughty.ttlptwit.aggregation.ListeningPartyTweetDto;
import com.broughty.ttlptwit.aggregation.ListeningPartyUserDto;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweetRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweeterRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyUserRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.MissingUserRecord;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

  public void batchUpdateUsers(@NonNull final List<? extends ListeningPartyUserRecord> userRecords) {
    LOGGER.info("Writing {} Listening Party User records", userRecords.size());
    dsl.batchStore(userRecords).execute();
  }

  public void batchUpdateMissingUsers(final List<? extends MissingUserRecord> missingUserRecords) {
    LOGGER.info("Writing {} Missing User records", missingUserRecords.size());
    dsl.batchStore(missingUserRecords).execute();
  }

  public void batchUpdateTweeters(final List<ListeningPartyTweeterRecord> tweeters) {
    LOGGER.info("Writing {} Tweeter records", tweeters.size());
    dsl.batchStore(tweeters).execute();
  }

  public List<ListeningPartyRecord> getActiveListeningPartyList(final int start, final int pageSize) {
    LOGGER.debug("Reading from start=[{}] page size=[{}]", start, pageSize);
    return dsl
        .selectFrom(LISTENING_PARTY)
        .where(LISTENING_PARTY.ACTIVE.isTrue())
        .orderBy(LISTENING_PARTY.TTLP_NO)
        .seek(start)
        .limit(pageSize)
        .fetch();
  }

  public List<ListeningPartyRecord> getActiveListeningPartyUpdateList(final int start, final int pageSize) {
    LOGGER.debug("Reading from start=[{}] page size=[{}]", start, pageSize);
    return dsl
        .selectFrom(LISTENING_PARTY)
        .where(LISTENING_PARTY.REQUIRES_UPDATE.isTrue())
        .and(LISTENING_PARTY.ACTIVE.isTrue())
        .and(LISTENING_PARTY.COLLECTION_LINK.contains("timelines"))
        .orderBy(LISTENING_PARTY.TTLP_NO)
        .seek(start)
        .limit(pageSize)
        .fetch();
  }

  public ListeningPartyRecord selectActiveByTtlpId(final Integer ttlpId) {
    return dsl.selectFrom(LISTENING_PARTY)
              .where(LISTENING_PARTY.TTLP_NO.eq(ttlpId))
              .and(LISTENING_PARTY.ACTIVE.isTrue())
              .fetchAny();
  }

  public List<ListeningPartyTweetDto> getListeningPartyTweets(final Integer ttlpId) {
    return
        dsl.select(LISTENING_PARTY_TWEET.TTLP_NO,
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
           .on(LISTENING_PARTY.TTLP_NO.eq(LISTENING_PARTY_TWEET.TTLP_NO))
           .where(LISTENING_PARTY.ACTIVE.isTrue())
           .and(LISTENING_PARTY_TWEET.ACTIVE.isTrue())
           .and(LISTENING_PARTY.TTLP_NO.eq(ttlpId))
           .fetch()
           .into(ListeningPartyTweetDto.class);
  }


  public int update(final ListeningPartyRecord listeningPartyRecord) {
    return dsl.update(LISTENING_PARTY).set(listeningPartyRecord)
              .where(LISTENING_PARTY.TTLP_NO.eq(listeningPartyRecord.getTtlpNo()))
              .execute();
  }


  public Map<ListeningPartyRecord, List<ListeningPartyTweetDto>> getListeningPartiesTweets(final List<ListeningPartyRecord> listeningParties) {

    List<Integer> partyIds = listeningParties.stream().map(ListeningPartyRecord::getTtlpNo).toList();

    List<ListeningPartyTweetDto> tweetDtoList = dsl.select(LISTENING_PARTY_TWEET.TTLP_NO,
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
                                                   .on(LISTENING_PARTY.TTLP_NO.eq(LISTENING_PARTY_TWEET.TTLP_NO))
                                                   .where(LISTENING_PARTY.ACTIVE.isTrue())
                                                   .and(LISTENING_PARTY_TWEET.ACTIVE.isTrue())
                                                   .and(LISTENING_PARTY.TTLP_NO.in(partyIds)).fetch().into(ListeningPartyTweetDto.class);

    return tweetDtoList.stream()
                       .collect(Collectors.groupingBy(ListeningPartyTweetDto::ttlpNo)).entrySet().stream()
                       .collect(
                           Collectors.toMap(entry -> listeningParties.stream()
                                                                     .filter(lp -> lp.getTtlpNo().equals(entry.getKey()))
                                                                     .findFirst()
                                                                     .orElse(null),
                                            Entry::getValue));
  }

  public Map<ListeningPartyRecord, List<ListeningPartyUserDto>> getListeningPartiesTweeters(final List<ListeningPartyRecord> listeningParties) {
    List<Integer> partyIds = listeningParties.stream().map(ListeningPartyRecord::getTtlpNo).toList();
    Map<Integer, String> tweeters = dsl.selectFrom(LISTENING_PARTY)
                                       .where(LISTENING_PARTY.TTLP_NO.in(partyIds))
                                       .and(LISTENING_PARTY.COLLECTION_LINK.isNotNull())
                                       .fetch().intoMap(LISTENING_PARTY.TTLP_NO, LISTENING_PARTY.TWEETERS);
    tweeters.forEach((k, v) -> LOGGER.info("For party id {} we have tweeters {}", k, v));
    List<String> userNames =
        tweeters.values().stream().flatMap(str -> Arrays.stream(str.split(":"))).map(s -> s.replaceFirst("@", "")).toList();
    userNames.forEach(userName -> LOGGER.info("List of user names to query {}", userName));
    List<ListeningPartyUserDto> tweetUserDtoList = dsl.select(LISTENING_PARTY_USER.ID,
                                                              LISTENING_PARTY_USER.USER_ID,
                                                              LISTENING_PARTY_USER.DISPLAY_NAME,
                                                              LISTENING_PARTY_USER.DESCRIPTION,
                                                              LISTENING_PARTY_USER.TWEETS,
                                                              LISTENING_PARTY_USER.URL,
                                                              LISTENING_PARTY_USER.CREATED_AT,
                                                              LISTENING_PARTY_USER.FOLLOWERS,
                                                              LISTENING_PARTY_USER.FOLLOWING,
                                                              LISTENING_PARTY_USER.LOCATION,
                                                              LISTENING_PARTY_USER.PROFILE_URL
                                                      )
                                                      .from(LISTENING_PARTY_USER)
                                                      .where(LISTENING_PARTY_USER.NAME.in(userNames))
                                                      .fetch().into(ListeningPartyUserDto.class);

    return null;
  }


  public Map<ListeningPartyTweetDto, ListeningPartyUserDto> getListeningPartiesAuthors(final List<ListeningPartyTweetDto> listeningPartyTweets) {
    List<String> authorIds = listeningPartyTweets.stream().map(ListeningPartyTweetDto::author).distinct().toList();
    LOGGER.info("We have {} authors for {} tweets", authorIds.size(), listeningPartyTweets.size());
    List<ListeningPartyUserDto> tweetUserDtoList = dsl.select(LISTENING_PARTY_USER.ID,
                                                              LISTENING_PARTY_USER.USER_ID,
                                                              LISTENING_PARTY_USER.DISPLAY_NAME,
                                                              LISTENING_PARTY_USER.DESCRIPTION,
                                                              LISTENING_PARTY_USER.TWEETS,
                                                              LISTENING_PARTY_USER.URL,
                                                              LISTENING_PARTY_USER.CREATED_AT,
                                                              LISTENING_PARTY_USER.FOLLOWERS,
                                                              LISTENING_PARTY_USER.FOLLOWING,
                                                              LISTENING_PARTY_USER.LOCATION,
                                                              LISTENING_PARTY_USER.PROFILE_URL
                                                      )
                                                      .from(LISTENING_PARTY_USER)
                                                      .where(LISTENING_PARTY_USER.USER_ID.in(authorIds))
                                                      .fetch().into(ListeningPartyUserDto.class);

    Map<ListeningPartyTweetDto, ListeningPartyUserDto> map = new HashMap<>();
    for (ListeningPartyTweetDto tweetRecord : listeningPartyTweets) {
      map.put(tweetRecord, tweetUserDtoList.stream().filter(user -> user.userId().equals(tweetRecord.author())).findFirst().orElse(null));
    }
    return map;
  }

  /**
   * Gets all Authors of tweets in the database or reply users and if they don't exist in the LISTENING_PARTY_USER table or the MISSING_USER table
   * then returns them
   *
   * @return List of twitter user id strings that have been involved at a party but are not in the db
   */
  public List<String> getMissingListeningPartyUsers() {
    LOGGER.debug("getMissingListeningPartyUsers");
    List<String> missingAuthors = dsl
        .selectDistinct()
        .from(LISTENING_PARTY_TWEET)
        .leftJoin(LISTENING_PARTY_USER)
        .on(LISTENING_PARTY_TWEET.AUTHOR.eq(LISTENING_PARTY_USER.USER_ID))
        .leftJoin(MISSING_USER)
        .on(LISTENING_PARTY_TWEET.AUTHOR.eq(MISSING_USER.USER_ID))
        .where(LISTENING_PARTY_USER.USER_ID.isNull()).and(MISSING_USER.USER_ID.isNull()).fetch().getValues(LISTENING_PARTY_TWEET.AUTHOR);

    List<String> missingReplyTo = dsl
        .selectDistinct()
        .from(LISTENING_PARTY_TWEET)
        .leftJoin(LISTENING_PARTY_USER)
        .on(LISTENING_PARTY_TWEET.IN_REPLY_TO_USERID.eq(LISTENING_PARTY_USER.USER_ID))
        .leftJoin(MISSING_USER)
        .on(LISTENING_PARTY_TWEET.IN_REPLY_TO_USERID.eq(MISSING_USER.USER_ID))
        .where(LISTENING_PARTY_USER.USER_ID.isNull()).and(MISSING_USER.USER_ID.isNull()).fetch().getValues(LISTENING_PARTY_TWEET.IN_REPLY_TO_USERID);

    return Stream.concat(missingAuthors.stream(), missingReplyTo.stream())
                 .distinct()
                 .collect(Collectors.toList());

  }


}
