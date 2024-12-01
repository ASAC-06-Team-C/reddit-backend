package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.UpdateUserNickNameRequestDto;
import com.asac6c.reddit.dto.UpdateUserPwRequestDto;
import com.asac6c.reddit.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    UserEntity save(UserEntity user);

    @Transactional
    Optional<UserEntity> findByUserNo(Long userNo);

    @Transactional
    List<UserEntity> findAll();

    @Transactional
    void deleteByUserNo(Long userNo);
}
