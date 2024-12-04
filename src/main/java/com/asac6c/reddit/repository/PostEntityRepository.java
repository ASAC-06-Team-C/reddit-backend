package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {

    @Transactional
    Optional<PostEntity> findByPostNo(Long postNo);

    @Transactional
    PostEntity save(PostEntity entity);

    @Transactional
    void deleteByPostNo(Long postNo);

    @Transactional
    List<PostEntity> findAllByUserEntityAndPostDraftIsTrue(UserEntity entity);

    @Transactional
    List<PostEntity> findAllByUserNoAndPostDraftIsTrue(Long userNo);
//    /**
//     * @param request {String sort_type Integer pages Integer content_count}
//     * @return PostsResponseBody
//     */
//
}
