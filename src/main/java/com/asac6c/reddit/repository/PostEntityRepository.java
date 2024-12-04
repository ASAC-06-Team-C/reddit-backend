package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


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

    @Transactional
    Page<PostEntity> findAllOrderByIdDesc(Pageable pageable);

}
