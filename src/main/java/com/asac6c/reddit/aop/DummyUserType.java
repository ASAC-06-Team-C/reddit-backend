package com.asac6c.reddit.aop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum DummyUserType {
    USER(1L, "user", "asdf", "user"),
    ADMIN(2L, "admin", "asdf", "admin");

    long userNo;
    String userId;
    String password;
    String userNickname;
}
