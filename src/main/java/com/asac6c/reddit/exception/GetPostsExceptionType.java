package com.asac6c.reddit.exception;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum GetPostsExceptionType {

    NO_MORE_POSTS(HttpStatus.NOT_FOUND, "더 이상 조회할 포스트가 없습니다."),
    ARGUMENT_TYPE_MISMATCH(HttpStatus.NOT_FOUND, "변수 타입이 지정된 것과 같지 않습니다.");
//    REST_METHOD_TYPE_MISMATCH(HttpStatus.NOT_FOUND, "API 호출 방식이 잘못 되었습니다. GET 방식으로 호출 해 주세요"); // 이건 Spring 에서 잡아주지 않나?

    //    String type;
    HttpStatus httpStatus;
    String message;

}
