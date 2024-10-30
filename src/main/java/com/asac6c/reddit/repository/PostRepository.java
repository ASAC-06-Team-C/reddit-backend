package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.GetReadPostsResponseBodyDto;
import com.asac6c.reddit.dto.GetReadPostsRequestBodyDto;
import com.asac6c.reddit.entity.Post;
import com.asac6c.reddit.entity.PostVote;
import com.asac6c.reddit.exception.DraftCustomException;
import com.asac6c.reddit.exception.DraftExceptionType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Repository
public class PostRepository {

  final static Map<Integer, Post> repositoryMap;
  final static Map<Integer, PostVote> postVotes;
  static Integer postId = 0;

  static {
    repositoryMap = new HashMap<>();
    postVotes = new HashMap<>();

    repositoryMap.put(0,
        new Post(0, 0, "DUMMY", "제목 0", "내용 0", 0, 0, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(1,
        new Post(1, 1, "DUMMY", "제목 1", "내용 1", 1, 1, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(2,
        new Post(2, 2, "DUMMY", "제목 2", "내용 2", 2, 2, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(3,
        new Post(3, 3, "DUMMY", "제목 3", "내용 3", 3, 3, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(4,
        new Post(4, 4, "DUMMY", "제목 4", "내용 4", 4, 4, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(5,
        new Post(5, 5, "DUMMY", "제목 5", "내용 5", 5, 5, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(6,
        new Post(6, 6, "DUMMY", "제목 6", "내용 6", 6, 6, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(7,
        new Post(7, 7, "DUMMY", "제목 7", "내용 7", 7, 7, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(8,
        new Post(8, 8, "DUMMY", "제목 8", "내용 8", 8, 8, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(9,
        new Post(9, 9, "DUMMY", "제목 9", "내용 9", 9, 9, false, new Date(System.currentTimeMillis())));
    repositoryMap.put(10,
        new Post(10, 10, "DUMMY", "제목 10", "내용 10", 10, 10, false,
            new Date(System.currentTimeMillis())));
  }

  public Post findPostById(Integer postId) {
    return repositoryMap.get(postId);
  }

  public Optional<Post> getDraftByPostId(Integer postNo) {
    return Optional.ofNullable(repositoryMap.get(postNo));
  }

  public Post createPost(Post.PostBuilder postBuilder) {
    Integer generatedNo = ++postId;
    Post createdPost = Post.completeInstanceForCreate(postBuilder, generatedNo);
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

  public void upsertPostDetail(Post entity) {
    Integer targetPostNo = entity.getPostNo();
    Post retrievedPost = Optional.ofNullable(repositoryMap.get(targetPostNo))
        .orElseThrow(
            () -> new DraftCustomException(DraftExceptionType.POST_NOT_EXIST, targetPostNo));
    Integer targetUserNo = retrievedPost.getUserNo();
    if (!entity.getUserNo().equals(targetUserNo)) {
      throw new DraftCustomException(DraftExceptionType.USER_HAVE_NO_AUTHORITY, targetUserNo);
    }
    repositoryMap.replace(targetPostNo, entity);
  }

  /**
   * @param request {String sort_type Integer pages Integer content_count}
   * @return PostsResponseBody
   */
  public List<GetReadPostsResponseBodyDto> getPostContents(GetReadPostsRequestBodyDto request) {

    List<GetReadPostsResponseBodyDto> responseBodies = new ArrayList<>();

    int startIndex = (request.getPages() - 1) * request.getContent_count();
    int endIndex = startIndex + request.getContent_count();

    for (int i = startIndex; i < endIndex; i++) {
      responseBodies.add(
          i, GetReadPostsResponseBodyDto.of(repositoryMap.get(i))
      );
    }
    return responseBodies;
  }

  public void deleteDraft(Integer postNo, Integer userNo) {
    Post targetPost = repositoryMap.get(postNo);
    if (!targetPost.getUserNo().equals(userNo)) {
      throw new IllegalArgumentException("잘못된 접근입니다.");
    }
    repositoryMap.remove(postNo);
  }
}
