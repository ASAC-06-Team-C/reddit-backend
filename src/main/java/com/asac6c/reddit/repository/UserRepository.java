package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.UpdateUserNickNameRequestDto;
import com.asac6c.reddit.dto.UpdateUserPwRequestDto;
import com.asac6c.reddit.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>();

    private Integer userId = 0;

    public void createUser(User user) {
        users.put(userId, User.from(userId++, user));
    }

    public User getUserById(Integer userNo) {
        return users.get(userNo);
    }

    public List<User> getAllUser() {
        return users.values().stream().toList();
    }

    public void updateUserNickName(Integer userNo, UpdateUserNickNameRequestDto updateUserNickNameRequestDto) {
        User user = users.get(userNo);
        user.setUserNickName(updateUserNickNameRequestDto.getUserNickName());
    }

    public void updateUserPassword(Integer userNo, UpdateUserPwRequestDto updateUserPwRequestDto) {
        User user = users.get(userNo);
        user.setUserPw(updateUserPwRequestDto.getUserPw());
    }

    public void deleteUser(Integer userNo) {
        users.remove(userNo);
    }
}
