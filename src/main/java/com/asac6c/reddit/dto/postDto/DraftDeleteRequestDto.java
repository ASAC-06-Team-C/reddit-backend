package com.asac6c.reddit.dto.postDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DraftDeleteRequestDto {

    Integer postNo;
    Integer userNo;
}
