/*
 * This file is generated by jOOQ.
 */
package com.broughty.ttlptwit.database.jooq.data.tables;


import com.broughty.ttlptwit.database.jooq.data.DefaultSchema;
import com.broughty.ttlptwit.database.jooq.data.Keys;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweetRecord;
import java.time.LocalDateTime;
import java.util.function.Function;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function15;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row15;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ListeningPartyTweet extends TableImpl<ListeningPartyTweetRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>LISTENING_PARTY_TWEET</code>
     */
    public static final ListeningPartyTweet LISTENING_PARTY_TWEET = new ListeningPartyTweet();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ListeningPartyTweetRecord> getRecordType() {
        return ListeningPartyTweetRecord.class;
    }

    /**
     * The column <code>LISTENING_PARTY_TWEET.ID</code>.
     */
    public final TableField<ListeningPartyTweetRecord, Long> ID = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.TWEET_ID</code>.
     */
    public final TableField<ListeningPartyTweetRecord, String> TWEET_ID = createField(DSL.name("TWEET_ID"), SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.TTLP_NO</code>.
     */
    public final TableField<ListeningPartyTweetRecord, Integer>
        TTLP_NO =
        createField(DSL.name("TTLP_NO"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.TEXT</code>.
     */
    public final TableField<ListeningPartyTweetRecord, String> TEXT = createField(DSL.name("TEXT"), SQLDataType.VARCHAR(512), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.AUTHOR</code>.
     */
    public final TableField<ListeningPartyTweetRecord, String> AUTHOR = createField(DSL.name("AUTHOR"), SQLDataType.VARCHAR(512), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.IN_REPLY_TO_USERID</code>.
     */
    public final TableField<ListeningPartyTweetRecord, String> IN_REPLY_TO_USERID = createField(DSL.name("IN_REPLY_TO_USERID"), SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.CREATED_AT</code>.
     */
    public final TableField<ListeningPartyTweetRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.LIKES</code>.
     */
    public final TableField<ListeningPartyTweetRecord, Integer> LIKES = createField(DSL.name("LIKES"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.QUOTE</code>.
     */
    public final TableField<ListeningPartyTweetRecord, Integer> QUOTE = createField(DSL.name("QUOTE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.REPLY</code>.
     */
    public final TableField<ListeningPartyTweetRecord, Integer> REPLY = createField(DSL.name("REPLY"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.RETWEET</code>.
     */
    public final TableField<ListeningPartyTweetRecord, Integer> RETWEET = createField(DSL.name("RETWEET"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.GEO</code>.
     */
    public final TableField<ListeningPartyTweetRecord, String> GEO = createField(DSL.name("GEO"), SQLDataType.VARCHAR(512), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.ATTACHMENTS</code>.
     */
    public final TableField<ListeningPartyTweetRecord, String> ATTACHMENTS = createField(DSL.name("ATTACHMENTS"), SQLDataType.VARCHAR(512), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.ACTIVE</code>.
     */
    public final TableField<ListeningPartyTweetRecord, Boolean>
        ACTIVE =
        createField(DSL.name("ACTIVE"), SQLDataType.BOOLEAN.defaultValue(DSL.field("TRUE", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>LISTENING_PARTY_TWEET.CREATED_DATE</code>.
     */
    public final TableField<ListeningPartyTweetRecord, LocalDateTime>
        CREATED_DATE =
        createField(DSL.name("CREATED_DATE"), SQLDataType.LOCALDATETIME(6), this, "");

    private ListeningPartyTweet(Name alias, Table<ListeningPartyTweetRecord> aliased) {
        this(alias, aliased, null);
    }

    private ListeningPartyTweet(Name alias, Table<ListeningPartyTweetRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>LISTENING_PARTY_TWEET</code> table reference
     */
    public ListeningPartyTweet(String alias) {
        this(DSL.name(alias), LISTENING_PARTY_TWEET);
    }

    /**
     * Create an aliased <code>LISTENING_PARTY_TWEET</code> table reference
     */
    public ListeningPartyTweet(Name alias) {
        this(alias, LISTENING_PARTY_TWEET);
    }

    /**
     * Create a <code>LISTENING_PARTY_TWEET</code> table reference
     */
    public ListeningPartyTweet() {
        this(DSL.name("LISTENING_PARTY_TWEET"), null);
    }

    public <O extends Record> ListeningPartyTweet(Table<O> child, ForeignKey<O, ListeningPartyTweetRecord> key) {
        super(child, key, LISTENING_PARTY_TWEET);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<ListeningPartyTweetRecord, Long> getIdentity() {
        return (Identity<ListeningPartyTweetRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ListeningPartyTweetRecord> getPrimaryKey() {
        return Keys.PK_LISTENING_PARTY_TWEET;
    }

    @Override
    public ListeningPartyTweet as(String alias) {
        return new ListeningPartyTweet(DSL.name(alias), this);
    }

    @Override
    public ListeningPartyTweet as(Name alias) {
        return new ListeningPartyTweet(alias, this);
    }

    @Override
    public ListeningPartyTweet as(Table<?> alias) {
        return new ListeningPartyTweet(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ListeningPartyTweet rename(String name) {
        return new ListeningPartyTweet(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ListeningPartyTweet rename(Name name) {
        return new ListeningPartyTweet(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ListeningPartyTweet rename(Table<?> name) {
        return new ListeningPartyTweet(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row15<Long, String, Integer, String, String, String, LocalDateTime, Integer, Integer, Integer, Integer, String, String, Boolean, LocalDateTime> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function15<? super Long, ? super String, ? super Integer, ? super String, ? super String, ? super String, ? super LocalDateTime, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super String, ? super String, ? super Boolean, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType,
                                      Function15<? super Long, ? super String, ? super Integer, ? super String, ? super String, ? super String, ? super LocalDateTime, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super String, ? super String, ? super Boolean, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
