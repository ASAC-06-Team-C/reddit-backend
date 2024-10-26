package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.CommentEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
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
  private final int post_no;
  
  public static CommentResponseDTO from(CommentEntity commentEntity, String user_profile, String user_nickname) {
    return new CommentResponseDTO(
            commentEntity.getUser_no(),
            user_profile,
            user_nickname,
            commentEntity.getComment_no(),
            commentEntity.getComment_vote_count(),
            commentEntity.getComment_content(),
            commentEntity.getComment_write_date(),
            commentEntity.getComment_depth(),
            commentEntity.getComment_mother(),
            commentEntity.getPost_no()
    );
  }
}
