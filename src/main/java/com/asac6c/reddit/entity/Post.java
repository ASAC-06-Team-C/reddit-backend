package com.asac6c.reddit.entity;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@ToString
@Getter
@RequiredArgsConstructor()
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Post {

    Integer post_no;
    Integer user_no;
    String community_name;
    String post_title;
    String post_content;
    Integer post_vote_count;
    Integer post_comment_count;
    Date post_write_date;
}