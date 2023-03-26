package com.signinproeject.domain.user.service.post.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequest {

    private String content;

    private LocalDateTime createTime;

}
