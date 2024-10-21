package com.asac6c.reddit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostEntity {
    Integer post_no;
    Integer user_no;
    String community_name;
    String post_title;
    String post_content;
    Integer post_vote_count;
    Integer post_comment_count;
    Date post_write_date;
}
