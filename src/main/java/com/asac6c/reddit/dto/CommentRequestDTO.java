package com.asac6c.reddit.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class CommentRequestDTO {

  @Getter
  @RequiredArgsConstructor
  public static class Read {

    private final int postNo;
    private final String sortType;
    private final int postCommentCount;
    private final int commentPage;
  }

  @Getter
  public static class Create {

    private int postNo;
    private int userNo;
    private String commentContent;
    private int commentMother;
    private int commentDepth;
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

    private int userNo;
    private int commentNo;
    private boolean commentVoteType;
  }
}
