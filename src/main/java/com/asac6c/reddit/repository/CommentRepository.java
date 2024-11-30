package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.CommentRequestDTO.Delete;
import com.asac6c.reddit.dto.CommentRequestDTO.Update;
import com.asac6c.reddit.dto.CommentRequestDTO.Vote;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.entity.CommentVoteEntity;
import com.asac6c.reddit.exception.CommentCustomException;
import com.asac6c.reddit.exception.CommentExceptionType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {

    private final Map<Long, CommentEntity> commentMap = new HashMap<>();
    private final Map<Long, CommentVoteEntity> commentVoteMap = new HashMap<>();

    private Long commentNo = 1L;
    private Long commentVoteNo = 1L;

    public List<CommentEntity> getComment(int postNo) {
        return commentMap.values().stream()
                .filter(comment -> comment.getPostEntity().getPostNo() == postNo)
                .toList();
    }

    public CommentEntity createComment(/*Create createRequest*/) {
        CommentEntity newComment = /*CommentEntity.from(createRequest, commentNo);*/ null;

        commentMap.put(commentNo++, newComment);
        return newComment;
    }

    public void updateComment(Update updateRequest) {
        CommentEntity comment = commentMap.get(updateRequest.getCommentNo());

        // 댓글 수정 권한
        if (comment.getUserEntity().getUserNo() != updateRequest.getUserNo()) {
            throw new CommentCustomException(CommentExceptionType.COMMENT_UPDATE_PERMISSION);
        }

//        comment.setCommentContent(updateRequest.getCommentContent());
    }

    // 삭제가 아닌 commentDeleted 만 바꿔준다?
    public void deleteComment(Delete deleteRequest) {
        CommentEntity comment = commentMap.get(deleteRequest.getCommentNo());

        // 댓글 삭제 권한
        if (comment.getUserEntity().getUserNo() != deleteRequest.getUserNo()) {
            throw new CommentCustomException(CommentExceptionType.COMMENT_DELETION_PERMISSION);
        }

//        comment.setCommentDeleted(true);
//    commentMap.remove(comment_no);
    }

    public void voteComment(Vote voteRequest) {
        // 유저 번호에 대한 vote 내역 가져오기
        CommentVoteEntity voteValid = /*commentVoteMap.values().stream()
                .filter(vote -> vote.getUserEntity() == voteRequest.getUserNo()
                        && vote.getCommentNo() == voteRequest.getCommentNo())
                .findFirst()
                .orElse(null);*/ null;

        // 투표를 하지 않았다면,
        if (voteValid == null) {
            CommentVoteEntity newVote = /*CommentVoteEntity.from(voteRequest, commentVoteNo);*/ null;
//            newVote.changeVoteType(voteRequest.getCommentVoteType());
            commentVoteMap.put(commentVoteNo++, newVote);
        }
        // 투표를 했다면
        else {
            CommentVoteEntity updateVote = /*CommentVoteEntity.from(voteRequest,
                    voteValid.getCommentVoteNo());*/ null;
//            updateVote.changeVoteType(voteRequest.getCommentVoteType());
            commentVoteMap.put(voteValid.getCommentVoteNo(), updateVote);
        }

        updateVoteCount(voteRequest);
    }

    private void updateVoteCount(Vote voteRequest) {
        CommentEntity comment = commentMap.get(voteRequest.getCommentNo());

//        int voteCount = commentVoteMap.values().stream()
//                .filter(vote -> vote.getCommentNo() == voteRequest.getCommentNo())
//                .mapToInt(vote -> "UP".equals(vote.getCommentVoteType()) ? 1
//                        : "DOWN".equals(vote.getCommentVoteType()) ? -1 : 0)
//                .sum();

//        comment.setCommentVoteCount(voteCount);
    }
}