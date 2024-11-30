package com.asac6c.reddit.service;

import com.asac6c.reddit.dto.postDto.DraftDeleteRequestDto;
import com.asac6c.reddit.dto.postDto.DraftSummaryResponseDto;
import com.asac6c.reddit.dto.postDto.DraftUpsertRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateRequestDto;
import com.asac6c.reddit.dto.postDto.PostCreateResponseDto;
import com.asac6c.reddit.dto.postDto.PostResponseDto;
import com.asac6c.reddit.entity.PostEntity;
import com.asac6c.reddit.exception.DraftCustomException;
import com.asac6c.reddit.exception.DraftExceptionType;
import com.asac6c.reddit.repository.PostRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DraftService {

    PostRepository postRepository;

    public PostCreateResponseDto createDraft(PostCreateRequestDto request) {
//        PostEntity.PostBuilder tempPost = null;
        PostEntity generatedPost = postRepository.createPost(/*tempPost*/);
        return PostCreateResponseDto.from(generatedPost);
    }

    public PostResponseDto getDraftDetailByPostNo(Integer id) {
        return postRepository.getDraftByPostId(id).map(PostResponseDto::from)
                .orElseThrow(() -> new DraftCustomException(DraftExceptionType.POST_NOT_EXIST, id));
    }

    public List<DraftSummaryResponseDto> getDraftListByUserId(Integer userId) {
        return postRepository.getDraftListByUserId(userId).stream()
                .filter(PostEntity::isPostDraft)
                .map(DraftSummaryResponseDto::from)
                .toList();
    }

    public List<DraftSummaryResponseDto> upsertDraftDetail(DraftUpsertRequestDto request) {
        PostEntity postForUpsert = null;
        postRepository.upsertPostDetail(postForUpsert);
        return getDraftListByUserId(request.getUserNo());
    }

    public void deleteDraft(DraftDeleteRequestDto request) {
        postRepository.deleteDraft(request.getPostNo(), request.getUserNo());
    }
}
