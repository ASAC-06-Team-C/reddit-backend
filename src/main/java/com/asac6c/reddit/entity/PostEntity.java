package com.asac6c.reddit.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "POST")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    UserEntity userEntity;
    String communityName;
    String postTitle;
    @Lob
    String postContent;
    Integer postVoteCount;
    Integer postCommentCount;
    boolean postDraft;
    Date postWriteDate;

//    public static Post.PostBuilder configureInstanceForCreate(PostCreateRequestDto request) {
//        return Post.builder()
//                .userNo(request.getUserNo())
//                .communityName("DUMMY")
//                .postTitle(request.getPostTitle())
//                .postContent(request.getPostContent())
//                .postVoteCount(0)
//                .postCommentCount(0)
//                .postDraft(request.isPostDraft())
//                .postWriteDate(new Date());
//    }

//    public static Post completeInstanceForCreate(Post.PostBuilder builder, Integer postNo) {
//        return builder.postNo(postNo)
//                .build();
//    }

//    public static Post instanceForUpsert(DraftUpsertRequestDto request) {
//        return new Post(
//                request.getPostNo(),
//                request.getUserNo(),
//                "DUMMY",
//                request.getPostTitle(),
//                request.getPostContent(),
//                0,
//                0,
//                request.isPostDraft(),
//                new Date()
//        );
//    }
}

