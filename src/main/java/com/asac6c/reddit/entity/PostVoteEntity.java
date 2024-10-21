package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.PostVoteCreateRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostVoteEntity {
    Integer post_vote_no;
    Integer user_no;
    Integer post_no;
    PostVoteType post_vote_type;

    public static PostVoteEntity from(Integer post_vote_no, PostVoteCreateRequestDto requestDto){
        return new PostVoteEntity(
                post_vote_no,
                requestDto.getUser_no(),
                requestDto.getPost_no(),
                requestDto.getPost_vote_type()
        );
    };
}
