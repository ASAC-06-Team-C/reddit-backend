package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.PostVoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository{
    private final Map<Integer, PostEntity> posts = new HashMap<>();
    private final Map<Integer, PostVoteEntity> postVotes = new HashMap<>();
    private Integer postId = 0;

    @Override
    public PostEntity findPostById(Integer postId) {
        return posts.get(postId);
    }

    @Override
    public void deletePostById(Integer postId) {
        posts.remove(postId);
    }

    @Override
    public Integer savePostVote(PostVoteEntity postVoteEntity) {
        PostVoteEntity postVote = postVotes.put(postId++, postVoteEntity);
        return postVote.getPost_vote_no();
    }
}
