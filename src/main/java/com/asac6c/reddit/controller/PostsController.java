package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.dto.PostsResponseBody;
import com.asac6c.reddit.service.PostsService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

    PostsService postsService;

    @PostMapping("/posts")
    @ResponseBody
    public List<PostsResponseBody> posts(@RequestBody PostsRequestBody requestBody){

        return postsService.getPostsContents(requestBody);

    }
}
