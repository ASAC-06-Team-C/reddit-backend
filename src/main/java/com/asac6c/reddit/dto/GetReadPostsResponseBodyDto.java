package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.PostEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetReadPostsResponseBodyDto {

    Long userNo;
    Long postNo;
    String communityName;
    String postTitle;
    String postContent;
    Integer postVoteCount;
    Integer postCommentCount;
    String isVoted;
    LocalDateTime postWriteDate;
    Author author;

    public static GetReadPostsResponseBodyDto of(PostEntity post) {
        return new GetReadPostsResponseBodyDto(
                post.getUserEntity().getUserNo(),
                post.getPostNo(),
                post.getCommunityName(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getPostVoteCount(),
                post.getPostCommentCount(),
                "LIKE",
                post.getCreatedAt(),
                new Author(
                        post.getUserEntity().getUserNo(),
                        post.getUserEntity().getUserNickName()
                )
        );
    }

    @Getter
    @RequiredArgsConstructor
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Author {

        Long user_no;
        String user_nickname;
    }
}
