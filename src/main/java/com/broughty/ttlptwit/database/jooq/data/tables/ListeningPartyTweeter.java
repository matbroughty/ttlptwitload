/*
 * This file is generated by jOOQ.
 */
package com.broughty.ttlptwit.database.jooq.data.tables;


import com.broughty.ttlptwit.database.jooq.data.DefaultSchema;
import com.broughty.ttlptwit.database.jooq.data.Keys;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweeterRecord;
import java.time.LocalDateTime;
import java.util.function.Function;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ListeningPartyTweeter extends TableImpl<ListeningPartyTweeterRecord> {

    /**
     * The reference instance of <code>LISTENING_PARTY_TWEETER</code>
     */
    public static final  ListeningPartyTweeter                                  LISTENING_PARTY_TWEETER = new ListeningPartyTweeter();
    private static final long                                                   serialVersionUID        = 1L;
    /**
     * The column <code>LISTENING_PARTY_TWEETER.ID</code>.
     */
    public final         TableField<ListeningPartyTweeterRecord, Long>
                                                                                ID                      =
        createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");
    /**
     * The column <code>LISTENING_PARTY_TWEETER.DISPLAY_NAME</code>.
     */
    public final         TableField<ListeningPartyTweeterRecord, String>
                                                                                DISPLAY_NAME            =
        createField(DSL.name("DISPLAY_NAME"), SQLDataType.VARCHAR(512).nullable(false), this, "");
    /**
     * The column <code>LISTENING_PARTY_TWEETER.TTLP_NO</code>.
     */
    public final         TableField<ListeningPartyTweeterRecord, Integer>
                                                                                TTLP_NO                 =
        createField(DSL.name("TTLP_NO"), SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>LISTENING_PARTY_TWEETER.UPDATED_AT</code>.
     */
    public final         TableField<ListeningPartyTweeterRecord, LocalDateTime>
                                                                                UPDATED_AT              =
        createField(DSL.name("UPDATED_AT"), SQLDataType.LOCALDATETIME(6), this, "");
    /**
     * The column <code>LISTENING_PARTY_TWEETER.ACTIVE</code>.
     */
    public final         TableField<ListeningPartyTweeterRecord, Boolean>
                                                                                ACTIVE                  =
        createField(DSL.name("ACTIVE"), SQLDataType.BOOLEAN.defaultValue(DSL.field("TRUE", SQLDataType.BOOLEAN)), this, "");
    /**
     * The column <code>LISTENING_PARTY_TWEETER.CREATED_DATE</code>.
     */
    public final         TableField<ListeningPartyTweeterRecord, LocalDateTime>
                                                                                CREATED_DATE            =
        createField(DSL.name("CREATED_DATE"), SQLDataType.LOCALDATETIME(6), this, "");

    private ListeningPartyTweeter(Name alias, Table<ListeningPartyTweeterRecord> aliased) {
        this(alias, aliased, null);
    }

    private ListeningPartyTweeter(Name alias, Table<ListeningPartyTweeterRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>LISTENING_PARTY_TWEETER</code> table reference
     */
    public ListeningPartyTweeter(String alias) {
        this(DSL.name(alias), LISTENING_PARTY_TWEETER);
    }

    /**
     * Create an aliased <code>LISTENING_PARTY_TWEETER</code> table reference
     */
    public ListeningPartyTweeter(Name alias) {
        this(alias, LISTENING_PARTY_TWEETER);
    }

    /**
     * Create a <code>LISTENING_PARTY_TWEETER</code> table reference
     */
    public ListeningPartyTweeter() {
        this(DSL.name("LISTENING_PARTY_TWEETER"), null);
    }

    public <O extends Record> ListeningPartyTweeter(Table<O> child, ForeignKey<O, ListeningPartyTweeterRecord> key) {
        super(child, key, LISTENING_PARTY_TWEETER);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ListeningPartyTweeterRecord> getRecordType() {
        return ListeningPartyTweeterRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<ListeningPartyTweeterRecord, Long> getIdentity() {
        return (Identity<ListeningPartyTweeterRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ListeningPartyTweeterRecord> getPrimaryKey() {
        return Keys.PK_LISTENING_PARTY_TWEETER;
    }

    @Override
    public ListeningPartyTweeter as(String alias) {
        return new ListeningPartyTweeter(DSL.name(alias), this);
    }

    @Override
    public ListeningPartyTweeter as(Name alias) {
        return new ListeningPartyTweeter(alias, this);
    }

    @Override
    public ListeningPartyTweeter as(Table<?> alias) {
        return new ListeningPartyTweeter(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ListeningPartyTweeter rename(String name) {
        return new ListeningPartyTweeter(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ListeningPartyTweeter rename(Name name) {
        return new ListeningPartyTweeter(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ListeningPartyTweeter rename(Table<?> name) {
        return new ListeningPartyTweeter(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, Integer, LocalDateTime, Boolean, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super Long, ? super String, ? super Integer, ? super LocalDateTime, ? super Boolean, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType,
                                      Function6<? super Long, ? super String, ? super Integer, ? super LocalDateTime, ? super Boolean, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}