package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.PostGetResponseDto;
import com.asac6c.reddit.dto.GetReadPostsResponseBodyDto;
import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import com.asac6c.reddit.dto.GetReadPostsRequestBodyDto;
import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.entity.Post;
import com.asac6c.reddit.entity.PostVote;
import com.asac6c.reddit.entity.PostVoteType;
import com.asac6c.reddit.repository.PostRepository;
import com.asac6c.reddit.repository.PostVoteRepository;
import com.asac6c.reddit.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService {

    PostRepository postRepository;
    PostVoteRepository postVoteRepository;
    UserRepository userRepository;

    public PostGetResponseDto getPost(Integer postId) {
        Post post = postRepository.findPostById(postId);
        String authorNickname = userRepository.getUserById(post.getUserNo()).getUserNickName();
        Optional<PostVote> postVote = postVoteRepository.findByUserNoAndPostNo(post.getUserNo(), post.getPostNo());
        return PostGetResponseDto.from(post, authorNickname, postVote.isPresent() ? postVote.get().getPostVoteType() : PostVoteType.NONE);
    }


    public PostCreateResponseDto createDraft(PostCreateRequestDto request) {
        Post.PostBuilder tempPost = Post.configureInstanceForCreate(request);
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

    public void updatePostVote(PostVoteUpdateRequestDto voteRequest) {
        postVoteRepository.savePostVote(PostVote.from(voteRequest));
    }

    public void deletePostVote(PostVoteUpdateRequestDto voteRequest) {
        postVoteRepository.deletePostVote(PostVote.from(voteRequest));
    }


    public List<GetReadPostsResponseBodyDto> getPostsContents(
            GetReadPostsRequestBodyDto requestBody) {
        return postRepository.getPostContents(requestBody);
    }


}
