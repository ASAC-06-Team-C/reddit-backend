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
@Table(name = "POST")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    UserEntity userEntity;
    String communityName;
    String postTitle;
    @Lob
    String postContent;
    Integer postVoteCount;
    Integer postCommentCount;
    Boolean postDraft;


}
