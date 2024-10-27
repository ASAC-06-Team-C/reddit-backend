package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

  public static CommentEntity createCommentFromRequest(CommentRequestDTO.Create createRequest,
      int comment_no) {
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

  public static CommentEntity updateCommentFromRequest(CommentRequestDTO.Update updateRequest,
      CommentEntity commentEntity) {
    return new CommentEntity(
        commentEntity.getComment_no(),
        commentEntity.getPost_no(),
        updateRequest.getUser_no(),
        updateRequest.getComment_content(),
        commentEntity.getComment_vote_count(),
        commentEntity.getComment_mother(),
        commentEntity.getComment_depth(),
        LocalDateTime.now()
    );
  }

  public static CommentEntity withVoteCount(CommentEntity commentEntity, int comment_vote_count) {
    return new CommentEntity(
        commentEntity.getComment_no(),
        commentEntity.getPost_no(),
        commentEntity.getUser_no(),
        commentEntity.getComment_content(),
        comment_vote_count,
        commentEntity.getComment_mother(),
        commentEntity.getComment_depth(),
        LocalDateTime.now()
    );
  }
}