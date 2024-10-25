package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostResponseBody;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.dto.PostsRequestBody;
import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.entity.Post;
import com.asac6c.reddit.exception.PostCustomException;
import com.asac6c.reddit.exception.PostExceptionType;
import com.asac6c.reddit.repository.PostRepository;
import com.asac6c.reddit.repository.PostVoteRepository;
import com.asac6c.reddit.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService {
    PostRepository postRepository;
    PostVoteRepository postVoteRepository;
    UserRepository userRepository;

    public PostGetResponseDto getPost(Integer postId) {
        Post post = postRepository.findPostById(postId);
        String authorNickname = userRepository.getUserById(post.getUserNo()).getUser_nickname();
        return PostGetResponseDto.from(post, authorNickname);
    }

    public PostResponseDto getDraftDetailByUserId(Integer id) {
        return postRepository.getDraftByPostId(id).map(PostResponseDto::from)
                .orElseThrow(() -> new PostCustomException(PostExceptionType.POST_NOT_EXIST, id));
    }

    public void deletePost(Integer postId) {
        postRepository.deletePostById(postId);
    }

    public List<DraftSummaryResponseDto> getDraftListByUserId(Integer userId) {
        return postRepository.getDraftListByUserId(userId).stream()
                .map(DraftSummaryResponseDto::from)
                .toList();
    }

    public void putPostVote(PostVoteCreateRequestDto voteRequest) {
        postVoteRepository.savePostVote(voteRequest);
    }


    public List<PostResponseBody> getPostsContents(PostsRequestBody requestBody) {
        return postRepository.getPostContents(requestBody);
    }


}
