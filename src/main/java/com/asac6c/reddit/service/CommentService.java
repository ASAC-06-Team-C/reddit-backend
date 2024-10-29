package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public List<CommentResponseDTO> getComment(CommentRequestDTO.Read readRequest) {
    List<CommentEntity> comment = commentRepository.getComment(readRequest.getPostNo());
    return comment.stream()
        .map(commentEntity -> CommentResponseDTO.from(commentEntity, null, null))
        .toList();
  }

  public List<CommentResponseDTO> getAllComment(CommentRequestDTO.Read readRequest) {
    List<CommentEntity> comment = commentRepository.getAllComment();
    return comment.stream()
        .map(commentEntity -> CommentResponseDTO.from(commentEntity, null, null))
        .toList();
  }

  public void createComment(CommentRequestDTO.Create createRequest) {
    CommentResponseDTO.from(commentRepository.createComment(createRequest), null, null);
  }

  public void updateComment(CommentRequestDTO.Update updateRequest) {
    commentRepository.updateComment(updateRequest);
  }

  public void deleteComment(CommentRequestDTO.Delete deleteRequest) {
    commentRepository.deleteComment(deleteRequest.getCommentNo());
  }

  public void voteComment(CommentRequestDTO.Vote voteRequest) {
    commentRepository.voteComment(voteRequest);
  }
}
