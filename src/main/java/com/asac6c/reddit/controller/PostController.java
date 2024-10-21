package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import org.springframework.http.ResponseEntity;

public interface PostController {
    public ResponseEntity<PostGetResponseDto> getPost(Integer post_no);
    public ResponseEntity<Void> deletePost(Integer post_no);
    public ResponseEntity<Void> createPostVote(PostVoteCreateRequestDto postVoteCreateRequestDto);
}
