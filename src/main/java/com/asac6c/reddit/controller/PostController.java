package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostResponseBody;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.service.PostService;
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


    @GetMapping("")
    public ResponseEntity<List<PostResponseBody>> posts(PostsRequestBody requestBody) {

        List<PostResponseBody> response = postService.getPostsContents(requestBody);

        return ResponseEntity.ok(response);
    }
}
