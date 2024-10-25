package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.PostResponseBody;
import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseBody>> posts(PostsRequestBody requestBody) {

        List<PostResponseBody> response = postsService.getPostsContents(requestBody);

        return ResponseEntity.ok(response);
    }
}
