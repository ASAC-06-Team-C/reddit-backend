package com.asac6c.reddit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

@Table(name = "POST_VOTE")
public class PostVoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postVoteNo;
    @ManyToOne
    @JoinColumn(name = "user_no")
    UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "post_no")
    PostEntity postEntity;
    VoteType postVoteType;

//    public static PostVoteEntity from(Long postVoteNo, PostVoteCreateRequestDto requestDto) {
//        return new PostVoteEntity(
//                postVoteNo,
//                requestDto.getUserNo(),
//                requestDto.getPostNo(),
//                requestDto.getPostVoteType()
//        );
//    }

    ;
}
