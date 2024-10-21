package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;

public interface PostService {
    public PostGetResponseDto getPost(Integer id);
    public void deletePost(Integer id);
    public void putPostVote(PostVoteCreateRequestDto voteRequest);
}
