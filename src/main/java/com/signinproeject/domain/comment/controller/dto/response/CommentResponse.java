package com.signinproeject.domain.comment.controller.dto.response;


import com.signinproeject.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CommentResponse {

    private String content;

    private LocalDateTime createTime;

    public static CommentResponse of(Comment comment){

        return CommentResponse.builder()
                .createTime(comment.getCreateTime())
                .content(comment.getContent())
                .build();

    }


}
