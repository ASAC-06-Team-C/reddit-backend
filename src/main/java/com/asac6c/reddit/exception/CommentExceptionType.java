package com.asac6c.reddit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentExceptionType {
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    COMMENT_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 생성에 실패했습니다."),
    COMMENT_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 수정에 실패했습니다."),
    COMMENT_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 삭제에 실패했습니다."),
    COMMENT_VOTE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 투표에 실패했습니다."),
    COMMENT_UPDATE_PERMISSION(HttpStatus.FORBIDDEN, "댓글 수정 권한이 없습니다."),
    COMMENT_DELETION_PERMISSION(HttpStatus.FORBIDDEN, "댓글 삭제 권한이 없습니다."),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "잘못된 요청 값입니다.");
    //  VALID_SORT_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 정렬 타입입니다."),
    //  VALID_VOTE_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 투표 타입입니다.");
    //  VALID_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "유효하지 않은 댓글 내용입니다."),
    //  VALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "유효하지 않은 페이지 번호입니다."),
    //  DUPLICATE_VOTE(HttpStatus.CONFLICT, "이미 투표한 댓글입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}