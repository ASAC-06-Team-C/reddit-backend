package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Post {

  Integer userNo;
  Integer postNo;
  String postTitle;
  String postContent;
  boolean postDraft;
  Date postWriteDate;

//  @NonFinal
//  static Integer lastId = 0;

  public static Post.PostBuilder instanceForCreate(PostCreateRequestDto request) {
    return Post.builder()
        .userNo(request.getUserNo())
        .postTitle(request.getPostTitle())
        .postContent(request.getPostContent())
        .postDraft(request.isPostDraft())
        .postWriteDate(new Date());
  }

  public static Post instanceForUpsert(DraftUpsertRequestDto request) {
    return new Post(
        request.getUserNo(),
        request.getPostNo(),
        request.getPostTitle(),
        request.getPostContent(),
        request.isPostDraft(),
        new Date()
    );
  }
}
