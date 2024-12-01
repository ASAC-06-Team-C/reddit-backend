package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "POST")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postNo;

    @ManyToOne
    @JoinColumn(name = "user_no", updatable = false)
    @Setter
    UserEntity userEntity;
    String communityName;
    String postTitle;
    @Lob
    String postContent;
    Integer postVoteCount;
    Integer postCommentCount;
    Boolean postDraft;

    public static PostEntity forPostCreate(PostCreateRequestDto request) {
        return new PostEntity(
                null,
                null,
                null,
                request.getPostTitle(),
                request.getPostContent(),
                0,
                0,
                false
        );
    }

    public static PostEntity forDraftCreate(PostCreateRequestDto request) {
        return new PostEntity(
                null,
                null,
                null,
                request.getPostTitle(),
                request.getPostContent(),
                0,
                0,
                true
        );
    }

    public static PostEntity forDraftUpdate(DraftUpsertRequestDto request) {
        return new PostEntity(
                request.getPostNo(),
                null,
                null,
                request.getPostTitle(),
                request.getPostContent(),
                0,
                0,
                true
        );
    }

    public static PostEntity forSubmitDraft(DraftUpsertRequestDto request) {
        return new PostEntity(
                request.getPostNo(),
                null,
                null,
                request.getPostTitle(),
                request.getPostContent(),
                0,
                0,
                false
        );
    }
}
