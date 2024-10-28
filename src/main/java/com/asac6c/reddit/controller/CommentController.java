package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
  
  private final CommentService commentService;
  
  @GetMapping
  public ResponseEntity<List<CommentResponseDTO>> getComment(
          @RequestParam("post_no") int postNo,
          @RequestParam("sort_type") String sortType,
          @RequestParam("post_comment_count") int postCommentCount,
          @RequestParam("comment_page") int commentPage) {
    
    CommentRequestDTO.Read readRequest = new CommentRequestDTO.Read(
            postNo,
            sortType,
            postCommentCount,
            commentPage
    );
    
    List<CommentResponseDTO> comment = commentService.getComment(readRequest);
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