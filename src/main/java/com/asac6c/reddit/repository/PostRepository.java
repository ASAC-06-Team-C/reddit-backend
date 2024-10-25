package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.Post;
import com.asac6c.reddit.entity.PostVote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class PostRepository implements IPostRepository {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final Map<Integer, PostVote> postVotes = new HashMap<>();
    private Integer postId = 0;

    @Override
    public Post findPostById(Integer postId) {
        return posts.get(postId);
    }

    @Override
    public void deletePostById(Integer postId) {
        posts.remove(postId);
    }

    @Override
    public Integer savePostVote(PostVote postVoteEntity) {
        PostVote postVote = postVotes.put(postId++, postVoteEntity);
        return postVote.getPostVoteNo();
    }
}
