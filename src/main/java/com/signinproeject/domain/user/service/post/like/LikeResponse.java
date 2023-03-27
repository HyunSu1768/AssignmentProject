package com.signinproeject.domain.user.service.post.like;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LikeResponse {

    private Long memberId;

    private Long postId;

}
