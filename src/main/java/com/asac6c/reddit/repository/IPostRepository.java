package com.asac6c.reddit.repository;

public interface IPostRepository {

    public Post findPostById(Integer postId);

    public void deletePostById(Integer postId);

    public Integer savePostVote(PostVote postVote);
}