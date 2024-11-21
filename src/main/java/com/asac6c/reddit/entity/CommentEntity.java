package com.asac6c.reddit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
    String commentContent;
    Integer commentVoteCount;

    @ManyToOne
    @JoinColumn(name = "comment_parent")
    CommentEntity commentParent;
    Integer commentDepth;
    Boolean commentDeleted;

}