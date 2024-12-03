package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    UserEntity save(UserEntity user);

    @Transactional
    Optional<UserEntity> findByUserNo(Long userNo);

    @Transactional
    Optional<UserEntity> findByUserId(String userId);

    @Transactional
    List<UserEntity> findAll();

    @Transactional
    void deleteByUserNo(Long userNo);
}
