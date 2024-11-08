package com.asac6c.reddit.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PostVoteType {
    LIKE,
    DISLIKE,
    NONE;

    @JsonCreator
    public static PostVoteType deserialize(String type) {
        for (PostVoteType postVoteType : PostVoteType.values()) {
            if (postVoteType.name().equals(type.toUpperCase())) {
                return postVoteType;
            }
        }
        return null;
    }
}
