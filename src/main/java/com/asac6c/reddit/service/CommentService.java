package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
  
  private final CommentRepository commentRepository;
  
  
  public List<CommentResponseDTO> getAllComment(CommentRequestDTO.Read readRequest) {
    List<CommentEntity> comment = commentRepository.getAllComment();
    return comment.stream()
            .map(commentEntity -> CommentResponseDTO.from(commentEntity, null, null))
            .collect(Collectors.toList());
  }
  
  public void createComment(CommentRequestDTO.Create createRequest) {
    CommentResponseDTO.from(commentRepository.createComment(createRequest), null, null);
  }
  
  public void updateComment(CommentRequestDTO.Update updateRequest) {
    commentRepository.updateComment(updateRequest);
  }
  
  public void deleteComment(CommentRequestDTO.Delete deleteRequest) {
    commentRepository.deleteComment(deleteRequest.getComment_no());
  }
  
  public void voteComment(CommentRequestDTO.Vote voteRequest) {
    commentRepository.voteComment(voteRequest);
  }
}
