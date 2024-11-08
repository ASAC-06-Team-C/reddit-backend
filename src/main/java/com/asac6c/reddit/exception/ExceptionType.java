package com.asac6c.reddit.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ExceptionType {
    REQUEST_NOT_VALID(HttpStatus.BAD_REQUEST, "요청 데이터가 유효성 검사를 통과하지 못했습니다."),
    POST_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당하는 post의 id가 존재하지 않습니다."),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 문제가 발생했습니다.");

    HttpStatus httpStatus;
    String message;
}
