package com.asac6c.reddit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CommentGlobalExceptionHandler {
  
  @ExceptionHandler(CommentCustomException.class)
  public ResponseEntity<Object> globalExceptionHandler(CommentCustomException commentCustomException) {
    log.warn(commentCustomException.getMessage());
    commentCustomException.printStackTrace();
    return new ResponseEntity<>(new ErrorResponse(commentCustomException.getMessage()), commentCustomException.getHttpStatus());
  }
  
  @Getter
  @RequiredArgsConstructor
  public static class ErrorResponse {
    private final String message;
  }
}
