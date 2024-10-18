package com.asac6c.reddit.dto.postDto;

import com.asac6c.reddit.entity.Post;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class DraftResponseDto {
  String postTitle;
  Date postWriteDate;

  public static DraftResponseDto from (Post response) {
    return new DraftResponseDto(response.getPostTitle(), response.getPostWriteDate());
  }
}
