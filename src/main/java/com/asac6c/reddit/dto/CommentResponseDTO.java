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

    private final int userNo;
    private final String userProfile;
    private final String userNickname;
    private final int commentNo;
    private final int commentVoteCount;
    private final String commentContent;
    private final LocalDateTime commentWriteDate;
    private final int commentDepth;
    private final int commentMother;
    private final int postNo;
    private final boolean commentDeleted;

//    public static CommentResponseDTO from(CommentEntity commentEntity, String userProfile,
//            String userNickname) {
//        return new CommentResponseDTO(
//                commentEntity.getUserNo(),
//                userProfile,
//                userNickname,
//                commentEntity.getCommentNo(),
//                commentEntity.getCommentVoteCount(),
//                commentEntity.getCommentContent(),
//                commentEntity.getCommentWriteDate(),
//                commentEntity.getCommentDepth(),
//                commentEntity.getCommentMother(),
//                commentEntity.getPostNo(),
//                commentEntity.isCommentDeleted()
//        );
//    }
}
