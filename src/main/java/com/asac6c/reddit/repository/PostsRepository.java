package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.dto.PostResponseBody;
import com.asac6c.reddit.dto.PostsResponseBody;
import org.springframework.stereotype.Repository;

@Repository
public class PostsRepository implements IPostsRepository<PostsResponseBody, PostsRequestBody> {

    // 쿼리문 개념임
    public PostsResponseBody getPostContents(PostsRequestBody request) {
        return null;
    }
}
