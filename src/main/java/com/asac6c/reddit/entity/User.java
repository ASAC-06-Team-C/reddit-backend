package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CreateUserRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    final Integer userNo;
    final String userId;
    @Setter
    String userPw;
    @Setter
    String userNickName;
    final Date userRegisterDate;

    public static User from(Integer userNo, Date userRegisterDate, CreateUserRequestDto createUserRequestDto) {
        return new User(
                userNo,
                createUserRequestDto.getUserId(),
                createUserRequestDto.getUserPw(),
                createUserRequestDto.getUserNickName(),
                userRegisterDate
        );
    }
}
