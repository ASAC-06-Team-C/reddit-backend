package com.asac6c.reddit.dto;

import com.asac6c.reddit.exception.CommentExceptionType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentExceptionResponseDto {
  
  HttpStatus httpStatus;
  Integer statusCode;
  String message;
  String cause;
  
  public static CommentExceptionResponseDto from(CommentExceptionType exceptionType,
                                                 String cause) {
    return new CommentExceptionResponseDto(
            exceptionType.getHttpStatus(),
            exceptionType.getHttpStatus().value(),
            exceptionType.getMessage(),
            cause
    );
  }
}
