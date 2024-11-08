package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CommentRequestDTO.*;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.exception.CommentCustomException;
import com.asac6c.reddit.exception.CommentExceptionType;
import com.asac6c.reddit.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
  
  private final CommentRepository commentRepository;
  
  public List<CommentResponseDTO> getComment(Read readRequest) {
    List<CommentEntity> comment = commentRepository.getComment(readRequest.getPostNo());
    
    // 댓글 없을 때, 예외 처리
    if (comment.isEmpty()) {
      throw new CommentCustomException(CommentExceptionType.COMMENT_NOT_FOUND);
    }
    
    return comment.stream()
            .map(commentEntity -> CommentResponseDTO.from(commentEntity, null, null))
            .toList();
  }
  
  public CommentResponseDTO createComment(Create createRequest) {
    // 일단. 댓글 내용이 없을 때, 예외처리
    if (createRequest.getCommentContent() == null || createRequest.getCommentContent().isEmpty()) {
      throw new CommentCustomException(CommentExceptionType.COMMENT_CREATE_FAILED);
    }
    
    try {
      return CommentResponseDTO.from(commentRepository.createComment(createRequest), null, null);
    } catch (Exception e) {
      throw new CommentCustomException(CommentExceptionType.COMMENT_CREATE_FAILED);
    }
  }
  
  public void updateComment(Update updateRequest) {
    if (updateRequest.getCommentContent() == null || updateRequest.getCommentContent().isEmpty()) {
      throw new CommentCustomException(CommentExceptionType.COMMENT_UPDATE_FAILED);
    }
    
    try {
      commentRepository.updateComment(updateRequest);
    } catch (Exception e) {
      throw new CommentCustomException(CommentExceptionType.COMMENT_UPDATE_FAILED);
    }
  }
  
  public void deleteComment(Delete deleteRequest) {
    try {
      commentRepository.deleteComment(deleteRequest);
    } catch (Exception e) {
      throw new CommentCustomException(CommentExceptionType.COMMENT_DELETE_FAILED);
    }
  }
  
  public void voteComment(Vote voteRequest) {
    try {
      commentRepository.voteComment(voteRequest);
    } catch (Exception e) {
      throw new CommentCustomException(CommentExceptionType.COMMENT_VOTE_FAILED);
    }
  }
}
