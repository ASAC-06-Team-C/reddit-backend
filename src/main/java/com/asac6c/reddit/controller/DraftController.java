package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.postDto.DraftDeleteRequestDto;
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
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("")
    public ResponseEntity<List<DraftSummaryResponseDto>> upsertDraft(
            @RequestBody DraftUpsertRequestDto request) {
        List<DraftSummaryResponseDto> response = draftService.upsertDraftDetail(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<Integer> deleteDraft(@RequestBody DraftDeleteRequestDto request) {
        draftService.deleteDraft(request);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
