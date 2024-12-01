package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CreateUserRequestDto;
import com.asac6c.reddit.entity.UserEntity;
import com.asac6c.reddit.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Date;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;

    public void userRegister(CreateUserRequestDto createUserRequestDto) {
        UserEntity user = UserEntity.from(createUserRequestDto);
        userRepository.save(user);
    }
}
