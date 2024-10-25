package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.User;

// Mock
public interface IUserRepository {
    public void createUser(User user);

    public User getUserById(Integer user_no);
}
