package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController implements IPostController {
    private final PostService postService;

    @GetMapping(value = "/{post_no}")
    @Override
    public ResponseEntity<PostGetResponseDto> getPost(
            @PathVariable("post_no") Integer post_no
    ) {
        PostGetResponseDto post = postService.getPost(post_no);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping(value = "/{post_no}")
    @Override
    public ResponseEntity<Void> deletePost(
            @PathVariable Integer post_no
    ) {
        postService.deletePost(post_no);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/vote")
    @Override
    public ResponseEntity<Void> createPostVote(
            @Valid @RequestBody PostVoteCreateRequestDto postVoteCreateRequestDto
    ) {
        postService.putPostVote(postVoteCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
