package com.asac6c.reddit.dto.common;

import com.asac6c.reddit.exception.GetPostsExceptionType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExceptionResponseDto {

    HttpStatus httpStatus;
    Integer statusCode;
    String message;
    String cause;

    public static ExceptionResponseDto from(GetPostsExceptionType exceptionType, String cause) {
        return new ExceptionResponseDto(
                exceptionType.getHttpStatus(),
                exceptionType.getHttpStatus().value(),
                exceptionType.getMessage(),
                cause
        );
    }
}