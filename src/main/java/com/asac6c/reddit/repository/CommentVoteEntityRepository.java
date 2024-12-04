package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.CommentVoteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentVoteEntityRepository extends JpaRepository<CommentVoteEntity, Long> {

    @Transactional
    CommentVoteEntity findByUserEntity_UserNo(Long userNo);
}
