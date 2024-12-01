package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Transactional
    Optional<PostEntity> findByPostNo(Long postNo);

    @Transactional
    PostEntity save(PostEntity entity);

    @Transactional
    void deleteByPostNo(Long postNo);

    @Transactional
    List<PostEntity> findAllByUserEntityAndPostDraftIsTrue(UserEntity entity);

//    /**
//     * @param request {String sort_type Integer pages Integer content_count}
//     * @return PostsResponseBody
//     */
//    public List<GetReadPostsResponseBodyDto> getPostContents(GetReadPostsRequestBodyDto request) {
//
//        List<GetReadPostsResponseBodyDto> responseBodies = new ArrayList<>();
//
//        int startIndex = (request.getPages() - 1) * request.getContent_count();
//        int endIndex = startIndex + request.getContent_count();
//
//        for (int i = startIndex; i < endIndex; i++) {
//            responseBodies.add(
//                    i, GetReadPostsResponseBodyDto.of(repositoryMap.get(i))
//            );
//        }
//        return responseBodies;
//    }
}
