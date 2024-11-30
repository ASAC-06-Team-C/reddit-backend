package com.asac6c.reddit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter

public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentNo;
    @ManyToOne
    @JoinColumn(name = "post_no")
    PostEntity postEntity;
    @ManyToOne
    @JoinColumn(name = "user_no")
    UserEntity userEntity;
    @Lob
    String commentContent;
    Integer commentVoteCount;
    @ManyToOne
    @JoinColumn(name = "comment_parent")
    CommentEntity commentParent;
    Integer commentDepth;
    boolean commentDeleted;

//    public static CommentEntity from(CommentRequestDTO.Create createRequest, int commentNo) {
//        return new CommentEntity(
//                commentNo,
//                createRequest.getPostNo(),
//                createRequest.getUserNo(),
//                createRequest.getCommentContent(),
//                0,
//                createRequest.getCommentMother(),
//                createRequest.getCommentDepth(),
//                LocalDateTime.now(),
//                false
//        );
//    }
}