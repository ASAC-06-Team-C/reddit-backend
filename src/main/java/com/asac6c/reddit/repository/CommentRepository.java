package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.CommentRequestDTO.Create;
import com.asac6c.reddit.dto.CommentRequestDTO.Update;
import com.asac6c.reddit.dto.CommentRequestDTO.Vote;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.entity.CommentVoteEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    int voteCount = comment.getCommentVoteCount();
    
    // 1. 댓글 no와 유저 no으로 commentVoteMap에서 투표를 했는지 확인.
    // 2. 투표를 하지 않았다면, 투표 타입에 맞게 up 또는 down
    // 3. 투표를 했다면, up, none, down에 맞춰서 생각.
    // 4. 그 전에 했던 좋아요 타입에 따라 계산?
    
    commentVoteMap.put(commentVoteNo, CommentVoteEntity.from(voteRequest, commentVoteNo++));

//    System.out.println(voteRequest.getCommentVoteType());
//    if (voteRequest.getCommentVoteType().equals("up")) {
//      voteCount = comment.getCommentVoteCount() + 1;
//    } else if (voteRequest.getCommentVoteType().equals("down")) {
//      voteCount = comment.getCommentVoteCount() - 1;
//    } else if (voteRequest.getCommentVoteType().equals("uptodown")) {
//      voteCount = comment.getCommentVoteCount() - 2;
//    } else if (voteRequest.getCommentVoteType().equals("downtoup")) {
//      voteCount = comment.getCommentVoteCount() + 2;
//    }

//    int voteCount = voteRequest.isCommentVoteType()
//        ? comment.getCommentVoteCount() + 1
//        : comment.getCommentVoteCount() - 1;
    
    comment.setCommentVoteCount(voteCount);
  }
}