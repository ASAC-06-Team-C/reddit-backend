package com.asac6c.reddit.exception;

import lombok.Getter;

@Getter
public class PostCustomException extends RuntimeException {
    public PostCustomException(Object object) {
        super((String) object);
    }
}
