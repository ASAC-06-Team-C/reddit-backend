package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.exception.PostCustomException;
import com.asac6c.reddit.exception.PostExceptionType;
import com.asac6c.reddit.repository.PostRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class PostService {
  PostRepository postRepository;

  public PostCreateResponseDto createPost(PostCreateRequestDto request) {
    return PostCreateResponseDto.from(postRepository.createPost(request)) ;
  }

  public PostResponseDto getDraftDetailByUserId(Integer id) {
    return postRepository.getDraftByPostId(id).map(PostResponseDto::from)
        .orElseThrow(() -> new PostCustomException(PostExceptionType.POST_NOT_EXIST, id));
  }

  public List<DraftSummaryResponseDto> getDraftListByUserId(Integer userId) {
    return postRepository.getDraftListByUserId(userId).stream()
        .map(DraftSummaryResponseDto::from)
        .toList();
  }
}
