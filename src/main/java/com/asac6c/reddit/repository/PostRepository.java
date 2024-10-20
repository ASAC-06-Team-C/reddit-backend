package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.entity.Post;
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
  static Map<Integer, Post> repositoryMap;

  static {
    repositoryMap = new HashMap<Integer, Post>();
  }

  private Integer postNumberGenerator() {
    Integer current  = repositoryMap.keySet()
        .stream()
        .max(Comparator.comparingInt(Integer::intValue))
        .orElse(0);
    return current + 1;
  }

  public Post createPost(PostCreateRequestDto request) {
    Integer generatedNo = postNumberGenerator();
    Post createdPost = Post.from(generatedNo, request);
    repositoryMap.put(generatedNo, createdPost);
    return createdPost;
  }

  public Optional<Post> getDraftByPostId(Integer postNo) {
    return Optional.ofNullable(repositoryMap.get(postNo));
  }

  public List<Post> getDraftListByUserId(Integer id) {
    return repositoryMap.values()
              .stream()
              .filter((post) -> post.getUserNo().equals(id))
              .toList();
  }
}
