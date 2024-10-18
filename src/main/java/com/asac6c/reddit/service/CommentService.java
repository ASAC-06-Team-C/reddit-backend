package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentRequestDTO.Create;
import com.asac6c.reddit.dto.CommentRequestDTO.Reply;
import com.asac6c.reddit.dto.CommentResponseDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private Map<Integer, CommentResponseDTO> commentMap = new HashMap<>();
  private Map<Integer, Set<Integer>> votesMap = new HashMap<>();
  private int comment_no = 1;

  public List<CommentResponseDTO> getComments(CommentRequestDTO.Read readRequest) {
    return new ArrayList<>(commentMap.values());
  }

  public void createComment(Create createRequest) {
    CommentResponseDTO newComment = new CommentResponseDTO(
        createRequest.getUser_no(),
        null,
        null,
        comment_no++,
        0,
        createRequest.getComment_content(),
        LocalDateTime.now(),
        0,
        0);

    commentMap.put(newComment.getComment_no(), newComment);
  }

  public void updateComment(CommentRequestDTO.Update updateRequest) {
    CommentResponseDTO comment = commentMap.get(updateRequest.getComment_no());
    if (comment.getUser_no() == updateRequest.getUser_no()) {
      CommentResponseDTO updateComment = new CommentResponseDTO(
          comment.getUser_no(),
          comment.getUser_profile(),
          comment.getUser_nickname(),
          comment.getComment_no(),
          comment.getComment_vote_count(),
          updateRequest.getComment_content(),
          comment.getComment_write_date(),
          comment.getComment_mother(),
          comment.getComment_mother()
      );
      commentMap.replace(updateRequest.getComment_no(), updateComment);
    }
  }

  public void deleteComment(CommentRequestDTO.Delete deleteRequest) {
    commentMap.remove(deleteRequest.getComment_no());
  }

  public void voteComment(CommentRequestDTO.Vote voteRequest) {
    CommentResponseDTO comment = commentMap.get(voteRequest.getComment_no());
    if (comment == null) {
      return;
    }

    Set<Integer> votes = votesMap.computeIfAbsent(voteRequest.getComment_no(),
        k -> new HashSet<>());

    if (voteRequest.isVote_type()) {
      if (!votes.contains(voteRequest.getUser_no())) {
        votes.add(voteRequest.getUser_no());
        CommentResponseDTO updatedComment = new CommentResponseDTO(
            comment.getUser_no(),
            comment.getUser_profile(),
            comment.getUser_nickname(),
            comment.getComment_no(),
            comment.getComment_vote_count() + 1,
            comment.getComment_content(),
            comment.getComment_write_date(),
            comment.getComment_depth(),
            comment.getComment_mother()
        );
        commentMap.replace(voteRequest.getComment_no(), updatedComment);
      }
    }
  }


  public void replyComment(Reply replyRequest) {
    CommentResponseDTO parentComment = commentMap.get(replyRequest.getComment_mother());
    int depth = parentComment != null ? parentComment.getComment_depth() + 1 : 0;

    CommentResponseDTO newReply = new CommentResponseDTO(
        replyRequest.getUser_no(),
        null,
        null,
        comment_no++,
        0,
        replyRequest.getComment_content(),
        LocalDateTime.now(),
        depth,
        replyRequest.getComment_mother());

    commentMap.put(newReply.getComment_no(), newReply);
  }
}
