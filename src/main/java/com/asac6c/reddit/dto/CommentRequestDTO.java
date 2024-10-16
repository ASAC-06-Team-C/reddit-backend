package com.asac6c.reddit.dto;

import lombok.Getter;


public class CommentRequestDTO {

  @Getter
  public static class Read {

    private int postNo;
    private String sortType;
    private int postCommentCount;
    private int commentPage;
  }

  @Getter
  public static class Create {

    private int userNo;
    private String commentContent;
  }

  @Getter
  public static class Update {

    private int userNo;
    private int commentNo;
    private String commentContent;
  }

  @Getter
  public static class Delete {

    private int userNo;
    private int commentNo;
  }

  @Getter
  public static class Vote {

    private int commentNo;
    private int userNo;
    private boolean voteType;
  }

  @Getter
  public static class Reply {

    private int userNo;
    private int commentMother;
    private String commentContent;
  }
}
