package com.asac6c.reddit.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class PostVoteRepository {

    private final Map<Integer, PostVote> postVotes = new HashMap<>();
    private Integer postVoteId = 0;

    public Optional<PostVote> findByUserNoAndPostNo(Integer userNo, Integer postNo) {
        for (Map.Entry<Integer, PostVote> postVote : postVotes.entrySet()) {
            PostVote postVoteObj = postVote.getValue();
            if (postVoteObj.getUserNo().equals(userNo) && postVoteObj.getPostNo().equals(postNo)) {
                return Optional.of(postVoteObj);
            }
        }
        return Optional.empty();
    }

    public void savePostVote(PostVote tempPostVote) {
        findByUserNoAndPostNo(tempPostVote.getUserNo(), tempPostVote.getPostNo())
                .ifPresentOrElse(
                        (postVote) -> postVote.setPostVoteType(tempPostVote.getPostVoteType()),
                        () -> postVotes.put(postVoteId, PostVote.from(postVoteId++, tempPostVote))
                );
    }

    public void deletePostVote(PostVote tempPostVote) {
        findByUserNoAndPostNo(tempPostVote.getUserNo(), tempPostVote.getPostNo())
                .ifPresent((postVote) -> postVotes.remove(postVote.getPostVoteNo())
                );
    }
}
