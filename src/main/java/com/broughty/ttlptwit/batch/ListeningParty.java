package com.broughty.ttlptwit.batch;

import static liquibase.repackaged.org.apache.commons.lang3.StringUtils.isNotBlank;
import static liquibase.repackaged.org.apache.commons.lang3.StringUtils.substringAfter;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record ListeningParty(String date, String artist, String album, String announcement, String replay, String tweeters, String collection,
                             String albumLink, String image, String image2, String releaseDate, String ttlpId, String image3) {


  private static final Logger LOGGER = LoggerFactory.getLogger(ListeningParty.class);

  /**
   * Gets the ttlp string id and returns as an integer
   */
  public static Integer ttlpIdAsInteger(ListeningParty listeningParty) {
    try {
      return Integer.parseInt(listeningParty.ttlpId);
    } catch (Exception e) {
      LOGGER.warn("Issue parsing the ttlp id [{}]", listeningParty.ttlpId);
    }
    return -1;
  }


  /**
   * Gets the collection id.  Twitter inserts the 'custom-' in front - see
   * <a href="https://developer.twitter.com/en/docs/twitter-api/v1/tweets/curate-a-collection/api-reference/get-collections-entries">here</a>
   *
   * @param collectionUrl takes the full collection url
   * @return the custom- collection id
   */
  public static String twitterCollection(String collectionUrl) {
    String collection = substringAfter(collectionUrl, "timelines/");
    if (isNotBlank(collection)) {
      return "custom-" + collection;
    }
    return null;
  }


  /**
   * Converts a {@link ListeningParty} to a {@link ListeningPartyRecord}
   *
   * @param listeningParty the {@link ListeningParty}
   * @return a new {@link ListeningPartyRecord} based on the listeningParty
   */
  public static ListeningPartyRecord toDatabaseRecord(ListeningParty listeningParty) {
    return new ListeningPartyRecord(LocalDateTime.parse(listeningParty.date, DateTimeFormatter.ISO_DATE_TIME), listeningParty.artist,
                                    listeningParty.album, listeningParty.announcement, listeningParty.replay, listeningParty.tweeters,
                                    listeningParty.collection, listeningParty.albumLink, listeningParty.image3, listeningParty.image2,
                                    listeningParty.releaseDate, ttlpIdAsInteger(listeningParty), listeningParty.hashCode(), true);
  }


}


