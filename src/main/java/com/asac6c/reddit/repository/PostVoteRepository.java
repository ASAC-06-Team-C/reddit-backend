package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.PostVoteCreateRequestDto;

public interface PostVoteRepository {
    public void savePostVote(PostVoteCreateRequestDto postVote);

    public void deletePostVote(Integer postVoteId);
}
