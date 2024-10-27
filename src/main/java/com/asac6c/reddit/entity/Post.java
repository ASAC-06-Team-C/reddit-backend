package com.asac6c.reddit.entity;


import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Builder
@RequiredArgsConstructor()
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Post {

  Integer postNo;
  Integer userNo;
  String communityName;
  String postTitle;
  String postContent;
  Integer postVoteCount;
  Integer postCommentCount;
  boolean postDraft;
  Date postWriteDate;

  public static Post.PostBuilder instanceForCreate(PostCreateRequestDto request) {
    return Post.builder()
        .userNo(request.getUserNo())
        .communityName("DUMMY")
        .postTitle(request.getPostTitle())
        .postContent(request.getPostContent())
        .postVoteCount(0)
        .postCommentCount(0)
        .postDraft(request.isPostDraft())
        .postWriteDate(new Date());
  }

  public static Post instanceForUpsert(DraftUpsertRequestDto request) {
    return new Post(
        request.getPostNo(),
        request.getUserNo(),
        "DUMMY",
        request.getPostTitle(),
        request.getPostContent(),
        0,
        0,
        request.isPostDraft(),
        new Date()
    );
  }
}

