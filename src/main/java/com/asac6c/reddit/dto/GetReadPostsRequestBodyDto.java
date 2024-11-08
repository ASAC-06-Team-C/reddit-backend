package com.asac6c.reddit.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Get requestParam 호출 시 직렬화 - 역직렬화 사용 불가로 인한 Snake Case 특별 허용.
 */

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@ToString
public class GetReadPostsRequestBodyDto {

    @NotBlank
    @NotNull
    @NotEmpty
    String sort_type;
    @Min(0)
    @NotNull
    Integer pages;
    @Min(0)
    @NotNull
    Integer content_count;
}
