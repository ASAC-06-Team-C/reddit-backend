package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.UserEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginResponseDto {

    Long userNo;
    String userNickname;

    public static LoginResponseDto from(UserEntity user) {
        return new LoginResponseDto(
                user.getUserNo(),
                user.getUserNickName()
        );
    }
}
