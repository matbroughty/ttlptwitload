package com.broughty.ttlptwit.batch;

import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY_TWEETER;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweeterRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class ListeningPartyWriter implements ItemWriter<ListeningPartyRecord> {

  private final ListeningPartyRepository repository;

  public ListeningPartyWriter(final ListeningPartyRepository repository) {
    this.repository = repository;
  }

  @Override
  public void write(@NonNull final List<? extends ListeningPartyRecord> list) {
    repository.batchUpdate(list);
    persistTweeters(list);
  }

  private void persistTweeters(final List<? extends ListeningPartyRecord> list) {
    list.stream().filter(lp -> isNotBlank(lp.getTweeters()))
        .forEach(lp -> {
          List<ListeningPartyTweeterRecord> tweeters =
              Arrays.stream(lp.getTweeters().split(":")).map(s -> s.replaceFirst("@", "")).map(
                  s -> {
                    ListeningPartyTweeterRecord lptr =
                        new ListeningPartyTweeterRecord(null, s, lp.getTtlpNo(), LocalDateTime.now(), true, LocalDateTime.now());
                    lptr.changed(LISTENING_PARTY_TWEETER.ID, false);
                    return lptr;
                  }
              ).toList();
          repository.batchUpdateTweeters(tweeters);
        });

  }
}
