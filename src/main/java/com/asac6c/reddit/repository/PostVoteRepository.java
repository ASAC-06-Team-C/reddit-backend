package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.entity.PostVote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PostVoteRepository implements IPostVoteRepository {
    private final Map<Integer, PostVote> postVotes = new HashMap<>();
    private Integer postVoteId = 0;

    @Override
    public void savePostVote(PostVoteCreateRequestDto postVote) {
        postVotes.put(postVoteId, PostVote.from(postVoteId++, postVote));
    }

    @Override
    public void deletePostVote(Integer postVoteId) {
        postVotes.remove(postVoteId);
    }
}
