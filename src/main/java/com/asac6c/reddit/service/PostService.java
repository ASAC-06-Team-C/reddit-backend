package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.GetReadPostsResponseBodyDto;
import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.dto.GetReadPostsRequestBodyDto;
import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.entity.PostEntity;
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
        PostEntity post = postRepository.findPostById(postId);
        String authorNickname = userRepository.getUserById(post.getUserEntity().getUserNo()).getUserNickName();
        return PostGetResponseDto.from(post, authorNickname);
    }


    public PostCreateResponseDto createDraft(/*PostCreateRequestDto request*/) {
//        PostEntity.PostBuilder tempPost = null;
        PostEntity generatedPost = postRepository.createPost();
        return PostCreateResponseDto.from(generatedPost);
    }


    public PostCreateResponseDto createPostByDraft(DraftUpsertRequestDto request) {
//        Post requestPost = Post.instanceForUpsert(request);
        PostEntity requestPost = null;
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


    public List<GetReadPostsResponseBodyDto> getPostsContents(
            GetReadPostsRequestBodyDto requestBody) {
        return postRepository.getPostContents(requestBody);
    }


}
