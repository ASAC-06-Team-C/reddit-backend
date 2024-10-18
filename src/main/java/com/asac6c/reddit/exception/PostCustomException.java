package com.asac6c.reddit.exception;

import lombok.Getter;

@Getter
public class PostCustomException extends RuntimeException {
  PostExceptionType exceptionType;

  public PostCustomException(PostExceptionType type, Object object) {
    super(type.getMessage() + object);
  }
}
