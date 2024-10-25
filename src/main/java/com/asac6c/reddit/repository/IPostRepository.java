package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.Post;
import com.asac6c.reddit.entity.PostVote;

public interface IPostRepository {
    public Post findPostById(Integer postId);

    public void deletePostById(Integer postId);

    public Integer savePostVote(PostVote postVote);
}