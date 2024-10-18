package com.asac6c.reddit.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponseDTO {

  private final int user_no;
  private final String user_profile;
  private final String user_nickname;
  private final int comment_no;
  private final int comment_vote_count;
  private final String comment_content;
  private final LocalDateTime comment_write_date;
  private final int comment_depth;
  private final int comment_mother;

  public CommentResponseDTO(int user_no, String user_profile, String user_nickname, int comment_no,
      int comment_vote_count, String comment_content,
      LocalDateTime comment_write_date, int comment_depth, int comment_mother) {
    this.user_no = user_no;
    this.user_profile = user_profile;
    this.user_nickname = user_nickname;
    this.comment_no = comment_no;
    this.comment_vote_count = comment_vote_count;
    this.comment_content = comment_content;
    this.comment_write_date = comment_write_date;
    this.comment_depth = comment_depth;
    this.comment_mother = comment_mother;
  }
}
