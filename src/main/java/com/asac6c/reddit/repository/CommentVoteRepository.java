package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.CommentVoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVoteEntity, Long> {


}
