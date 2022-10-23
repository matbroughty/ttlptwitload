package com.broughty.ttlptwit.batch;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
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
  }
}
