package com.asac6c.reddit.service;

import com.asac6c.reddit.aop.AddDummyUser;
import com.asac6c.reddit.aop.DummyUserType;
import com.asac6c.reddit.dto.postDto.DraftDeleteRequestDto;
import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.entity.UserEntity;
import com.asac6c.reddit.exception.DraftCustomException;
import com.asac6c.reddit.exception.DraftExceptionType;
import com.asac6c.reddit.repository.PostEntityRepository;

import com.asac6c.reddit.repository.UserEntityRepository;

import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ComponentScan
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DraftService {

    PostEntityRepository postEntityRepository;
    UserEntityRepository userEntityRepository;

    @AddDummyUser(type = DummyUserType.USER)
    @Transactional
    public PostCreateResponseDto createDraft(PostCreateRequestDto request) {
        PostEntity tempPost = PostEntity.forDraftCreate(request);
        tempPost.setUserNo(1L);
        PostEntity generatedPost = postEntityRepository.save(tempPost);
        return PostCreateResponseDto.from(generatedPost);
    }

    @Transactional
    public PostResponseDto getDraftDetailByPostNo(Long id) {
        return postEntityRepository.findByPostNo(id).map(PostResponseDto::from)
                .orElseThrow(() -> new DraftCustomException(DraftExceptionType.POST_NOT_EXIST, id));
    }


    @Transactional
    public List<DraftSummaryResponseDto> getDraftListByUserId(Long userId) {
        UserEntity user = userEntityRepository.findByUserNo(userId).orElseThrow(
                () -> new DraftCustomException(DraftExceptionType.POST_NOT_EXIST,
                        ""));
        return postEntityRepository.findAllByUserEntityAndPostDraftIsTrue(user)
                .stream()
                .filter(PostEntity::getPostDraft)
                .map(DraftSummaryResponseDto::from)
                .toList();
    }

    @Transactional
    public List<DraftSummaryResponseDto> upsertDraftDetail(DraftUpsertRequestDto request) {
        PostEntity postForUpsert = PostEntity.forDraftUpdate(request);
        postEntityRepository.save(postForUpsert);
        return getDraftListByUserId(request.getUserNo());
    }

    @Transactional
    public void deleteDraft(DraftDeleteRequestDto request) {
        postEntityRepository.deleteByPostNo(request.getPostNo());
    }
}
