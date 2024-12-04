package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.CommentRequestDTO.*;
import com.asac6c.reddit.dto.CommentResponseDTO;
import com.asac6c.reddit.entity.CommentEntity;
import com.asac6c.reddit.entity.CommentVoteEntity;
import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.UserEntity;
import com.asac6c.reddit.repository.CommentEntityRepository;
import com.asac6c.reddit.repository.CommentVoteEntityRepository;
import com.asac6c.reddit.repository.PostEntityRepository;
import com.asac6c.reddit.repository.UserEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asac6c.reddit.entity.VoteType.DISLIKE;
import static com.asac6c.reddit.entity.VoteType.LIKE;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentEntityRepository commentEntityRepository;
    private final CommentVoteEntityRepository commentVoteEntityRepository;
    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;

    @Transactional
    public List<CommentResponseDTO> ReadComment(Read readRequest) {
        int postNo = readRequest.getPostNo();
        Long postNoLong = (long) postNo;
        List<CommentEntity> comment = commentEntityRepository.findAllByPostEntity_PostNo(postNoLong);

        return comment.stream()
                .map(commentEntity -> CommentResponseDTO.from(commentEntity, null, null))
                .toList();
    }

    @Transactional
    public CommentResponseDTO createComment(Create createRequest) {
        PostEntity postNo = postEntityRepository.findById((long) createRequest.getPostNo())
                .orElseThrow(() -> new RuntimeException(""));
        UserEntity userNo = userEntityRepository.findById((long) createRequest.getUserNo())
                .orElseThrow(() -> new RuntimeException(""));

        CommentEntity commentEntity = CommentEntity.create(
                postNo,
                userNo,
                createRequest
        );

        CommentEntity savedComment = commentEntityRepository.save(commentEntity);

        return CommentResponseDTO.from(savedComment, null, null);
    }

    @Transactional
    public void updateComment(Update updateRequest) {
        CommentEntity comment = commentEntityRepository.findById((long) updateRequest.getCommentNo())
                .orElseThrow(() -> new RuntimeException(""));

        comment.setCommentContent(updateRequest.getCommentContent());

        commentEntityRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Delete deleteRequest) {
        CommentEntity comment = commentEntityRepository.findById((long) deleteRequest.getCommentNo())
                .orElseThrow(() -> new RuntimeException(""));

        comment.setCommentDeleted(true);

        commentEntityRepository.save(comment);
    }

    @Transactional
    public void voteComment(Vote voteRequest) {
        CommentVoteEntity voteEntity = commentVoteEntityRepository.findByUserEntity_UserNo((long) voteRequest.getUserNo());

        if (voteEntity == null) {
            CommentVoteEntity newVote = CommentVoteEntity.from(voteRequest);

            voteEntity = commentVoteEntityRepository.save(newVote);
        } else {
            CommentVoteEntity updateVote = CommentVoteEntity.from(voteRequest);

            voteEntity = commentVoteEntityRepository.save(updateVote);
        }

        updateVoteCount(voteRequest, voteEntity);
    }

    private void updateVoteCount(Vote voteRequest, CommentVoteEntity voteEntity) {
        CommentEntity comment = commentEntityRepository.findById((long) voteRequest.getCommentNo())
                .orElseThrow(() -> new RuntimeException(""));

        Integer voteCount = comment.getCommentVoteCount();

        if (voteEntity.getCommentVoteType().equals(LIKE)) {
            comment.setCommentVoteCount(voteCount + 1);
        } else if (voteEntity.getCommentVoteType().equals(DISLIKE)) {
            comment.setCommentVoteCount(voteCount - 1);
        }

        commentEntityRepository.save(comment);
    }
    //        // 투표를 하지 않았다면,
//        if (voteValid == null) {
//            CommentVoteEntity newVote = CommentVoteEntity.from(voteRequest, commentVoteNo);
//            newVote.changeVoteType(voteRequest.getCommentVoteType());
//            commentVoteMap.put(commentVoteNo++, newVote);
//        }
//        // 투표를 했다면
//        else {
//            CommentVoteEntity updateVote = CommentVoteEntity.from(voteRequest,
//                    voteValid.getCommentVoteNo());
//            updateVote.changeVoteType(voteRequest.getCommentVoteType());
//            commentVoteMap.put(voteValid.getCommentVoteNo(), updateVote);
//        }
}

//    private final CommentRepository commentRepository;
//
//    public List<CommentResponseDTO> getComment(Read readRequest) {
//        List<CommentEntity> comment = commentRepository.getComment(readRequest.getPostNo());
//
//        // 댓글 없을 때, 예외 처리
//        if (comment.isEmpty()) {
//            throw new CommentCustomException(CommentExceptionType.COMMENT_NOT_FOUND);
//        }
//
//        return comment.stream()
//                .map(commentEntity -> CommentResponseDTO.from(commentEntity, null, null))
//                .toList();
//    }
//
//    public CommentResponseDTO createComment(Create createRequest) {
//        // 일단. 댓글 내용이 없을 때, 예외처리
//        if (createRequest.getCommentContent() == null || createRequest.getCommentContent()
//                .isEmpty()) {
//            throw new CommentCustomException(CommentExceptionType.COMMENT_CREATE_FAILED);
//        }
//
//        try {
//            return CommentResponseDTO.from(commentRepository.createComment(createRequest), null,
//                    null);
//        } catch (Exception e) {
//            throw new CommentCustomException(CommentExceptionType.COMMENT_CREATE_FAILED);
//        }
//    }
//
//    public void updateComment(Update updateRequest) {
//        if (updateRequest.getCommentContent() == null || updateRequest.getCommentContent()
//                .isEmpty()) {
//            throw new CommentCustomException(CommentExceptionType.COMMENT_UPDATE_FAILED);
//        }
//
//        try {
//            commentRepository.updateComment(updateRequest);
//        } catch (Exception e) {
//            throw new CommentCustomException(CommentExceptionType.COMMENT_UPDATE_FAILED);
//        }
//    }
//
//    public void deleteComment(Delete deleteRequest) {
//        try {
//            commentRepository.deleteComment(deleteRequest);
//        } catch (Exception e) {
//            throw new CommentCustomException(CommentExceptionType.COMMENT_DELETE_FAILED);
//        }
//    }
//
//    public void voteComment(Vote voteRequest) {
//        try {
//            commentRepository.voteComment(voteRequest);
//        } catch (Exception e) {
//            throw new CommentCustomException(CommentExceptionType.COMMENT_VOTE_FAILED);
//        }
//    }