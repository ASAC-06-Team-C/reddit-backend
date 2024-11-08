package com.asac6c.reddit.exception;

import lombok.Getter;

@Getter
public class DraftCustomException extends RuntimeException {

    DraftExceptionType exceptionType;

    public DraftCustomException(DraftExceptionType type, Object object) {
        super(type.getMessage() + object);
    }
}
