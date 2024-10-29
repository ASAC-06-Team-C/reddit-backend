package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.GetReadPostsResponseBodyDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.dto.GetReadPostsRequestBodyDto;
import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
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

    public PostCreateResponseDto createDraft(PostCreateRequestDto request) {
        Post.PostBuilder tempPost = Post.instanceForCreate(request);
        Post generatedPost = postRepository.createPost(tempPost);
        return PostCreateResponseDto.from(generatedPost);
    }

    public PostCreateResponseDto createPostByDraft(DraftUpsertRequestDto request) {
        Post requestPost = Post.instanceForUpsert(request);
        postRepository.upsertPostDetail(requestPost);
        return PostCreateResponseDto.from(requestPost);
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


    public List<GetReadPostsResponseBodyDto> getPostsContents(GetReadPostsRequestBodyDto requestBody) {
        return postRepository.getPostContents(requestBody);
    }


}
