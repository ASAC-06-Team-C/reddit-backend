package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.CommentEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentResponseDTO {

    private final Long userNo;
    private final String userProfile;
    private final String userNickname;
    private final Long commentNo;
    private final int commentVoteCount;
    private final String commentContent;
    private final LocalDateTime commentWriteDate;
    private final int commentDepth;
    private final Long commentParent;
    private final Long postNo;
    private final boolean commentDeleted;

    public static CommentResponseDTO from(CommentEntity commentEntity, String userProfile,
            String userNickname) {
        return new CommentResponseDTO(
                commentEntity.getUserEntity().getUserNo(),
                userProfile,
                userNickname,
                commentEntity.getCommentNo(),
                commentEntity.getCommentVoteCount(),
                commentEntity.getCommentContent(),
                commentEntity.getCreatedAt(),
                commentEntity.getCommentDepth(),
                commentEntity.getCommentParent().getCommentNo(),
                commentEntity.getPostEntity().getPostNo(),
                commentEntity.isCommentDeleted()
        );
    }
}
