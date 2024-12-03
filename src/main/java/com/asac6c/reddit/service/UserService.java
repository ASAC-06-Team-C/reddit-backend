package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CreateUserRequestDto;
import com.asac6c.reddit.dto.LoginRequestDto;
import com.asac6c.reddit.dto.LoginResponseDto;
import com.asac6c.reddit.entity.UserEntity;
import com.asac6c.reddit.repository.UserEntityRepository;
import jakarta.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Transactional
    public LoginResponseDto userLogin(LoginRequestDto request) {
        UserEntity user = userEntityRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 user가 존재하지 않습니다."));
        if (!request.getUserPw().equals(user.getUserPw())) {
            throw new RuntimeException("아이디 비밀번호가 일치하지 않음.");
        }
        return LoginResponseDto.from(user);
    }
}
