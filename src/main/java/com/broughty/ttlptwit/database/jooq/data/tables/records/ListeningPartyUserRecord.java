/*
 * This file is generated by jOOQ.
 */
package com.broughty.ttlptwit.database.jooq.data.tables.records;


import com.broughty.ttlptwit.database.jooq.data.tables.ListeningPartyUser;
import java.time.LocalDateTime;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ListeningPartyUserRecord extends UpdatableRecordImpl<ListeningPartyUserRecord> implements
    Record15<Long, String, String, String, String, String, LocalDateTime, Integer, Integer, Integer, String, String, String, Boolean, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LISTENING_PARTY_USER.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Create a detached ListeningPartyUserRecord
     */
    public ListeningPartyUserRecord() {
        super(ListeningPartyUser.LISTENING_PARTY_USER);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.USER_ID</code>.
     */
    public void setUserId(String value) {
        set(1, value);
    }

    /**
     * Create a detached, initialised ListeningPartyUserRecord
     */
    public ListeningPartyUserRecord(Long id,
                                    String userId,
                                    String name,
                                    String displayName,
                                    String location,
                                    String description,
                                    LocalDateTime createdAt,
                                    Integer followers,
                                    Integer following,
                                    Integer tweets,
                                    String lang,
                                    String url,
                                    String profileUrl,
                                    Boolean active,
                                    LocalDateTime createdDate) {
        super(ListeningPartyUser.LISTENING_PARTY_USER);

        setId(id);
        setUserId(userId);
        setName(name);
        setDisplayName(displayName);
        setLocation(location);
        setDescription(description);
        setCreatedAt(createdAt);
        setFollowers(followers);
        setFollowing(following);
        setTweets(tweets);
        setLang(lang);
        setUrl(url);
        setProfileUrl(profileUrl);
        setActive(active);
        setCreatedDate(createdDate);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.NAME</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.DISPLAY_NAME</code>.
     */
    public void setDisplayName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.USER_ID</code>.
     */
    public String getUserId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.LOCATION</code>.
     */
    public void setLocation(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.LOCATION</code>.
     */
    public String getLocation() {
        return (String) get(4);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.NAME</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.DESCRIPTION</code>.
     */
    public String getDescription() {
        return (String) get(5);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.DISPLAY_NAME</code>.
     */
    public String getDisplayName() {
        return (String) get(3);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.CREATED_AT</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.DESCRIPTION</code>.
     */
    public void setDescription(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.FOLLOWERS</code>.
     */
    public Integer getFollowers() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.CREATED_AT</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.FOLLOWING</code>.
     */
    public Integer getFollowing() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.FOLLOWERS</code>.
     */
    public void setFollowers(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.TWEETS</code>.
     */
    public Integer getTweets() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.FOLLOWING</code>.
     */
    public void setFollowing(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.LANG</code>.
     */
    public String getLang() {
        return (String) get(10);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.TWEETS</code>.
     */
    public void setTweets(Integer value) {
        set(9, value);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.LANG</code>.
     */
    public void setLang(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.URL</code>.
     */
    public String getUrl() {
        return (String) get(11);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.PROFILE_URL</code>.
     */
    public String getProfileUrl() {
        return (String) get(12);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.URL</code>.
     */
    public void setUrl(String value) {
        set(11, value);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.PROFILE_URL</code>.
     */
    public void setProfileUrl(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_USER.ACTIVE</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(13);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.ACTIVE</code>.
     */
    public void setActive(Boolean value) {
        set(13, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>LISTENING_PARTY_USER.CREATED_DATE</code>.
     */
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(14);
    }

    /**
     * Setter for <code>LISTENING_PARTY_USER.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(14, value);
    }

    @Override
    public Field<Long> field1() {
        return ListeningPartyUser.LISTENING_PARTY_USER.ID;
    }

    @Override
    public Field<String> field2() {
        return ListeningPartyUser.LISTENING_PARTY_USER.USER_ID;
    }

    @Override
    public Field<String> field3() {
        return ListeningPartyUser.LISTENING_PARTY_USER.NAME;
    }

    @Override
    public Field<String> field4() {
        return ListeningPartyUser.LISTENING_PARTY_USER.DISPLAY_NAME;
    }

    @Override
    public Field<String> field5() {
        return ListeningPartyUser.LISTENING_PARTY_USER.LOCATION;
    }

    @Override
    public Field<String> field6() {
        return ListeningPartyUser.LISTENING_PARTY_USER.DESCRIPTION;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return ListeningPartyUser.LISTENING_PARTY_USER.CREATED_AT;
    }

    @Override
    public Field<Integer> field8() {
        return ListeningPartyUser.LISTENING_PARTY_USER.FOLLOWERS;
    }

    @Override
    public Field<Integer> field9() {
        return ListeningPartyUser.LISTENING_PARTY_USER.FOLLOWING;
    }

    @Override
    public Field<Integer> field10() {
        return ListeningPartyUser.LISTENING_PARTY_USER.TWEETS;
    }

    @Override
    public Field<String> field11() {
        return ListeningPartyUser.LISTENING_PARTY_USER.LANG;
    }

    @Override
    public Field<String> field12() {
        return ListeningPartyUser.LISTENING_PARTY_USER.URL;
    }

    @Override
    public Field<String> field13() {
        return ListeningPartyUser.LISTENING_PARTY_USER.PROFILE_URL;
    }

    @Override
    public Row15<Long, String, String, String, String, String, LocalDateTime, Integer, Integer, Integer, String, String, String, Boolean, LocalDateTime> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    @Override
    public Row15<Long, String, String, String, String, String, LocalDateTime, Integer, Integer, Integer, String, String, String, Boolean, LocalDateTime> valuesRow() {
        return (Row15) super.valuesRow();
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUserId();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public String component4() {
        return getDisplayName();
    }

    @Override
    public String component5() {
        return getLocation();
    }

    @Override
    public String component6() {
        return getDescription();
    }

    @Override
    public LocalDateTime component7() {
        return getCreatedAt();
    }

    @Override
    public Integer component8() {
        return getFollowers();
    }

    @Override
    public Integer component9() {
        return getFollowing();
    }

    @Override
    public Integer component10() {
        return getTweets();
    }

    @Override
    public String component11() {
        return getLang();
    }

    @Override
    public String component12() {
        return getUrl();
    }

    @Override
    public String component13() {
        return getProfileUrl();
    }

    @Override
    public Field<Boolean> field14() {
        return ListeningPartyUser.LISTENING_PARTY_USER.ACTIVE;
    }

    @Override
    public Field<LocalDateTime> field15() {
        return ListeningPartyUser.LISTENING_PARTY_USER.CREATED_DATE;
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUserId();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public String value4() {
        return getDisplayName();
    }

    @Override
    public String value5() {
        return getLocation();
    }

    @Override
    public String value6() {
        return getDescription();
    }

    @Override
    public LocalDateTime value7() {
        return getCreatedAt();
    }

    @Override
    public Integer value8() {
        return getFollowers();
    }

    @Override
    public Integer value9() {
        return getFollowing();
    }

    @Override
    public Integer value10() {
        return getTweets();
    }

    @Override
    public String value11() {
        return getLang();
    }

    @Override
    public String value12() {
        return getUrl();
    }

    @Override
    public String value13() {
        return getProfileUrl();
    }

    @Override
    public Boolean component14() {
        return getActive();
    }

    @Override
    public LocalDateTime component15() {
        return getCreatedDate();
    }

    @Override
    public ListeningPartyUserRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value2(String value) {
        setUserId(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value4(String value) {
        setDisplayName(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value5(String value) {
        setLocation(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value6(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value7(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value8(Integer value) {
        setFollowers(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value9(Integer value) {
        setFollowing(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value10(Integer value) {
        setTweets(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value11(String value) {
        setLang(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value12(String value) {
        setUrl(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord value13(String value) {
        setProfileUrl(value);
        return this;
    }

    @Override
    public Boolean value14() {
        return getActive();
    }

    @Override
    public LocalDateTime value15() {
        return getCreatedDate();
    }

    @Override
    public ListeningPartyUserRecord value14(Boolean value) {
        setActive(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    public ListeningPartyUserRecord value15(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public ListeningPartyUserRecord values(Long value1,
                                           String value2,
                                           String value3,
                                           String value4,
                                           String value5,
                                           String value6,
                                           LocalDateTime value7,
                                           Integer value8,
                                           Integer value9,
                                           Integer value10,
                                           String value11,
                                           String value12,
                                           String value13,
                                           Boolean value14,
                                           LocalDateTime value15) {
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
        return this;
    }
}
