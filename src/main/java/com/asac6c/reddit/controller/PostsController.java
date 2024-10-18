package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.dto.PostsResponseBody;
import com.asac6c.reddit.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/posts")
    @ResponseBody
    public ResponseEntity<PostsResponseBody> posts(@RequestBody PostsRequestBody requestBody) {

        PostsResponseBody response = postsService.getPostsContents(requestBody);

        return ResponseEntity.ok(response);
    }
}
