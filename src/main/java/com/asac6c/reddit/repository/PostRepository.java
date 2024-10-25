package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.Post;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Repository
public class PostRepository {

  static Map<Integer, Post> repositoryMap;

  static {
    repositoryMap = new HashMap<>();
  }

  private Integer postNumberGenerator() {
    Integer current = repositoryMap.keySet()
        .stream()
        .max(Comparator.comparingInt(Integer::intValue))
        .orElse(0);
    return current + 1;
  }

  public Post createPost(Post.PostBuilder postBuilder) {
    Integer generatedNo = postNumberGenerator();
    Post createdPost = postBuilder.postNo(generatedNo).build();
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

  public Post upsertPostDetail(Post entity) {
    Post retrievedPost = Optional.ofNullable(repositoryMap.get(entity.getPostNo()))
        .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
    Integer targetPostNo = entity.getPostNo();
    if (!entity.getUserNo().equals(retrievedPost.getUserNo())) {
      throw new IllegalArgumentException("이 사용자에게는 권한이 없습니다.");
    }
    repositoryMap.replace(targetPostNo, entity);
    return entity;
  }
}
