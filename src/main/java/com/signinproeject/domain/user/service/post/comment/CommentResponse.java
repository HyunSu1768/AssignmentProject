package com.signinproeject.domain.user.service.post.comment;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentResponse {
    private String content;
}
