package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.GetReadPostsRequestBodyDto;
import com.asac6c.reddit.dto.GetReadPostsResponseBodyDto;
import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.PostVoteEntity;
import com.asac6c.reddit.entity.VoteType;
import com.asac6c.reddit.exception.GetPostsCustomException;
import com.asac6c.reddit.exception.GetPostsExceptionType;
import com.asac6c.reddit.repository.PostEntityRepository;
import com.asac6c.reddit.repository.PostVoteEntityRepository;
import com.asac6c.reddit.repository.UserEntityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService {

    PostEntityRepository postEntityRepository;
    PostVoteEntityRepository postVoteRepository;
    UserEntityRepository userEntityRepository;

    @Transactional
    public PostGetResponseDto getPost(Long postId) {
        PostEntity post = postEntityRepository.findByPostNo(postId)
                .orElseThrow(() -> new GetPostsCustomException(
                        GetPostsExceptionType.UNDEFINED_EXCEPTION));
        String authorNickname = userEntityRepository.findByUserNo(post.getUserEntity().getUserNo())
                .orElseThrow(() -> new GetPostsCustomException(
                        GetPostsExceptionType.ARGUMENT_TYPE_MISMATCH))
                .getUserNickName();
        Optional<PostVoteEntity> postVote = postVoteRepository.findByUserEntityAndPostEntity(
                post.getUserEntity(),
                post);
        return PostGetResponseDto.from(post, authorNickname,
                postVote.isPresent() ? postVote.get().getPostVoteType() : VoteType.NONE);
    }


    @Transactional
    public PostCreateResponseDto createDraft(PostCreateRequestDto request) {
        PostEntity tempPost = PostEntity.forPostCreate(request);
        PostEntity generatedPost = postEntityRepository.save(tempPost);
        return PostCreateResponseDto.from(generatedPost);
    }

    @Transactional
    public PostCreateResponseDto createPostByDraft(DraftUpsertRequestDto request) {
        PostEntity requestPost = PostEntity.forSubmitDraft(request);
        postEntityRepository.save(requestPost);
        return PostCreateResponseDto.from(requestPost);
    }

    @Transactional
    public void deletePost(Long postId) {
        postEntityRepository.deleteByPostNo(postId);
    }

    @Transactional
    public void updatePostVote(PostVoteUpdateRequestDto voteRequest) {
        postVoteRepository.save(PostVoteEntity.from(voteRequest));
    }

    @Transactional
    public void deletePostVote(PostVoteUpdateRequestDto voteRequest) {
        postVoteRepository.delete(PostVoteEntity.from(voteRequest));
    }

    @Transactional
    public List<GetReadPostsResponseBodyDto> getPostsContents(
            GetReadPostsRequestBodyDto requestBody) {
        Pageable pageable = PageRequest.of(requestBody.getPages(), requestBody.getContent_count());

        Page<PostEntity> pages = postEntityRepository.findAllOrderByIdDesc(pageable);

        return pages.map(GetReadPostsResponseBodyDto::of)
                .stream().toList();
    }

}
