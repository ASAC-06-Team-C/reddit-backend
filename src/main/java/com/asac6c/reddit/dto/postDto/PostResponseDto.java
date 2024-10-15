package com.asac6c.reddit.dto.postDto;

import com.asac6c.reddit.repository.Post;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class PostResponseDto {
  Integer postNo;
  String postTitle;
  String postContent;

  public static PostResponseDto from (Post response){
    return new PostResponseDto(
        response.getPostNo(),
        response.getPostTitle(),
        response.getPostContent()
    );
  }

}
