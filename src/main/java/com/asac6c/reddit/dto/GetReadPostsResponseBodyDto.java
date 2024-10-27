package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.Post;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

    Integer userNo;
    Integer postNo;
    String communityName;
    String postTitle;
    String postContent;
    Integer postVoteCount;
    Integer postCommentCount;
    Date postWriteDate;

    public static GetReadPostsResponseBodyDto of(Post post) {
        return new GetReadPostsResponseBodyDto(
                post.getUserNo(),
                post.getPostNo(),
                post.getCommunityName(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getPostVoteCount(),
                post.getPostCommentCount(),
                post.getPostWriteDate()
        );
    }
}
