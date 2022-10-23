package com.broughty.ttlptwit.repository;

import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweetRecord;
import java.util.List;
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

  public int update(final ListeningPartyRecord listeningPartyRecord) {
    return dsl.update(LISTENING_PARTY).set(listeningPartyRecord)
              .where(LISTENING_PARTY.TTLP_NO.eq(listeningPartyRecord.getTtlpNo()))
              .execute();
  }


}
