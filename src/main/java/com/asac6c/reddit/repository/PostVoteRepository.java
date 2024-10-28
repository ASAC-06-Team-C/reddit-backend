package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import com.asac6c.reddit.entity.PostVote;
import com.asac6c.reddit.entity.PostVoteType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PostVoteRepository {
    private final Map<Integer, PostVote> postVotes = new HashMap<>();
    private Integer postVoteId = 0;

    public void savePostVote(PostVoteUpdateRequestDto postVote) {
        postVotes.put(postVoteId, PostVote.from(postVoteId, postVote));
        postVoteId++;
    }

    public void updatePostVote(Integer postVoteId, PostVoteType postVoteType) {
        PostVote postVote = postVotes.get(postVoteId);
        postVote.setPostVoteType(postVoteType);
    }

    public void deletePostVote(Integer postVoteId) {
        postVotes.remove(postVoteId);
    }
}
