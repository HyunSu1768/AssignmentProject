package com.signinproeject.domain.user.Response;

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
