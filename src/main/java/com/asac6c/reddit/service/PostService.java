package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.entity.Post;
import com.asac6c.reddit.repository.PostRepository;
import com.asac6c.reddit.repository.PostVoteRepository;
import com.asac6c.reddit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final PostVoteRepository postVoteRepository;
    private final UserRepository userRepository;

    @Override
    public PostGetResponseDto getPost(Integer postId) {
        Post post = postRepository.findPostById(postId);
        String authorNickname = userRepository.getUserById(post.getUserNo()).getUser_nickname();
        return PostGetResponseDto.from(post, authorNickname);
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
