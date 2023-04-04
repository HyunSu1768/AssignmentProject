package com.signinproeject.domain.post.controller.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class PostCreateRequest {

    @Size(max = 50,min = 5,message = "제목은 5글자 이상,50글자 이하여야 합니다.")
    private String title;

    @Size(max = 1000,message = "포스트의 내용은 1000자를 넘을 수 없습니다.")
    private String description;

}

