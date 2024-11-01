package com.asac6c.reddit.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CommentRequestDTO {
  
  @Getter
  @RequiredArgsConstructor
  @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Read {
    
    private final int postNo;
    private final String sortType;
    private final int postCommentCount;
    private final int commentPage;
  }
  
  @Getter
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Create {
    
    private int postNo;
    private int userNo;
    private String commentContent;
    private int commentMother;
    private int commentDepth;
  }
  
  @Getter
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Update {
    
    private int userNo;
    private int commentNo;
    private String commentContent;
  }
  
  @Getter
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Delete {
    
    private int userNo;
    private int commentNo;
  }
  
  @Getter
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Vote {
    
    private int userNo;
    private int commentNo;
    private String commentVoteType;
  }
}
