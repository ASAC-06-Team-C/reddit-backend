package com.asac6c.reddit.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ExceptionType {
  COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 댓글을 찾을 수 없습니다."),
  POST_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 게시물을 찾을 수 없습니다."),
  INVALID_SORT_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 정렬 타입입니다."),
  INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "유효하지 않은 페이지 번호입니다."),
  COMMENT_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 생성에 실패했습니다."),
  COMMENT_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 수정에 실패했습니다."),
  COMMENT_DELETION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 삭제에 실패했습니다."),
  INVALID_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "유효하지 않은 댓글 내용입니다."),
  UNAUTHORIZED_COMMENT_MODIFICATION(HttpStatus.FORBIDDEN, "댓글 수정 권한이 없습니다."),
  UNAUTHORIZED_COMMENT_DELETION(HttpStatus.FORBIDDEN, "댓글 삭제 권한이 없습니다."),
  INVALID_VOTE_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 투표 타입입니다."),
  DUPLICATE_VOTE(HttpStatus.CONFLICT, "이미 투표한 댓글입니다."),
  COMMENT_VOTE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 투표에 실패했습니다.");

  HttpStatus httpStatus;
  String message;
}