package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class CommentEntity {
  private final int comment_no;
  private final int post_no;
  private final int user_no;
  private final String comment_content;
  private final int comment_vote_count;
  private final int comment_mother;
  private final int comment_depth;
  private final LocalDateTime comment_write_date;
  
  public static CommentEntity from(CommentRequestDTO.Create createRequest, int comment_no) {
    return new CommentEntity(
            comment_no,
            createRequest.getPost_no(),
            createRequest.getUser_no(),
            createRequest.getComment_content(),
            0,
            createRequest.getComment_mother(),
            createRequest.getComment_depth(),
            LocalDateTime.now()
    );
  }
}