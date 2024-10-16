package com.asac6c.reddit.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@ToString
public class PostsResponseBody {
    Integer user_no;
    Integer post_no;
    String community_name;
    String post_title;
    String post_content;
    Integer post_vote_count;
    Integer post_comment_count;
    Integer post_write_date;
}
