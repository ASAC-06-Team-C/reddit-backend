package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import org.springframework.http.ResponseEntity;

public interface IPostController {
    public ResponseEntity<PostGetResponseDto> getPost(Integer post_no);

    public ResponseEntity<Void> deletePost(Integer post_no);

    public ResponseEntity<Void> createPostVote(PostVoteUpdateRequestDto postVoteUpdateRequestDto);
}
