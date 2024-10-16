package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.dto.PostsResponseBody;
import com.asac6c.reddit.repository.PostsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository repository;

    public List<PostsResponseBody> getPostsContents(PostsRequestBody requestBody){
        return repository.getPostContents(requestBody);
    }


}
