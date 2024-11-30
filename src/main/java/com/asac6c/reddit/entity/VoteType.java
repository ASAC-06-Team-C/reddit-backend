package com.asac6c.reddit.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum VoteType {
    LIKE,
    DISLIKE,
    NONE;

    @JsonCreator
    public static VoteType deserialize(String type) {
        for (VoteType postVoteType : VoteType.values()) {
            if (postVoteType.name().equals(type.toUpperCase())) {
                return postVoteType;
            }
        }
        return null;
    }
}
