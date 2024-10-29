package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class CommentEntity {

  private final int commentNo;
  private final int postNo;
  private final int userNo;
  @Setter
  private String commentContent;
  @Setter
  private int commentVoteCount;
  private final int commentMother;
  private final int commentDepth;
  private final LocalDateTime commentWriteDate;

  public static CommentEntity from(CommentRequestDTO.Create createRequest, int commentNo) {
    return new CommentEntity(
        commentNo,
        createRequest.getPostNo(),
        createRequest.getUserNo(),
        createRequest.getCommentContent(),
        0,
        createRequest.getCommentMother(),
        createRequest.getCommentDepth(),
        LocalDateTime.now()
    );
  }
}