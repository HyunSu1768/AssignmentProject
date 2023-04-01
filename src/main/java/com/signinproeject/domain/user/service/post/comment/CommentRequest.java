package com.signinproeject.domain.user.service.post.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentRequest {

    private String content;

    private LocalDateTime createTime;

}
