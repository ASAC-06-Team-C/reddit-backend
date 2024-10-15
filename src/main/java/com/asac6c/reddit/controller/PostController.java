package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.postDto.DraftResponseDto;
import com.asac6c.reddit.dto.postDto.PostCreateDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.service.PostService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class PostController {
  PostService postService;

  @PostMapping("/posts")
  public ResponseEntity<PostCreateResponseDto> create(@RequestBody PostCreateDto request) {
    PostCreateResponseDto response = postService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/drafts")
  public ResponseEntity<PostCreateResponseDto> createDraft(@RequestBody PostCreateDto request) {
    PostCreateResponseDto response = postService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/drafts/{post_no}")
  public ResponseEntity<PostResponseDto> getDraft(@PathVariable Integer post_no) {
    PostResponseDto response = postService.getDraft(post_no);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/drafts/{user_no}")
  public ResponseEntity<List<DraftResponseDto>> getDraftList(@PathVariable Integer user_no) {
    List<DraftResponseDto> response = postService.getDraftListByUserId(user_no);
    return ResponseEntity.ok(response);
  }

  // customException이랑 customResponseEntity
  // 날짜 추가햇는데 포맷 맞는지 확인.
  // 에러처리 통일필요함.

  // 쓰다보니까 draft 경우
  // 1. 이전에 받았던 draft를 받고 난 뒤에 update할때 (내용변경, false로)
  // 2. draft에서 post로 전환할 때 (시간, 내용 변경)
  // 3.


}
