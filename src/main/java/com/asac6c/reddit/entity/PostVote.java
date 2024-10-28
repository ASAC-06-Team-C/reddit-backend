package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostVote {
    final Integer postVoteNo;
    final Integer userNo;
    final Integer postNo;
    @Setter
    PostVoteType postVoteType;

    public static PostVote from(Integer postVoteNo, PostVoteUpdateRequestDto requestDto) {
        return new PostVote(
                postVoteNo,
                requestDto.getUserNo(),
                requestDto.getPostNo(),
                requestDto.getPostVoteType()
        );
    }

    ;
}
