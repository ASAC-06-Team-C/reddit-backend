package com.asac6c.reddit.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentVoteEntity {
  private final int comment_vote_no;
  private final int user_no;
  private final int comment_no;
  private final boolean comment_vote_type;
}
