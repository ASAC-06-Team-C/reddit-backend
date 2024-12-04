package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO.Vote;
import jakarta.persistence.*;
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
    private Long commentVoteNo;

    @ManyToOne
    @JoinColumn(name = "user_no", insertable = false, updatable = false)
    private UserEntity userEntity;

    @JoinColumn(name = "user_no")
    private Long userNo;

    @ManyToOne
    @JoinColumn(name = "comment_no", insertable = false, updatable = false)
    private CommentEntity comment;

    @JoinColumn(name = "comment_no")
    private Long commentNo;

    private VoteType commentVoteType;

    public static CommentVoteEntity from(Vote voteRequest) {
        CommentVoteEntity commentVoteEntity = new CommentVoteEntity();
        commentVoteEntity.userNo = (long) voteRequest.getUserNo();
        commentVoteEntity.commentNo = (long) voteRequest.getCommentNo();
        commentVoteEntity.commentVoteType = VoteType.valueOf(voteRequest.getCommentVoteType());

        return commentVoteEntity;
    }
}
