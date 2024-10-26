package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentVoteEntity {
  private final int comment_vote_no;
  private final int user_no;
  private final int comment_no;
  private final boolean comment_vote_type;
  
  public static CommentVoteEntity from(CommentRequestDTO.Vote voteRequest, int comment_vote_no) {
    return new CommentVoteEntity(
            comment_vote_no,
            voteRequest.getUser_no(),
            voteRequest.getComment_no(),
            voteRequest.isComment_vote_type()
    );
  }
}
