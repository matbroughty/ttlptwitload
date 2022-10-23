package com.broughty.ttlptwit.batch;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.stereotype.Component;

@Component
public class ListeningPartyReader extends AbstractPagingItemReader<ListeningPartyRecord> {

  private final ListeningPartyRepository repository;

  public ListeningPartyReader(final ListeningPartyRepository repository) {
    this.repository = repository;
  }


  @Override
  protected void doReadPage() {
    int start = getPage() * getPageSize();
    results = new CopyOnWriteArrayList<>(repository.getListeningPartyUpdateList(start, getPageSize()));
  }

  @Override
  protected void doJumpToPage(final int i) {
    // no implementation
  }
}
