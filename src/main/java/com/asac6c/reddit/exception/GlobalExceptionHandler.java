package com.asac6c.reddit.exception;

import com.asac6c.reddit.dto.common.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ResponseEntity<ExceptionResponseDto> makeResponse(DraftCustomException e) {
        return ResponseEntity
                .status(e.getExceptionType().getHttpStatus())
                .body(ExceptionResponseDto.from(e));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handlePostException(DraftCustomException e) {
        log.warn("[WARN] : " + e.getMessage(), e);
        return makeResponse(e);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handlePostException(RuntimeException e) {
        log.warn("[WARN] : " + e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponseDto.from(e));
    }
    // 전자 : Exception을 이용해 response 생성
    // 후자 : Exception을 보고 이에 맞는 Exception Type을 매핑 후 response 생성

    private ResponseEntity<ExceptionResponseDto> makeResponse(GetPostsExceptionType exceptionType,
            String cause) {
        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(ExceptionResponseDto.from(exceptionType, cause));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleArgumentTypeMismatchException(
            MethodArgumentNotValidException e) {
        GetPostsExceptionType exceptionType = GetPostsExceptionType.ARGUMENT_TYPE_MISMATCH;
        String cause = String.format("파라미터 값 타입이 지정된 것과 다릅니다: %s", e.getFieldError().getField());
        return makeResponse(exceptionType, cause);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleNoMorePostsException(
            GetPostsCustomException e) {
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

//    @ExceptionHandler
//    public ResponseEntity<ExceptionResponseDto> handleUndefinedException(Exception e) {
//        log.error("[ERROR] : " + e.getMessage(), e);
//        GetPostsExceptionType exceptionType = GetPostsExceptionType.UNDEFINED_EXCEPTION;
//        String cause = "정의되지 않은 오류 입니다.";
//        return makeResponse(exceptionType, cause);
//    }

    private ResponseEntity<ExceptionResponseDto> makeResponse(ExceptionType exceptionType,
            String cause) {
        return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(ExceptionResponseDto.from(exceptionType, cause));
    }

//    @ExceptionHandler
//    public ResponseEntity<ExceptionResponseDto> handleValidationException(MethodArgumentNotValidException e) {
//        ExceptionType exceptionType = ExceptionType.REQUEST_NOT_VALID;
//        String cause = String.format("%s 필드에서 문제 발생", e.getFieldError().getField());
//        return makeResponse(exceptionType, cause);
//    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handlePostException(PostCustomException e) {
        ExceptionType exceptionType = ExceptionType.POST_NOT_EXIST;
        String cause = String.format("'%s' id가 존재하지 않습니다.", e.getMessage());
        return makeResponse(exceptionType, cause);
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleUnknownException(Exception e) {
        log.error(e.getMessage(), e + "이상한에러야");
        ExceptionType exceptionType = ExceptionType.UNKNOWN_ERROR;
        String cause = String.format("%s", e.getCause());
        return makeResponse(exceptionType, cause);
    }
}
