package com.asac6c.reddit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@ToString
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Post {
    Integer postNo;
    Integer userNo;
    String communityName;
    String postTitle;
    String postContent;
    Integer postVoteCount;
    Integer postCommentCount;
    boolean postDraft;
    Date postWriteDate;
}
