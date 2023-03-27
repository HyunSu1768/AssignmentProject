package com.signinproeject.domain.user.service.post.like;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetLikeRequest {

    private Long memberId;

    private Long postId;

}
