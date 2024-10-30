package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.CommentRequestDTO;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<CommentResponseDTO> createComment(
      @RequestBody CommentRequestDTO.Create createRequest) {
    CommentResponseDTO comment = commentService.createComment(createRequest);
    System.out.println(createRequest.getCommentContent());
    return ResponseEntity.status(HttpStatus.CREATED).body(comment);
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
    System.out.println(voteRequest.isCommentVoteType());
    System.out.println(voteRequest.getCommentNo());
    commentService.voteComment(voteRequest);
    return ResponseEntity.ok("성공");
  }
}