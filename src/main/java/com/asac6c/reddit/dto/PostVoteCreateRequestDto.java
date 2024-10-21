package com.asac6c.reddit.dto;

import com.asac6c.reddit.entity.PostVoteType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostVoteCreateRequestDto {
    @NotNull
    Integer post_no;
    @NotNull
    Integer user_no;
    @NotNull
    PostVoteType post_vote_type;
}
