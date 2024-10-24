package com.asac6c.reddit.dto;

import lombok.Getter;

public class CommentRequestDTO {

  @Getter
  public static class Read {

    private int post_no;
    private String sort_type;
    private int post_comment_count;
    private int comment_page;
  }

  @Getter
  public static class Create {

    private int user_no;
    private String comment_content;
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
    private boolean vote_type;
  }

  @Getter
  public static class Reply {

    private int user_no;
    private int comment_mother;
    private String comment_content;
  }
}
