package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
//Mock
public class UserRepositoryImpl implements UserRepository {
    private final Map<Integer, User> users = new HashMap<>();

    private Integer userId = 0;

    @Override
    public void createUser(User user) {
        users.put(userId++, user);
    }

    @Override
    public User getUserById(Integer user_no) {
        return users.get(user_no);
    }
}
