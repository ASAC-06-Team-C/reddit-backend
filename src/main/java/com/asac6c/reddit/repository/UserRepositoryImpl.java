package com.asac6c.reddit.repository;

import com.asac6c.reddit.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
//Mock
public class UserRepositoryImpl implements UserRepository{
    private final Map<Integer,UserEntity> users = new HashMap<>();

    private Integer userId = 0;

    @Override
    public void createUser(UserEntity user) {
        users.put(userId++, user);
    }

    @Override
    public UserEntity getUserById(Integer user_no) {
        return users.get(user_no);
    }
}
