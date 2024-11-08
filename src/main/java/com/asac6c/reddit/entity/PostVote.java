package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PostVote {
    final Integer postVoteNo;
    final Integer userNo;
    final Integer postNo;
    @Setter
    PostVoteType postVoteType;

    public static PostVote from(Integer postVoteNo, PostVote postvote) {
        return new PostVote(
                postVoteNo,
                postvote.getUserNo(),
                postvote.getPostNo(),
                postvote.getPostVoteType()
        );
    }

    public static PostVote from(PostVoteUpdateRequestDto requestDto) {
        return new PostVote(
                null,
                requestDto.getUserNo(),
                requestDto.getPostNo(),
                requestDto.getPostVoteType()
        );
    }
}
