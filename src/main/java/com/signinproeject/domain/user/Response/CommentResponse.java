package com.signinproeject.domain.user.Response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CommentResponse {
    private String content;

    private LocalDateTime createTime;
}
