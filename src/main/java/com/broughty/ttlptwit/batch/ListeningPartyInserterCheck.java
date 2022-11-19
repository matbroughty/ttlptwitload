package com.broughty.ttlptwit.batch;


import static com.broughty.ttlptwit.batch.ListeningParty.toDatabaseRecord;
import static com.broughty.ttlptwit.batch.ListeningParty.ttlpIdAsInteger;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ListeningPartyInserterCheck implements ItemProcessor<ListeningParty, ListeningPartyRecord> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListeningPartyInserterCheck.class);

  final ListeningPartyRepository repository;

  public ListeningPartyInserterCheck(final ListeningPartyRepository repository) {
    this.repository = repository;
  }


  @Override
  public ListeningPartyRecord process(@NonNull final ListeningParty listeningParty) {

    if (ttlpIdAsInteger(listeningParty) == -1) {
      LOGGER.debug("Skipping a non listening party {}", listeningParty);
      return null;
    }
    ListeningPartyRecord existingRecord = repository.selectActiveByTtlpId(ListeningParty.ttlpIdAsInteger(listeningParty));
    if (existingRecord == null) {
      // Need to create a new record
      LOGGER.info("Creating new Listening Party record -> {}", listeningParty);
      return toDatabaseRecord(listeningParty);
    }
    LOGGER.debug("Found existing record ttlp no {} - Artist {}.  Current hash = {} and last known has = {}",
                 existingRecord.getTtlpNo(), existingRecord.getArtist(), listeningParty.hashCode(), existingRecord.getLastState());
    // If hash changed then update the record and mark as requiring update
    if (!existingRecord.getLastState().equals(listeningParty.hashCode())) {
      ListeningPartyRecord listeningPartyRecord = toDatabaseRecord(listeningParty);
      // this doesn't affect the hash
      listeningPartyRecord.setRequiresUpdate(true);
      int val = repository.update(listeningPartyRecord);
      LOGGER.info("Updated record ttlp id  {} album {} and this returned code {}", listeningParty.ttlpId(), listeningParty.album(), val);
    }
    // don't need to do anything with the record as no change so return null
    return null;
  }
}
