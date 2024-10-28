package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;

public interface IPostService {
    public PostGetResponseDto getPost(Integer id);

    public void deletePost(Integer id);

    public void putPostVote(PostVoteUpdateRequestDto voteRequest);
}
