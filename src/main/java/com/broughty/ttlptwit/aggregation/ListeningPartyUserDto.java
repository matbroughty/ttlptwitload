package com.broughty.ttlptwit.aggregation;

import java.time.LocalDateTime;

public record ListeningPartyUserDto(Long id, String userId, String displayName, String description,
                                    Integer tweets, String url, LocalDateTime createdAt, Integer followers, Integer following,
                                    String location, String profileUrl) {

}
