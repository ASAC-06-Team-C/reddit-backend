package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.PostResponseBody;
import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostsRepository implements IPostsRepository<List<PostResponseBody>, PostsRequestBody> {

    static Map<Integer, Post> repositoryMap;

    static {
        // dummy data
        repositoryMap = new HashMap<>();
        repositoryMap.put(0, new Post(0, 0, "DUMMY", "제목 0", "내용 0", 0, 0, new Date(System.currentTimeMillis())));
        repositoryMap.put(1, new Post(1, 1, "DUMMY", "제목 1", "내용 1", 1, 1, new Date(System.currentTimeMillis())));
        repositoryMap.put(2, new Post(2, 2, "DUMMY", "제목 2", "내용 2", 2, 2, new Date(System.currentTimeMillis())));
        repositoryMap.put(3, new Post(3, 3, "DUMMY", "제목 3", "내용 3", 3, 3, new Date(System.currentTimeMillis())));
        repositoryMap.put(4, new Post(4, 4, "DUMMY", "제목 4", "내용 4", 4, 4, new Date(System.currentTimeMillis())));
        repositoryMap.put(5, new Post(5, 5, "DUMMY", "제목 5", "내용 5", 5, 5, new Date(System.currentTimeMillis())));
        repositoryMap.put(6, new Post(6, 6, "DUMMY", "제목 6", "내용 6", 6, 6, new Date(System.currentTimeMillis())));
        repositoryMap.put(7, new Post(7, 7, "DUMMY", "제목 7", "내용 7", 7, 7, new Date(System.currentTimeMillis())));
        repositoryMap.put(8, new Post(8, 8, "DUMMY", "제목 8", "내용 8", 8, 8, new Date(System.currentTimeMillis())));
        repositoryMap.put(9, new Post(9, 9, "DUMMY", "제목 9", "내용 9", 9, 9, new Date(System.currentTimeMillis())));
        repositoryMap.put(10,
                new Post(10, 10, "DUMMY", "제목 10", "내용 10", 10, 10, new Date(System.currentTimeMillis())));

    }

    /**
     * @param request {String sort_type Integer pages Integer content_count}
     * @return PostsResponseBody
     */
    public List<PostResponseBody> getPostContents(PostsRequestBody request) {

//        PostsResponseBody responseBody = new PostsResponseBody(new ArrayList<>());
        List<PostResponseBody> responseBodies = new ArrayList<>();

        int startIndex = (request.getPages() - 1) * request.getContent_count();
        int endIndex = startIndex + request.getContent_count();

        for (int i = startIndex; i < endIndex; i++) {
            responseBodies.add(
                    i, PostResponseBody.of(repositoryMap.get(i))
            );
        }
        return responseBodies;
    }
}
