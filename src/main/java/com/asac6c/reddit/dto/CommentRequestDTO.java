package com.asac6c.reddit.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CommentRequestDTO {

    @Getter
    @RequiredArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Read {
        @NotNull(message = "postNo은 Null일 수 없습니다.")
        private final int postNo;
        private final String sortType;
        private final int postCommentCount;
        private final int commentPage;
    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Create {
        @NotNull(message = "postNo은 Null일 수 없습니다.")
        private int postNo;
        @NotNull(message = "userNo은 Null일 수 없습니다.")
        private int userNo;
        private String commentContent;
        private int commentMother;
        private int commentDepth;
    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Update {
        @NotNull(message = "userNo은 Null일 수 없습니다.")
        private int userNo;
        @NotNull(message = "commentNo은 Null일 수 없습니다.")
        private int commentNo;
        private String commentContent;
    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Delete {
        @NotNull(message = "userNo은 Null일 수 없습니다.")
        private int userNo;
        @NotNull(message = "commentNo은 Null일 수 없습니다.")
        private int commentNo;
    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Vote {
        @NotNull(message = "userNo은 Null일 수 없습니다.")
        private int userNo;
        @NotNull(message = "commentNo은 Null일 수 없습니다.")
        private int commentNo;
        private String commentVoteType;
    }
}
