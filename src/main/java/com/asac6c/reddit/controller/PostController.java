package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.service.PostService;

import java.util.List;

import lombok.AccessLevel;
import jakarta.validation.Valid;
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

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {
    PostService postService;

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
            @Valid @RequestBody PostVoteCreateRequestDto postVoteCreateRequestDto
    ) {
        postService.putPostVote(postVoteCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
