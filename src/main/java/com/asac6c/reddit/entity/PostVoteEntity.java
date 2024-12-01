package com.asac6c.reddit.entity;

import com.asac6c.reddit.dto.PostVoteUpdateRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "POST_VOTE")
public class PostVoteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postVoteNo;
    @ManyToOne
    @JoinColumn(name = "user_no")
    UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "post_no")
    PostEntity postEntity;
    VoteType postVoteType;

    public static PostVoteEntity from(PostVoteUpdateRequestDto request) {
        return new PostVoteEntity(
                request.getPostVoteNo(),
                null,
                null,
                request.getPostVoteType()
        );
    }

}
