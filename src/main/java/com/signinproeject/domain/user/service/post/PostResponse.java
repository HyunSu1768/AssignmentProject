package com.signinproeject.domain.user.service.post;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.service.post.comment.CommentResponse;
import jakarta.persistence.*;
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

    private List<CommentResponse> commentResponses;


}
