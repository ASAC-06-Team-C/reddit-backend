package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.GetReadPostsResponseBodyDto;
import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import com.asac6c.reddit.dto.GetReadPostsRequestBodyDto;
import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.PostVoteEntity;
import com.asac6c.reddit.entity.UserEntity;
import com.asac6c.reddit.entity.VoteType;
import com.asac6c.reddit.exception.GetPostsCustomException;
import com.asac6c.reddit.exception.GetPostsExceptionType;
import com.asac6c.reddit.repository.PostRepository;
import com.asac6c.reddit.repository.PostVoteEntityRepository;
import com.asac6c.reddit.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService {

    PostRepository postRepository;
    PostVoteEntityRepository postVoteRepository;
    UserRepository userRepository;

    @Transactional
    public PostGetResponseDto getPost(Long postId) {
        PostEntity post = postRepository.findByPostNo(postId)
                .orElseThrow(() -> new GetPostsCustomException(
                        GetPostsExceptionType.UNDEFINED_EXCEPTION));
        String authorNickname = userRepository.findByUserNo(post.getUserEntity().getUserNo())
                .orElseThrow(() -> new GetPostsCustomException(
                        GetPostsExceptionType.ARGUMENT_TYPE_MISMATCH))
                .getUserNickName();
        Optional<PostVoteEntity> postVote = postVoteRepository.findByUserNoAndPostNo(
                post.getUserNo(),
                post.getPostNo());
        return PostGetResponseDto.from(post, authorNickname,
                postVote.isPresent() ? postVote.get().getPostVoteType() : VoteType.NONE);
    }


    @Transactional
    public PostCreateResponseDto createDraft(PostCreateRequestDto request) {
        PostEntity tempPost = PostEntity.forPostCreate(request);
        // dummy User
        tempPost.setUserEntity(new UserEntity(1L, "asdf", "1234", "dummy"));
        PostEntity generatedPost = postRepository.save(tempPost);
        return PostCreateResponseDto.from(generatedPost);
    }


    public PostCreateResponseDto createPostByDraft(DraftUpsertRequestDto request) {
        PostEntity requestPost = PostEntity.forSubmitDraft(request);
        requestPost.setUserEntity(new UserEntity(1L, "asdf", "1234", "dummy"));
        postRepository.save(requestPost);
        return PostCreateResponseDto.from(requestPost);
    }

    public void deletePost(Long postId) {
        postRepository.deleteByPostNo(postId);
    }

    public void updatePostVote(PostVoteUpdateRequestDto voteRequest) {
        postVoteRepository.savePostVote(PostVoteEntity.from(voteRequest));
    }

    public void deletePostVote(PostVoteUpdateRequestDto voteRequest) {
        postVoteRepository.deletePostVote(PostVote.from(voteRequest));
    }


    public List<GetReadPostsResponseBodyDto> getPostsContents(
            GetReadPostsRequestBodyDto requestBody) {
        return postRepository.getPostContents(requestBody);
    }


}
