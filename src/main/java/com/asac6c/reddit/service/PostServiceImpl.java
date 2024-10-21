package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.repository.PostRepositoryImpl;
import com.asac6c.reddit.repository.PostVoteRepositoryImpl;
import com.asac6c.reddit.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepositoryImpl postRepository;
    private final PostVoteRepositoryImpl postVoteRepository;
    private final UserRepositoryImpl userRepository;

    @Override
    public PostGetResponseDto getPost(Integer postId) {
        PostEntity post = postRepository.findPostById(postId);
        String authorNickname = userRepository.getUserById(post.getUser_no()).getUser_nickname();
        return PostGetResponseDto.from(post,authorNickname);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deletePostById(postId);
    }

    @Override
    public void putPostVote(PostVoteCreateRequestDto voteRequest) {
        postVoteRepository.savePostVote(voteRequest);
    }
}
