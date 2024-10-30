package com.asac6c.reddit.exception;

import lombok.Getter;

@Getter
public class CommentCustomException extends RuntimeException {

  public CommentCustomException(String message) {
    super(message);
  }
}
