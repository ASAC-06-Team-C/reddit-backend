package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.PostEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

// 직렬화를 위해 Getter 필요
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true ,level = AccessLevel.PRIVATE)
public class PostGetResponseDto {
    Integer post_no;
    String post_title;
    String post_content;
    Integer post_vote_count;
    Integer post_comment_count;
    Date post_write_date;
    Author author;

    @Getter
    @RequiredArgsConstructor
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Author{
        Integer user_no;
        String user_nickname;
    }

    public static PostGetResponseDto from(PostEntity post, String user_nickname){
        return new PostGetResponseDto(
                post.getPost_no(),
                post.getPost_title(),
                post.getPost_content(),
                post.getPost_vote_count(),
                post.getPost_comment_count(),
                post.getPost_write_date(),
                new Author(
                        post.getUser_no(),
                        user_nickname
                )
        );
    }
}