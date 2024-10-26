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
