package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.UserEntity;

// Mock
public interface IUserRepository {

    public void createUser(UserEntity user);

    public UserEntity getUserById(Long user_no);
}
