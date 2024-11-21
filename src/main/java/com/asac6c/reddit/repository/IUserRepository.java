package com.asac6c.reddit.repository;

// Mock
public interface IUserRepository {

    public void createUser(User user);

    public User getUserById(Integer user_no);
}
