package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
  
  @Autowired
  private CommentService commentService;
  
  @GetMapping
  public ResponseEntity<List<CommentResponseDTO>> getAllComment(@RequestBody CommentRequestDTO.Read readRequest) {
    List<CommentResponseDTO> comment = commentService.getAllComment(readRequest);
    return ResponseEntity.ok(comment);
  }
  
  @PostMapping
  public ResponseEntity<String> createComment(@RequestBody CommentRequestDTO.Create createRequest) {
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
}