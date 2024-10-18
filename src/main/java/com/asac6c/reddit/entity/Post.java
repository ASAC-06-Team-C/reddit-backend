package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.postDto.PostCreateDto;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Getter
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class Post {
  Integer userNo;
  Integer postNo;
  String postTitle;
  String postContent;
  boolean postDraft;
  Date postWriteDate;
//  @NonFinal
//  static Integer lastId = 0;


  public static Post from (Integer postNo, PostCreateDto request) {
    return new Post(
        request.getUserNo(),
        postNo,
        request.getPostTitle(),
        request.getPostContent(),
        request.isPostDraft(),
        new Date()
        );
  }
}
