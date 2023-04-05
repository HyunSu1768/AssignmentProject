package com.signinproeject.domain.like.controller.dto.response;

import com.signinproeject.domain.like.entity.Likes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LikeResponse {

    private Long memberId;

    private Long postId;

    public static LikeResponse of(Likes likes){

        return LikeResponse.builder()
                .memberId(likes.getMemberId())
                .postId(likes.getPost().getId())
                .build();

    }

}
