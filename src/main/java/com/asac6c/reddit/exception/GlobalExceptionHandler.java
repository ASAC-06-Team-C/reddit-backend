package com.asac6c.reddit.exception;

import com.asac6c.reddit.dto.common.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ResponseEntity<ExceptionResponseDto> makeResponse(ExceptionType exceptionType, String cause) {
        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(ExceptionResponseDto.from(exceptionType, cause));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleValidationException(MethodArgumentNotValidException e) {
        ExceptionType exceptionType = ExceptionType.REQUEST_NOT_VALID;
        String cause = String.format("%s 필드에서 문제 발생", e.getFieldError().getField());
        return makeResponse(exceptionType, cause);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handlePostException(PostCustomException e) {
        ExceptionType exceptionType = ExceptionType.POST_NOT_EXIST;
        String cause = String.format("'%s' id가 존재하지 않습니다.", e.getMessage());
        return makeResponse(exceptionType, cause);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleUnknownException(Exception e) {
        ExceptionType exceptionType = ExceptionType.UNKNOWN_ERROR;
        String cause = String.format("%s", e.getCause());
        return makeResponse(exceptionType, cause);
    }
}
