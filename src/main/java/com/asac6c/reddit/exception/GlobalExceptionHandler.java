package com.asac6c.reddit.exception;

import com.asac6c.reddit.dto.common.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ResponseEntity<ExceptionResponseDto> makeResponse(GetPostsExceptionType exceptionType, String cause) {
        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(ExceptionResponseDto.from(exceptionType, cause));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleArgumentTypeMismatchException(MethodArgumentNotValidException e) {
        GetPostsExceptionType exceptionType = GetPostsExceptionType.ARGUMENT_TYPE_MISMATCH;
        String cause = String.format("파라미터 값 타입이 지정된 것과 다릅니다: %s", e.getFieldError().getField());
        return makeResponse(exceptionType, cause);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleNoMorePostsException(GetPostsCustomException e) {
        GetPostsExceptionType exceptionType = GetPostsExceptionType.NO_MORE_POSTS;
        String cause = "";
        return makeResponse(exceptionType, cause);
    }

//    @ExceptionHandler
//    public ResponseEntity<ExceptionResponseDto> handleRestMethodTypeMismatchException(GetPostsCustomException e) {
//        GetPostsExceptionType exceptionType = GetPostsExceptionType.REST_METHOD_TYPE_MISMATCH;
//        String cause = "";
//        return makeResponse(exceptionType, cause);
//    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleUndefinedException(Exception e) {
        GetPostsExceptionType exceptionType = GetPostsExceptionType.UNDEFINED_EXCEPTION;
        String cause = "정의되지 않은 오류 입니다.";
        return makeResponse(exceptionType, cause);
    }

}
