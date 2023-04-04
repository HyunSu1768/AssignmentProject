package com.signinproeject.domain.comment.controller.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentRequest {

    private String content;

    private LocalDateTime createTime;

}
