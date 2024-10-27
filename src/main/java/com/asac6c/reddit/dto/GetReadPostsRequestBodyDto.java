package com.asac6c.reddit.dto;

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

    String sort_type;
    Integer pages;
    Integer content_count;
}
