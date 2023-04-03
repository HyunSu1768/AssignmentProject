package com.signinproeject.domain.user.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class PostResponse {

    private String title;

    private String description;

    private Long memberId;

    private int like;

    private List<CommentResponse> commentResponses;
    private List<LikeResponse> likeResponses;

}
