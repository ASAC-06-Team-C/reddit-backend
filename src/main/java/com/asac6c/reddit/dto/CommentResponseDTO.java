package com.asac6c.reddit.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponseDTO {

  private int userNo;
  private String userProfile;
  private String userNickname;
  private int commentNo;
  private int commentVoteCount;
  private String commentContent;
  private LocalDateTime commentWriteDate;
  private int commentDepth;
  private int commentMother;

  public CommentResponseDTO(int userNo, int commentNo, String commentContent,
      LocalDateTime commentWriteDate, int commentMother) {
    this.userNo = userNo;
    this.commentNo = commentNo;
    this.commentContent = commentContent;
    this.commentWriteDate = commentWriteDate;
    this.commentMother = commentMother;
  }

  public void addVote() {
    this.commentVoteCount++;
  }

  public void subVote() {
    this.commentVoteCount--;
  }
}
