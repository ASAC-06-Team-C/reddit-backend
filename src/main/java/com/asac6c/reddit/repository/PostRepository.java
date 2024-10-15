package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.postDto.DraftResponseDto;
import com.asac6c.reddit.dto.postDto.PostCreateDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.exception.PostCustomException;
import com.asac6c.reddit.exception.PostExceptionType;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;


@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@Repository
public class PostRepository {
  Map<Integer, Post> repositoryMap;

  public PostRepository() {
    repositoryMap = new HashMap<>();
  }

  private Integer postNumberGenerator() {
    Integer current  = repositoryMap.keySet()
        .stream()
        .max(Comparator.comparingInt(Integer::intValue))
        .orElse(0);
    return current;
  }

  public PostCreateResponseDto create(PostCreateDto request) {
    Integer generatedNo = postNumberGenerator();
    Post createdPost = Post.from(generatedNo, request);
    repositoryMap.put(generatedNo, createdPost);
    return new PostCreateResponseDto(generatedNo);
  }

  public PostResponseDto get(Integer postNo) {
    Optional<Post> maybePost = Optional.ofNullable(repositoryMap.get(postNo));
    return maybePost.map(PostResponseDto::from)
        .orElseThrow(() -> new PostCustomException(PostExceptionType.POST_NOT_EXIST, postNo));
  }

  public List<DraftResponseDto> getDraftListByUserId(Integer id) {
    return repositoryMap.values()
              .stream()
              .filter((each) -> each.getUserNo().equals(id) )
              .map(DraftResponseDto::from)
              .toList();
  }
}
