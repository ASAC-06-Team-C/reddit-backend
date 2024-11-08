package com.asac6c.reddit.exception;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum DraftExceptionType {
    POST_NOT_EXIST(HttpStatus.NOT_FOUND, "해당하는 post의 id가 존재하지 않습니다. post_no : "),
    USER_HAVE_NO_AUTHORITY(HttpStatus.UNAUTHORIZED, "해당하는 user에게는 이 post의 접근 권한이 없습니다. user_no : ");
    HttpStatus httpStatus;
    String message;
}
