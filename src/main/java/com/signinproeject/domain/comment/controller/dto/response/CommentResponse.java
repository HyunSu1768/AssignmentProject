package com.signinproeject.domain.comment.controller.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CommentResponse {
    private String content;

    private LocalDateTime createTime;
}
