package com.asac6c.reddit.entity;


public enum VoteType {
    LIKE,
    DISLIKE,
    NONE;


    public static VoteType deserialize(String type) {
        for (VoteType postVoteType : VoteType.values()) {
            if (postVoteType.name().equals(type.toUpperCase())) {
                return postVoteType;
            }
        }
        return null;
    }
}
