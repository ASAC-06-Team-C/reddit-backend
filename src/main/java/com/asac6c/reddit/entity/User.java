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
    @Getter
    String userNickName;
    final Date userRegisterDate;

    public static User from(Date userRegisterDate, CreateUserRequestDto createUserRequestDto) {
        return new User(
                null,
                createUserRequestDto.getUserId(),
                createUserRequestDto.getUserPw(),
                createUserRequestDto.getUserNickname(),
                userRegisterDate
        );
    }

    public static User from(Integer userNo, User user) {
        return new User(
                userNo,
                user.userId,
                user.userPw,
                user.userNickName,
                user.userRegisterDate
        );
    }
}
