package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.entity.Post;
import com.asac6c.reddit.exception.PostCustomException;
import com.asac6c.reddit.exception.PostExceptionType;
import com.asac6c.reddit.repository.PostRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DraftService {

  PostRepository postRepository;

  public PostCreateResponseDto createDraft(PostCreateRequestDto request) {
    Post.PostBuilder tempPost = Post.instanceForCreate(request);
    Post generatedPost = postRepository.createPost(tempPost);
    return PostCreateResponseDto.from(generatedPost);
  }

  public PostResponseDto getDraftDetailByPostNo(Integer id) {
    return postRepository.getDraftByPostId(id).map(PostResponseDto::from)
        .orElseThrow(() -> new PostCustomException(PostExceptionType.POST_NOT_EXIST, id));
  }

  public List<DraftSummaryResponseDto> getDraftListByUserId(Integer userId) {
    return postRepository.getDraftListByUserId(userId).stream()
        .map(DraftSummaryResponseDto::from)
        .toList();
  }

  public DraftSummaryResponseDto upsertDraftDetail(DraftUpsertRequestDto request) {
    Post postForUpsert = Post.instanceForUpsert(request);
    return DraftSummaryResponseDto.from(postRepository.upsertPostDetail(postForUpsert));
  }
}
