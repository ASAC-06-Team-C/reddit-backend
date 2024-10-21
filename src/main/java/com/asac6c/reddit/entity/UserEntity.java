package com.asac6c.reddit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//Mock
public class UserEntity {
    Integer user_no;
    String user_id;
    String user_pw;
    String user_nickname;
    LocalDateTime user_register_date;
}
