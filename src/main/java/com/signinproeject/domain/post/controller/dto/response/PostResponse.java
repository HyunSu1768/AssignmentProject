package com.signinproeject.domain.post.controller.dto.response;

import com.signinproeject.domain.comment.controller.dto.response.CommentResponse;
import com.signinproeject.domain.like.controller.dto.response.LikeResponse;
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
