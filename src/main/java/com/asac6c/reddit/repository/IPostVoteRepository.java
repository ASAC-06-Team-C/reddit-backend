package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;

public interface IPostVoteRepository {
    public void savePostVote(PostVoteUpdateRequestDto postVote);

    public void deletePostVote(Integer postVoteId);
}
