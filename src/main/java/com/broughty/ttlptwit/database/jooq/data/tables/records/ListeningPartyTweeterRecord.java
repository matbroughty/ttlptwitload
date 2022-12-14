/*
 * This file is generated by jOOQ.
 */
package com.broughty.ttlptwit.database.jooq.data.tables.records;


import com.broughty.ttlptwit.database.jooq.data.tables.ListeningPartyTweeter;
import java.time.LocalDateTime;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ListeningPartyTweeterRecord extends UpdatableRecordImpl<ListeningPartyTweeterRecord>
    implements Record6<Long, String, Integer, LocalDateTime, Boolean, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached ListeningPartyTweeterRecord
     */
    public ListeningPartyTweeterRecord() {
        super(ListeningPartyTweeter.LISTENING_PARTY_TWEETER);
    }

    /**
     * Create a detached, initialised ListeningPartyTweeterRecord
     */
    public ListeningPartyTweeterRecord(Long id,
                                       String displayName,
                                       Integer ttlpNo,
                                       LocalDateTime updatedAt,
                                       Boolean active,
                                       LocalDateTime createdDate) {
        super(ListeningPartyTweeter.LISTENING_PARTY_TWEETER);

        setId(id);
        setDisplayName(displayName);
        setTtlpNo(ttlpNo);
        setUpdatedAt(updatedAt);
        setActive(active);
        setCreatedDate(createdDate);
    }

    /**
     * Getter for <code>LISTENING_PARTY_TWEETER.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LISTENING_PARTY_TWEETER.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_TWEETER.DISPLAY_NAME</code>.
     */
    public String getDisplayName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LISTENING_PARTY_TWEETER.DISPLAY_NAME</code>.
     */
    public void setDisplayName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_TWEETER.TTLP_NO</code>.
     */
    public Integer getTtlpNo() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>LISTENING_PARTY_TWEETER.TTLP_NO</code>.
     */
    public void setTtlpNo(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_TWEETER.UPDATED_AT</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>LISTENING_PARTY_TWEETER.UPDATED_AT</code>.
     */
    public void setUpdatedAt(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>LISTENING_PARTY_TWEETER.ACTIVE</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>LISTENING_PARTY_TWEETER.ACTIVE</code>.
     */
    public void setActive(Boolean value) {
        set(4, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>LISTENING_PARTY_TWEETER.CREATED_DATE</code>.
     */
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>LISTENING_PARTY_TWEETER.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(5, value);
    }

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    @Override
    public Row6<Long, String, Integer, LocalDateTime, Boolean, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, String, Integer, LocalDateTime, Boolean, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ListeningPartyTweeter.LISTENING_PARTY_TWEETER.ID;
    }

    @Override
    public Field<String> field2() {
        return ListeningPartyTweeter.LISTENING_PARTY_TWEETER.DISPLAY_NAME;
    }

    @Override
    public Field<Integer> field3() {
        return ListeningPartyTweeter.LISTENING_PARTY_TWEETER.TTLP_NO;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return ListeningPartyTweeter.LISTENING_PARTY_TWEETER.UPDATED_AT;
    }

    @Override
    public Field<Boolean> field5() {
        return ListeningPartyTweeter.LISTENING_PARTY_TWEETER.ACTIVE;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return ListeningPartyTweeter.LISTENING_PARTY_TWEETER.CREATED_DATE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getDisplayName();
    }

    @Override
    public Integer component3() {
        return getTtlpNo();
    }

    @Override
    public LocalDateTime component4() {
        return getUpdatedAt();
    }

    @Override
    public Boolean component5() {
        return getActive();
    }

    @Override
    public LocalDateTime component6() {
        return getCreatedDate();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getDisplayName();
    }

    @Override
    public Integer value3() {
        return getTtlpNo();
    }

    @Override
    public LocalDateTime value4() {
        return getUpdatedAt();
    }

    @Override
    public Boolean value5() {
        return getActive();
    }

    @Override
    public LocalDateTime value6() {
        return getCreatedDate();
    }

    @Override
    public ListeningPartyTweeterRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public ListeningPartyTweeterRecord value2(String value) {
        setDisplayName(value);
        return this;
    }

    @Override
    public ListeningPartyTweeterRecord value3(Integer value) {
        setTtlpNo(value);
        return this;
    }

    @Override
    public ListeningPartyTweeterRecord value4(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public ListeningPartyTweeterRecord value5(Boolean value) {
        setActive(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    public ListeningPartyTweeterRecord value6(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public ListeningPartyTweeterRecord values(Long value1,
                                              String value2,
                                              Integer value3,
                                              LocalDateTime value4,
                                              Boolean value5,
                                              LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }
}
