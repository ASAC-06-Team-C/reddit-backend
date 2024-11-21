package com.asac6c.reddit.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

// Jackson은 reflection을 이용해 객체 필드에 직접 값 할당 가능
// 즉, 생성자나 Setter가 없어도 Getter같이 필드를 지정해주기만 해도 역직렬화 가능
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostVoteUpdateRequestDto {

    Integer postVoteNo;
    @NotNull
    Integer postNo;
    @NotNull
    Integer userNo;
    @NotNull
    PostVoteType postVoteType;
}
