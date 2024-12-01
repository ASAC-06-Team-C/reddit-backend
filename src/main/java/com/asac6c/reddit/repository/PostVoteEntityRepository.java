package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.PostVoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PostVoteEntityRepository extends JpaRepository<PostVoteEntity, Long> {

    @Transactional
    Optional<PostVoteEntity> findByUserNoAndPostNo(Long userNo, Long postNo);

    @Transactional
    PostVoteEntity save(PostVoteEntity entity);

    @Transactional
    void deletePostVote(PostVoteEntity entity);
}
