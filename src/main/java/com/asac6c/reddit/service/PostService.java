package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.postDto.DraftResponseDto;
import com.asac6c.reddit.dto.postDto.PostCreateDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
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

  public PostCreateResponseDto create(PostCreateDto request) {
    return postRepository.create(request);
  }

  public PostResponseDto getDraft(Integer id) {
    return postRepository.get(id);
  }

  public List<DraftResponseDto> getDraftListByUserId(Integer userId) {
    return postRepository.getDraftListByUserId(userId);
  }

}
