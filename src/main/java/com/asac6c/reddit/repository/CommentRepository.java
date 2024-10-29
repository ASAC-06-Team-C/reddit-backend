package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.CommentRequestDTO.Create;
import com.asac6c.reddit.dto.CommentRequestDTO.Update;
import com.asac6c.reddit.dto.CommentRequestDTO.Vote;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.entity.CommentVoteEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

  private final Map<Integer, CommentEntity> commentMap = new HashMap<>();
  private final Map<Integer, CommentVoteEntity> commentVoteMap = new HashMap<>();

  private int commentNo = 1;
  private int commentVoteNo = 1;

  public List<CommentEntity> getComment(int postNo) {
    return commentMap.values().stream()
        .filter(comment -> comment.getPostNo() == postNo)
        .toList();
  }

  public List<CommentEntity> getAllComment() {
    return new ArrayList<>(commentMap.values());
  }

  public CommentEntity createComment(Create createRequest) {
    CommentEntity newComment = CommentEntity.from(createRequest, commentNo);

    commentMap.put(commentNo++, newComment);
    return newComment;
  }

  public void updateComment(Update updateRequest) {
    CommentEntity comment = commentMap.get(updateRequest.getCommentNo());

    comment.setCommentContent(updateRequest.getCommentContent());
  }

  public void deleteComment(int comment_no) {
    commentMap.remove(comment_no);
  }

  public void voteComment(Vote voteRequest) {
    CommentEntity comment = commentMap.get(voteRequest.getCommentNo());

    commentVoteMap.put(commentVoteNo, CommentVoteEntity.from(voteRequest, commentVoteNo++));

    int voteCount = voteRequest.isCommentVoteType()
        ? comment.getCommentVoteCount() + 1
        : comment.getCommentVoteCount() - 1;

    comment.setCommentVoteCount(voteCount);
  }
}