package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.Post;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class PostResponseBody {
    Integer user_no;
    Integer post_no;
    String community_name;
    String post_title;
    String post_content;
    Integer post_vote_count;
    Integer post_comment_count;
    Date post_write_date;

    public static PostResponseBody of(Post post) {
        return new PostResponseBody(
                post.getUser_no(),
                post.getPost_no(),
                post.getCommunity_name(),
                post.getPost_title(),
                post.getPost_content(),
                post.getPost_vote_count(),
                post.getPost_comment_count(),
                post.getPost_write_date()
        );
    }
}
