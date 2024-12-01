package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.VoteType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

// 직렬화를 위해 Getter 필요
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostGetResponseDto {

    Long postNo;
    String postTitle;
    String postContent;
    Integer postVoteCount;
    Integer postCommentCount;
    LocalDateTime postWriteDate;
    Author author;

    @Getter
    @RequiredArgsConstructor
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Author {

        Long userNo;
        String userNickname;
        VoteType isVoted;
    }

    public static PostGetResponseDto from(PostEntity post, String userNickname, VoteType isVoted) {
        return new PostGetResponseDto(
                post.getPostNo(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getPostVoteCount(),
                post.getPostCommentCount(),
                post.getCreatedAt(),
                new Author(
                        post.getUserEntity().getUserNo(),
                        userNickname,
                        isVoted
                )
        );
    }
}