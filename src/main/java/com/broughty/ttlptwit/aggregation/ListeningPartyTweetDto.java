package com.broughty.ttlptwit.aggregation;

import java.time.LocalDateTime;

public record ListeningPartyTweetDto(Integer ttlpNo, String artist, String album, String id, String text, String author, String geo,
                                     Integer likes, Integer retweets, Integer quotes, Integer replies, String inReplyTo, LocalDateTime createdAt) {

}
