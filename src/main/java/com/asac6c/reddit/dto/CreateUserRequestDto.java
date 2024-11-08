package com.asac6c.reddit.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateUserRequestDto {
    @NotEmpty
    String userId;
    @NotEmpty
    String userPw;
    @NotEmpty
    String userNickname;
}
