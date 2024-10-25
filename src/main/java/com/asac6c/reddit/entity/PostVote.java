package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostVote {
    Integer postVoteNo;
    Integer userNo;
    Integer postNo;
    PostVoteType postVoteType;

    public static PostVote from(Integer postVoteNo, PostVoteCreateRequestDto requestDto) {
        return new PostVote(
                postVoteNo,
                requestDto.getUserNo(),
                requestDto.getPostNo(),
                requestDto.getPostVoteType()
        );
    }

    ;
}
