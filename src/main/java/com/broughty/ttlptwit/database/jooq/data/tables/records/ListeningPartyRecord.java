/*
 * This file is generated by jOOQ.
 */
package com.broughty.ttlptwit.database.jooq.data.tables.records;


import com.broughty.ttlptwit.database.jooq.data.tables.ListeningParty;
import java.time.LocalDateTime;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record17;
import org.jooq.Row17;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ListeningPartyRecord extends UpdatableRecordImpl<ListeningPartyRecord> implements
    Record17<Long, LocalDateTime, String, String, String, String, String, String, String, String, String, String, Integer, Integer, Boolean, Boolean, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached, initialised ListeningPartyRecord
     */
    public ListeningPartyRecord(Long id,
                                LocalDateTime partyDate,
                                String artist,
                                String album,
                                String announcementTweet,
                                String replayLink,
                                String tweeters,
                                String collectionLink,
                                String albumLink,
                                String albumImageLarge,
                                String albumImageSmall,
                                String albumReleaseDate,
                                Integer ttlpNo,
                                Integer lastState,
                                Boolean requiresUpdate,
                                Boolean active,
                                LocalDateTime createdDate) {
        super(ListeningParty.LISTENING_PARTY);

        setId(id);
        setPartyDate(partyDate);
        setArtist(artist);
        setAlbum(album);
        setAnnouncementTweet(announcementTweet);
        setReplayLink(replayLink);
        setTweeters(tweeters);
        setCollectionLink(collectionLink);
        setAlbumLink(albumLink);
        setAlbumImageLarge(albumImageLarge);
        setAlbumImageSmall(albumImageSmall);
        setAlbumReleaseDate(albumReleaseDate);
        setTtlpNo(ttlpNo);
        setLastState(lastState);
        setRequiresUpdate(requiresUpdate);
        setActive(active);
        setCreatedDate(createdDate);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.PARTY_DATE</code>.
     */
    public LocalDateTime getPartyDate() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>LISTENING_PARTY.PARTY_DATE</code>.
     */
    public void setPartyDate(LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ARTIST</code>.
     */
    public String getArtist() {
        return (String) get(2);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ARTIST</code>.
     */
    public void setArtist(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ALBUM</code>.
     */
    public String getAlbum() {
        return (String) get(3);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ALBUM</code>.
     */
    public void setAlbum(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ANNOUNCEMENT_TWEET</code>.
     */
    public String getAnnouncementTweet() {
        return (String) get(4);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ANNOUNCEMENT_TWEET</code>.
     */
    public void setAnnouncementTweet(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.REPLAY_LINK</code>.
     */
    public String getReplayLink() {
        return (String) get(5);
    }

    /**
     * Setter for <code>LISTENING_PARTY.REPLAY_LINK</code>.
     */
    public void setReplayLink(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.TWEETERS</code>.
     */
    public String getTweeters() {
        return (String) get(6);
    }

    /**
     * Setter for <code>LISTENING_PARTY.TWEETERS</code>.
     */
    public void setTweeters(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.COLLECTION_LINK</code>.
     */
    public String getCollectionLink() {
        return (String) get(7);
    }

    /**
     * Setter for <code>LISTENING_PARTY.COLLECTION_LINK</code>.
     */
    public void setCollectionLink(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ALBUM_LINK</code>.
     */
    public String getAlbumLink() {
        return (String) get(8);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ALBUM_LINK</code>.
     */
    public void setAlbumLink(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ALBUM_IMAGE_LARGE</code>.
     */
    public String getAlbumImageLarge() {
        return (String) get(9);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ALBUM_IMAGE_LARGE</code>.
     */
    public void setAlbumImageLarge(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ALBUM_IMAGE_SMALL</code>.
     */
    public String getAlbumImageSmall() {
        return (String) get(10);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ALBUM_IMAGE_SMALL</code>.
     */
    public void setAlbumImageSmall(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ALBUM_RELEASE_DATE</code>.
     */
    public String getAlbumReleaseDate() {
        return (String) get(11);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ALBUM_RELEASE_DATE</code>.
     */
    public void setAlbumReleaseDate(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.TTLP_NO</code>.
     */
    public Integer getTtlpNo() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>LISTENING_PARTY.TTLP_NO</code>.
     */
    public void setTtlpNo(Integer value) {
        set(12, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.LAST_STATE</code>.
     */
    public Integer getLastState() {
        return (Integer) get(13);
    }

    /**
     * Setter for <code>LISTENING_PARTY.LAST_STATE</code>.
     */
    public void setLastState(Integer value) {
        set(13, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.REQUIRES_UPDATE</code>.
     */
    public Boolean getRequiresUpdate() {
        return (Boolean) get(14);
    }

    /**
     * Setter for <code>LISTENING_PARTY.REQUIRES_UPDATE</code>.
     */
    public void setRequiresUpdate(Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.ACTIVE</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(15);
    }

    /**
     * Setter for <code>LISTENING_PARTY.ACTIVE</code>.
     */
    public void setActive(Boolean value) {
        set(15, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY.CREATED_DATE</code>.
     */
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(16);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>LISTENING_PARTY.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(16, value);
    }

    // -------------------------------------------------------------------------
    // Record17 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    @Override
    public Row17<Long, LocalDateTime, String, String, String, String, String, String, String, String, String, String, Integer, Integer, Boolean, Boolean, LocalDateTime> fieldsRow() {
        return (Row17) super.fieldsRow();
    }

    @Override
    public Row17<Long, LocalDateTime, String, String, String, String, String, String, String, String, String, String, Integer, Integer, Boolean, Boolean, LocalDateTime> valuesRow() {
        return (Row17) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ListeningParty.LISTENING_PARTY.ID;
    }

    @Override
    public Field<LocalDateTime> field2() {
        return ListeningParty.LISTENING_PARTY.PARTY_DATE;
    }

    @Override
    public Field<String> field3() {
        return ListeningParty.LISTENING_PARTY.ARTIST;
    }

    @Override
    public Field<String> field4() {
        return ListeningParty.LISTENING_PARTY.ALBUM;
    }

    @Override
    public Field<String> field5() {
        return ListeningParty.LISTENING_PARTY.ANNOUNCEMENT_TWEET;
    }

    @Override
    public Field<String> field6() {
        return ListeningParty.LISTENING_PARTY.REPLAY_LINK;
    }

    @Override
    public Field<String> field7() {
        return ListeningParty.LISTENING_PARTY.TWEETERS;
    }

    @Override
    public Field<String> field8() {
        return ListeningParty.LISTENING_PARTY.COLLECTION_LINK;
    }

    @Override
    public Field<String> field9() {
        return ListeningParty.LISTENING_PARTY.ALBUM_LINK;
    }

    @Override
    public Field<String> field10() {
        return ListeningParty.LISTENING_PARTY.ALBUM_IMAGE_LARGE;
    }

    @Override
    public Field<String> field11() {
        return ListeningParty.LISTENING_PARTY.ALBUM_IMAGE_SMALL;
    }

    @Override
    public Field<String> field12() {
        return ListeningParty.LISTENING_PARTY.ALBUM_RELEASE_DATE;
    }

    @Override
    public Field<Integer> field13() {
        return ListeningParty.LISTENING_PARTY.TTLP_NO;
    }

    @Override
    public Field<Integer> field14() {
        return ListeningParty.LISTENING_PARTY.LAST_STATE;
    }

    @Override
    public Field<Boolean> field15() {
        return ListeningParty.LISTENING_PARTY.REQUIRES_UPDATE;
    }

    @Override
    public Field<Boolean> field16() {
        return ListeningParty.LISTENING_PARTY.ACTIVE;
    }

    @Override
    public Field<LocalDateTime> field17() {
        return ListeningParty.LISTENING_PARTY.CREATED_DATE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public LocalDateTime component2() {
        return getPartyDate();
    }

    @Override
    public String component3() {
        return getArtist();
    }

    @Override
    public String component4() {
        return getAlbum();
    }

    @Override
    public String component5() {
        return getAnnouncementTweet();
    }

    @Override
    public String component6() {
        return getReplayLink();
    }

    @Override
    public String component7() {
        return getTweeters();
    }

    @Override
    public String component8() {
        return getCollectionLink();
    }

    @Override
    public String component9() {
        return getAlbumLink();
    }

    @Override
    public String component10() {
        return getAlbumImageLarge();
    }

    @Override
    public String component11() {
        return getAlbumImageSmall();
    }

    @Override
    public String component12() {
        return getAlbumReleaseDate();
    }

    @Override
    public Integer component13() {
        return getTtlpNo();
    }

    @Override
    public Integer component14() {
        return getLastState();
    }

    @Override
    public Boolean component15() {
        return getRequiresUpdate();
    }

    @Override
    public Boolean component16() {
        return getActive();
    }

    @Override
    public LocalDateTime component17() {
        return getCreatedDate();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public LocalDateTime value2() {
        return getPartyDate();
    }

    @Override
    public String value3() {
        return getArtist();
    }

    @Override
    public String value4() {
        return getAlbum();
    }

    @Override
    public String value5() {
        return getAnnouncementTweet();
    }

    @Override
    public String value6() {
        return getReplayLink();
    }

    @Override
    public String value7() {
        return getTweeters();
    }

    @Override
    public String value8() {
        return getCollectionLink();
    }

    @Override
    public String value9() {
        return getAlbumLink();
    }

    @Override
    public String value10() {
        return getAlbumImageLarge();
    }

    @Override
    public String value11() {
        return getAlbumImageSmall();
    }

    @Override
    public String value12() {
        return getAlbumReleaseDate();
    }

    @Override
    public Integer value13() {
        return getTtlpNo();
    }

    @Override
    public Integer value14() {
        return getLastState();
    }

    @Override
    public Boolean value15() {
        return getRequiresUpdate();
    }

    @Override
    public Boolean value16() {
        return getActive();
    }

    @Override
    public LocalDateTime value17() {
        return getCreatedDate();
    }

    @Override
    public ListeningPartyRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value2(LocalDateTime value) {
        setPartyDate(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value3(String value) {
        setArtist(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value4(String value) {
        setAlbum(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value5(String value) {
        setAnnouncementTweet(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value6(String value) {
        setReplayLink(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value7(String value) {
        setTweeters(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value8(String value) {
        setCollectionLink(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value9(String value) {
        setAlbumLink(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value10(String value) {
        setAlbumImageLarge(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value11(String value) {
        setAlbumImageSmall(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value12(String value) {
        setAlbumReleaseDate(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value13(Integer value) {
        setTtlpNo(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value14(Integer value) {
        setLastState(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value15(Boolean value) {
        setRequiresUpdate(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value16(Boolean value) {
        setActive(value);
        return this;
    }

    @Override
    public ListeningPartyRecord value17(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ListeningPartyRecord
     */
    public ListeningPartyRecord() {
        super(ListeningParty.LISTENING_PARTY);
    }

    @Override
    public ListeningPartyRecord values(Long value1,
                                       LocalDateTime value2,
                                       String value3,
                                       String value4,
                                       String value5,
                                       String value6,
                                       String value7,
                                       String value8,
                                       String value9,
                                       String value10,
                                       String value11,
                                       String value12,
                                       Integer value13,
                                       Integer value14,
                                       Boolean value15,
                                       Boolean value16,
                                       LocalDateTime value17) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        return this;
    }
}
