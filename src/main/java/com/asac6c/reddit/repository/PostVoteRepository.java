package com.asac6c.reddit.repository;

import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import com.asac6c.reddit.entity.PostVoteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PostVoteRepository implements IPostVoteRepository {

    private final Map<Long, PostVoteEntity> postVotes = new HashMap<>();
    private Long postVoteId = 0L;

    @Override
    public void savePostVote(PostVoteCreateRequestDto postVote) {
//        postVotes.put(postVoteId, PostVoteEntity.from(postVoteId += 1, postVote));
    }

    @Override
    public void deletePostVote(Integer postVoteId) {
        postVotes.remove(postVoteId);
    }
}
