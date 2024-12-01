package com.asac6c.reddit.dto.postDto;

import com.asac6c.reddit.entity.PostEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostCreateResponseDto {

    Long postNo;
    String postTitle;
    String postContent;
    LocalDateTime postWriteDate;

    public static PostCreateResponseDto from(PostEntity post) {
        return new PostCreateResponseDto(
                post.getPostNo(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getCreatedAt()
        );
    }
}
