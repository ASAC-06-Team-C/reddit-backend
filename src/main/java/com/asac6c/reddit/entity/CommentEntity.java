package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class CommentEntity {

  private final int comment_no;
  private final int post_no;
  private final int user_no;
  @Setter
  private String comment_content;
  @Setter
  private int comment_vote_count;
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