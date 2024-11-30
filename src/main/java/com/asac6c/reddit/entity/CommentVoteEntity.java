package com.asac6c.reddit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "COMMENT_VOTE")
public class CommentVoteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentVoteNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "comment_no")
    CommentEntity commentNo;

    VoteType commentVoteType;
//    public static CommentVoteEntity from(CommentRequestDTO.Vote voteRequest, int commentVoteNo) {
//        return new CommentVoteEntity(
//                commentVoteNo,
//                voteRequest.getUserNo(),
//                voteRequest.getCommentNo()
////        voteRequest.getCommentVoteType()
//        );
//    }

//    public void changeVoteType(String commentVoteType) {
//        this.commentVoteType = commentVoteType;
//    }
}
