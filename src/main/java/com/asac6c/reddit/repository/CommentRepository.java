package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.entity.CommentEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class CommentRepository {
  private final Map<Integer, CommentEntity> commentMap = new HashMap<>();
  private final Map<Integer, Set<Integer>> commentVoteMap = new HashMap<>();
  
  private int comment_no = 1;
  
  public CommentEntity getComment(int comment_no) {
    return commentMap.get(comment_no);
  }
  
  public List<CommentEntity> getAllComment() {
    return new ArrayList<>(commentMap.values());
  }
  
  public CommentEntity createComment(CommentRequestDTO.Create createRequest) {
    CommentEntity newComment = CommentEntity.from(createRequest, comment_no);
    commentMap.put(comment_no++, newComment);
    return newComment;
  }
  
  public void updateComment(CommentRequestDTO.Update updateRequest) {
    CommentEntity comment = commentMap.get(updateRequest.getComment_no());
    CommentEntity updateComment = new CommentEntity(
            comment.getComment_no(),
            comment.getPost_no(),
            updateRequest.getUser_no(),
            updateRequest.getComment_content(),
            comment.getComment_vote_count(),
            comment.getComment_mother(),
            comment.getComment_depth(),
            LocalDateTime.now()
    );
    
    commentMap.replace(updateRequest.getComment_no(), updateComment);
  }
  
  public void deleteComment(int comment_no) {
    commentMap.remove(comment_no);
  }
  
  public void voteComment(CommentRequestDTO.Vote voteRequest) {
    CommentEntity comment = commentMap.get(voteRequest.getComment_no());
    
    Set<Integer> vote = commentVoteMap.computeIfAbsent(voteRequest.getComment_no(),
            k -> new HashSet<>());
    
    if (voteRequest.isComment_vote_type()) {
      if (!vote.contains(voteRequest.getUser_no())) {
        vote.add(voteRequest.getUser_no());
        CommentEntity updateComment = new CommentEntity(
                voteRequest.getComment_no(),
                comment.getPost_no(),
                voteRequest.getUser_no(),
                comment.getComment_content(),
                comment.getComment_vote_count() + 1,
                comment.getComment_mother(),
                comment.getComment_depth(),
                LocalDateTime.now()
        );
        commentMap.replace(voteRequest.getComment_no(), updateComment);
      }
    }
  }
}
