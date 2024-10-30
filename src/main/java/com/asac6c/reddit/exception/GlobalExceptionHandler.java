//package com.asac6c.reddit.exception;
//
//import com.asac6c.reddit.dto.CommentExceptionResponseDto;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//
//  private ResponseEntity<CommentExceptionResponseDto> makeResponse(
//      ExceptionType exceptionType, String cause) {
//    return ResponseEntity
//        .status(exceptionType.getHttpStatus())
//        .body(CommentExceptionResponseDto.from(exceptionType, cause));
//  }
//
//  public ResponseEntity<CommentExceptionResponseDto>
//}
