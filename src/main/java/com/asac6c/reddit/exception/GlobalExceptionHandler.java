package com.asac6c.reddit.exception;

import com.asac6c.reddit.dto.common.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

  @ExceptionHandler
  public ResponseEntity<ExceptionResponseDto> handlePostException(Exception e) {
    log.error("[ERROR] : " + e.getMessage(), e);
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ExceptionResponseDto.from(e));
  }
}