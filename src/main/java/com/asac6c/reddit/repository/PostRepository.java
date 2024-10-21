package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.PostVoteEntity;

public interface PostRepository {
    public PostEntity findPostById(Integer postId);
    public void deletePostById(Integer postId);
    public Integer savePostVote(PostVoteEntity postVoteEntity);
}