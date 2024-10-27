package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.CommentRequestDTO.Create;
import com.asac6c.reddit.dto.CommentRequestDTO.Read;
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

  private int comment_no = 1;
  private int comment_vote_no = 1;

  public List<CommentEntity> getComment(Read readRequest) {
    return commentMap.values().stream()
        .filter(comment -> comment.getPost_no() == readRequest.getPost_no())
        .toList();
  }

  public List<CommentEntity> getAllComment() {
    return new ArrayList<>(commentMap.values());
  }

  public CommentEntity createComment(Create createRequest) {
    CommentEntity newComment = CommentEntity.createCommentFromRequest(createRequest, comment_no);

    commentMap.put(comment_no++, newComment);
    return newComment;
  }

  public void updateComment(Update updateRequest) {
    CommentEntity comment = commentMap.get(updateRequest.getComment_no());
    CommentEntity updateComment = CommentEntity.updateCommentFromRequest(updateRequest, comment);

    commentMap.replace(updateRequest.getComment_no(), updateComment);
  }

  public void deleteComment(int comment_no) {
    commentMap.remove(comment_no);
  }

  public void voteComment(Vote voteRequest) {
    CommentEntity comment = commentMap.get(voteRequest.getComment_no());
    
    commentVoteMap.put(comment_vote_no, CommentVoteEntity.from(voteRequest, comment_vote_no++));

    int voteCount = voteRequest.isComment_vote_type()
        ? comment.getComment_vote_count() + 1
        : comment.getComment_vote_count() - 1;

    CommentEntity updateVote = CommentEntity.withVoteCount(comment, voteCount);

    commentMap.replace(voteRequest.getComment_no(), updateVote);
  }
}
