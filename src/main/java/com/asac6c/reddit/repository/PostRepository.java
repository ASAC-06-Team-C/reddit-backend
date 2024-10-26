package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.Post;

import java.util.Comparator;

import com.asac6c.reddit.entity.PostVote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Repository
public class PostRepository {

  final static Map<Integer, Post> repositoryMap;
  final static Map<Integer, PostVote> postVotes;
  static Integer postId = 0;

  static {
    repositoryMap = new HashMap<Integer, Post>();
    postVotes = new HashMap<Integer, PostVote>();
  }

  public Post findPostById(Integer postId) {
    return repositoryMap.get(postId);
  }

  public Optional<Post> getDraftByPostId(Integer postNo) {
    return Optional.ofNullable(repositoryMap.get(postNo));
  }

  public Post createPost(Post.PostBuilder postBuilder) {
    Integer generatedNo = ++postId;
    Post createdPost = postBuilder.postNo(generatedNo).build();
    repositoryMap.put(generatedNo, createdPost);
    return createdPost;
  }

  public void deletePostById(Integer postId) {
    repositoryMap.remove(postId);
  }

  public Integer savePostVote(PostVote postVoteEntity) {
    PostVote postVote = postVotes.put(postId++, postVoteEntity);
    return postVote.getPostVoteNo();
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
