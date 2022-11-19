/*
 * This file is generated by jOOQ.
 */
package com.broughty.ttlptwit.database.jooq.data;


import com.broughty.ttlptwit.database.jooq.data.tables.ListeningParty;
import com.broughty.ttlptwit.database.jooq.data.tables.ListeningPartyTweet;
import com.broughty.ttlptwit.database.jooq.data.tables.ListeningPartyTweeter;
import com.broughty.ttlptwit.database.jooq.data.tables.ListeningPartyUser;
import com.broughty.ttlptwit.database.jooq.data.tables.MissingUser;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweetRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyTweeterRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyUserRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.MissingUserRecord;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ListeningPartyRecord>
                                                               PK_LISTENING_PARTY         =
        Internal.createUniqueKey(ListeningParty.LISTENING_PARTY,
                                 DSL.name("PK_LISTENING_PARTY"),
                                 new TableField[]{ListeningParty.LISTENING_PARTY.ID},
                                 true);
    public static final UniqueKey<ListeningPartyTweetRecord>
                                                               PK_LISTENING_PARTY_TWEET   =
        Internal.createUniqueKey(ListeningPartyTweet.LISTENING_PARTY_TWEET,
                                 DSL.name("PK_LISTENING_PARTY_TWEET"),
                                 new TableField[]{ListeningPartyTweet.LISTENING_PARTY_TWEET.ID},
                                 true);
    public static final UniqueKey<ListeningPartyTweeterRecord>
                                                               PK_LISTENING_PARTY_TWEETER =
        Internal.createUniqueKey(ListeningPartyTweeter.LISTENING_PARTY_TWEETER,
                                 DSL.name("PK_LISTENING_PARTY_TWEETER"),
                                 new TableField[]{ListeningPartyTweeter.LISTENING_PARTY_TWEETER.ID},
                                 true);
    public static final UniqueKey<ListeningPartyUserRecord>
                                                               PK_LISTENING_PARTY_USER    =
        Internal.createUniqueKey(ListeningPartyUser.LISTENING_PARTY_USER,
                                 DSL.name("PK_LISTENING_PARTY_USER"),
                                 new TableField[]{ListeningPartyUser.LISTENING_PARTY_USER.ID},
                                 true);
    public static final UniqueKey<MissingUserRecord>
                                                               PK_MISSING_USER            =
        Internal.createUniqueKey(MissingUser.MISSING_USER, DSL.name("PK_MISSING_USER"), new TableField[]{MissingUser.MISSING_USER.ID}, true);
}
