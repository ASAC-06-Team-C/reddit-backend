package com.asac6c.reddit.exception;

import lombok.Getter;

@Getter
public class GetPostsCustomException extends RuntimeException {

    public GetPostsCustomException(Object object) {
        super((String) object);
    }

}
