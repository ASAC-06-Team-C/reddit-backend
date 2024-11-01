package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentVoteEntity {
  
  private final int commentVoteNo;
  private final int userNo;
  private final int commentNo;
  private final String commentVoteType;
  
  public static CommentVoteEntity from(CommentRequestDTO.Vote voteRequest, int commentVoteNo) {
    return new CommentVoteEntity(
            commentVoteNo,
            voteRequest.getUserNo(),
            voteRequest.getCommentNo(),
            voteRequest.getCommentVoteType()
    );
  }
}
