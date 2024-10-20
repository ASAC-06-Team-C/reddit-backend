package com.asac6c.reddit.exception;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public enum PostExceptionType {
    POST_NOT_EXIST("D01", "해당하는 post의 id가 존재하지 않습니다. : ", HttpStatus.NOT_FOUND);

    String type;
    String message;
    HttpStatus status;
}
