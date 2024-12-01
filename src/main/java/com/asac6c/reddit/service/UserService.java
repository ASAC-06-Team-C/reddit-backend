package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CreateUserRequestDto;
import com.asac6c.reddit.entity.UserEntity;
import com.asac6c.reddit.repository.UserEntityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UserService {

    UserEntityRepository userEntityRepository;

    @Transactional
    public void userRegister(CreateUserRequestDto createUserRequestDto) {
        UserEntity user = UserEntity.from(createUserRequestDto);
        userEntityRepository.save(user);
    }
}
