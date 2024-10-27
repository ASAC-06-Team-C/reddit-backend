package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.service.DraftService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/drafts")
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DraftController {

  DraftService draftService;

  @PostMapping("")
  public ResponseEntity<PostCreateResponseDto> createDraft(
      @RequestBody PostCreateRequestDto request) {
    PostCreateResponseDto response = draftService.createDraft(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{post_no}")
  public ResponseEntity<PostResponseDto> getDraftDetail(@PathVariable Integer post_no) {
    PostResponseDto response = draftService.getDraftDetailByPostNo(post_no);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/")
  public ResponseEntity<List<DraftSummaryResponseDto>> getDraftList(@RequestParam Integer user_no) {
    List<DraftSummaryResponseDto> response = draftService.getDraftListByUserId(user_no);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  // 받고 draft에 업데이트 돼었다는거 보여줘야 하니까 summary Dto
  @PutMapping("")
  public ResponseEntity<DraftSummaryResponseDto> upsertDraft(
      @RequestBody DraftUpsertRequestDto request) {
    DraftSummaryResponseDto response = draftService.upsertDraftDetail(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
