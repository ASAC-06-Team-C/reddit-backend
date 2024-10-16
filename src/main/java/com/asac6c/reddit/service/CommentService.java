package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentResponseDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private Map<Integer, CommentResponseDTO> commentMap = new HashMap<>();
  private int commentNo = 1;

  public List<CommentResponseDTO> getComments(CommentRequestDTO.Read readRequest) {
    return new ArrayList<>(commentMap.values());
  }

  public CommentResponseDTO createComment(CommentRequestDTO.Create createRequest) {
    CommentResponseDTO newComment = new CommentResponseDTO(
        createRequest.getUserNo(),
        commentNo++,
        createRequest.getCommentContent(),
        LocalDateTime.now(),
        0);

    commentMap.put(newComment.getCommentNo(), newComment);

    return newComment;
  }

  public void updateComment(CommentRequestDTO.Update updateRequest) {
    CommentResponseDTO comment = commentMap.get(updateRequest.getCommentNo());
    if (comment.getUserNo() == updateRequest.getUserNo()) {
      CommentResponseDTO updateComment = new CommentResponseDTO(
          comment.getUserNo(),
          comment.getCommentNo(),
          updateRequest.getCommentContent(),
          comment.getCommentWriteDate(),
          comment.getCommentMother()
      );
      commentMap.replace(updateRequest.getCommentNo(), updateComment);
    }
  }

  public void deleteComment(CommentRequestDTO.Delete deleteRequest) {
    commentMap.remove(deleteRequest.getCommentNo());
  }

  public void voteComment(CommentRequestDTO.Vote voteRequest) {
    CommentResponseDTO comment = commentMap.get(voteRequest.getCommentNo());
    if (comment != null) {
      if (voteRequest.isVoteType()) {
        comment.addVote();
      } else {
        comment.subVote();
      }
    }
  }

  public CommentResponseDTO replyComment(CommentRequestDTO.Reply replyRequest) {
    CommentResponseDTO newReply = new CommentResponseDTO(
        replyRequest.getUserNo(),
        commentNo++,
        replyRequest.getCommentContent(),
        LocalDateTime.now(),
        replyRequest.getCommentMother());

    commentMap.put(newReply.getCommentNo(), newReply);
    return newReply;
  }
}
