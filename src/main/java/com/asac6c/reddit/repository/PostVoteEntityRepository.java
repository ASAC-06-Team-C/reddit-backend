package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.PostVoteEntity;
import com.asac6c.reddit.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PostVoteEntityRepository extends JpaRepository<PostVoteEntity, Long> {

    @Transactional
    Optional<PostVoteEntity> findByUserEntityAndPostEntity(UserEntity userNo, PostEntity postNo);

    @Transactional
    PostVoteEntity save(PostVoteEntity entity);

    @Transactional
    void delete(PostVoteEntity entity);
}
