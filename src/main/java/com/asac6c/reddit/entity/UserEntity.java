package com.asac6c.reddit.entity;

import com.asac6c.reddit.aop.DummyUserType;
import com.asac6c.reddit.dto.CreateUserRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "USER")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userNo;

    @Column(updatable = false, nullable = false)
    String userId;

    @Setter
    @Column(nullable = false)
    String userPw;

    @Setter
    @Column(nullable = false)
    String userNickName;


    public static UserEntity from(CreateUserRequestDto request) {
        return new UserEntity(
                null,
                request.getUserId(),
                request.getUserPw(),
                request.getUserNickname()
        );
    }

    public static UserEntity from(DummyUserType type) {
        return new UserEntity(
                type.getUserNo(),
                type.getUserId(),
                type.getPassword(),
                type.getUserNickname()
        );
    }
}