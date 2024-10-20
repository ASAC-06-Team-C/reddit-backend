package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class PostController {
  PostService postService;

  @PostMapping("/posts")
  public ResponseEntity<PostCreateResponseDto> createPost(@RequestBody PostCreateRequestDto request) {
    PostCreateResponseDto response = postService.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/drafts")
  public ResponseEntity<PostCreateResponseDto> createDraft(@RequestBody PostCreateRequestDto request) {
    PostCreateResponseDto response = postService.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/drafts/{post_no}")
  public ResponseEntity<PostResponseDto> getDraftDetail(@PathVariable Integer post_no) {
    PostResponseDto response = postService.getDraftDetailByUserId(post_no);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/drafts/")
  public ResponseEntity<List<DraftSummaryResponseDto>> getDraftList(@RequestParam Integer user_no) {
    List<DraftSummaryResponseDto> response = postService.getDraftListByUserId(user_no);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
  // 에러처리 통일필요함.

  // 쓰다보니까 draft 경우
  // 1. 이전에 받았던 draft를 받고 난 뒤에 update할때 (내용변경, false로)
  // 2. draft에서 post로 전환할 때 (시간, 내용 변경)

  // draft 서비스는 내가 추가할거ㅗ
  // 기능이 더 생기는 쪽이라면 이슈 추가


}
