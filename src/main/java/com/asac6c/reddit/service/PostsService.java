package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostResponseBody;
import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository repository;

    public List<PostResponseBody> getPostsContents(PostsRequestBody requestBody) {
        return repository.getPostContents(requestBody);
    }


}
