package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping
  public List<CommentResponseDTO> getComments(@RequestBody
  CommentRequestDTO.Read readRequest) {
    return commentService.getComments(readRequest);
  }

  @PostMapping
  public ResponseEntity<String> createComment(@RequestBody
  CommentRequestDTO.Create createRequest) {
    commentService.createComment(createRequest);
    return ResponseEntity.ok("성공");
  }

  @PutMapping
  public ResponseEntity<String> updateComment(@RequestBody CommentRequestDTO.Update updateRequest) {
    commentService.updateComment(updateRequest);
    return ResponseEntity.ok("성공");
  }

  @DeleteMapping
  public ResponseEntity<String> deleteComment(@RequestBody CommentRequestDTO.Delete deleteRequest) {
    commentService.deleteComment(deleteRequest);
    return ResponseEntity.ok("성공");
  }

  @PostMapping("/vote")
  public ResponseEntity<String> voteComment(@RequestBody CommentRequestDTO.Vote voteRequest) {
    commentService.voteComment(voteRequest);
    return ResponseEntity.ok("성공");
  }

  @PostMapping("/reply")
  public ResponseEntity<String> replyComment(
      @RequestBody CommentRequestDTO.Reply replyRequest) {
    commentService.replyComment(replyRequest);
    return ResponseEntity.ok("성공");
  }
}


