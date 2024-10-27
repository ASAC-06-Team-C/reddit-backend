package com.asac6c.reddit.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class CommentRequestDTO {

  @Getter
  @RequiredArgsConstructor
  public static class Read {

    private final int post_no;
    private final String sort_type;
    private final int post_comment_count;
    private final int comment_page;
  }

  @Getter
  public static class Create {

    private int post_no;
    private int user_no;
    private String comment_content;
    private int comment_mother;
    private int comment_depth;
  }

  @Getter
  public static class Update {

    private int user_no;
    private int comment_no;
    private String comment_content;
  }

  @Getter
  public static class Delete {

    private int user_no;
    private int comment_no;
  }

  @Getter
  public static class Vote {

    private int user_no;
    private int comment_no;
    private boolean comment_vote_type;
  }
}
