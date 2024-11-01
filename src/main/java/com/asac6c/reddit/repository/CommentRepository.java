package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.CommentRequestDTO.Create;
import com.asac6c.reddit.dto.CommentRequestDTO.Update;
import com.asac6c.reddit.dto.CommentRequestDTO.Vote;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.entity.CommentVoteEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

  private final Map<Integer, CommentEntity> commentMap = new HashMap<>();
  // 게시글에서 가져오는거(댓글번호와 그 댓글번호에 해당하는 좋아요 집합)
  private final Map<Integer, CommentVoteEntity> commentVoteMap = new HashMap<>();
  //
//  private final Map<Integer, Set<CommentVoteEntity>> commentVoteMap = new HashMap<>();

  private int commentNo = 1;
  private int commentVoteNo = 1;

  public List<CommentEntity> getComment(int postNo) {
    return commentMap.values().stream()
        .filter(comment -> comment.getPostNo() == postNo)
        .toList();
  }

  public List<CommentEntity> getAllComment() {
    return new ArrayList<>(commentMap.values());
  }

  public CommentEntity createComment(Create createRequest) {
    CommentEntity newComment = CommentEntity.from(createRequest, commentNo);

    commentMap.put(commentNo++, newComment);
    return newComment;
  }

  public void updateComment(Update updateRequest) {
    CommentEntity comment = commentMap.get(updateRequest.getCommentNo());

    comment.setCommentContent(updateRequest.getCommentContent());
  }

  public void deleteComment(int comment_no) {
    commentMap.remove(comment_no);
  }

  public void voteComment(Vote voteRequest) {
    // 유저 번호에 대한 vote 내역 가져오기
    CommentVoteEntity voteValid = commentVoteMap.values().stream()
        .filter(vote -> vote.getUserNo() == voteRequest.getUserNo()
            && vote.getCommentNo() == voteRequest.getCommentNo())
        .findFirst()
        .orElse(null);

    // 투표를 하지 않았다면,
    if (voteValid == null) {
      CommentVoteEntity newVote = CommentVoteEntity.from(voteRequest, commentVoteNo);
      newVote.changeVoteType(voteRequest.getCommentVoteType());
      commentVoteMap.put(commentVoteNo++, newVote);
    }
    // 투표를 했다면
    else {
      CommentVoteEntity updateVote = CommentVoteEntity.from(voteRequest,
          voteValid.getCommentVoteNo());
      updateVote.changeVoteType(voteRequest.getCommentVoteType());
      commentVoteMap.put(voteValid.getCommentVoteNo(), updateVote);
    }

    updateVoteCount(voteRequest);
  }

  private void updateVoteCount(Vote voteRequest) {
    CommentEntity comment = commentMap.get(voteRequest.getCommentNo());

    int voteCount = commentVoteMap.values().stream()
        .filter(vote -> vote.getCommentNo() == voteRequest.getCommentNo())
        .mapToInt(vote -> "UP".equals(vote.getCommentVoteType()) ? 1
            : "DOWN".equals(vote.getCommentVoteType()) ? -1 : 0)
        .sum();

    comment.setCommentVoteCount(voteCount);
  }
}