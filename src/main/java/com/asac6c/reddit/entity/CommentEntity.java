package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.CommentRequestDTO.Create;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "COMMENT")
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
    @Setter
    String commentContent;

    @Setter
    Integer commentVoteCount;
    Long commentParent;
    Integer commentDepth;

    @Setter
    Boolean commentDeleted;

    public static CommentEntity create(PostEntity postEntity, UserEntity userEntity, Create createRequest) {
        CommentEntity comment = new CommentEntity();
        comment.postEntity = postEntity;
        comment.userEntity = userEntity;
        comment.commentContent = createRequest.getCommentContent();
        comment.commentParent = (long) createRequest.getCommentParent();
        comment.commentDepth = createRequest.getCommentDepth();
        comment.commentVoteCount = 0;
        comment.commentDeleted = false;
        return comment;
    }
}