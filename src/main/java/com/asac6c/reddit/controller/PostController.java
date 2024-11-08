package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.GetReadPostsResponseBodyDto;
import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import com.asac6c.reddit.dto.GetReadPostsRequestBodyDto;
import com.asac6c.reddit.entity.PostVoteType;
import com.asac6c.reddit.service.PostService;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostController {

    PostService postService;

    @PostMapping("")
    public ResponseEntity<PostCreateResponseDto> createDraft(
            @RequestBody PostCreateRequestDto request) {
        PostCreateResponseDto response = postService.createDraft(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{post_no}")
    public ResponseEntity<PostGetResponseDto> getPost(
            @PathVariable("post_no") Integer post_no
    ) {
        PostGetResponseDto post = postService.getPost(post_no);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping(value = "/{post_no}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Integer post_no
    ) {
        postService.deletePost(post_no);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/vote")
    public ResponseEntity<Void> createPostVote(
            @Valid @RequestBody PostVoteUpdateRequestDto postVoteUpdateRequestDto
    ) {
        if (postVoteUpdateRequestDto.getPostVoteType().equals(PostVoteType.NONE)) {
            postService.deletePostVote(postVoteUpdateRequestDto);
        } else {
            postService.updatePostVote(postVoteUpdateRequestDto);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("")
    public ResponseEntity<List<GetReadPostsResponseBodyDto>> readPosts(@Valid GetReadPostsRequestBodyDto requestBody) {

        List<GetReadPostsResponseBodyDto> response = postService.getPostsContents(requestBody);

        return ResponseEntity.ok(response);
    }

    @PutMapping("")
    public ResponseEntity<PostCreateResponseDto> createPostByDraft(
            @RequestBody DraftUpsertRequestDto request) {
        PostCreateResponseDto response = postService.createPostByDraft(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
