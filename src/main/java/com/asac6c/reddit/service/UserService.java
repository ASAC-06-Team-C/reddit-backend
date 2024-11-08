package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CreateUserRequestDto;
import com.asac6c.reddit.entity.User;
import com.asac6c.reddit.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    public void userRegister(CreateUserRequestDto createUserRequestDto) {
        User user = User.from(new Date(), createUserRequestDto);
        userRepository.createUser(user);
    }
}
