package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.UserEntity;

// Mock
public interface UserRepository {
    public void createUser(UserEntity user);
    public UserEntity getUserById(Integer user_no);
}
