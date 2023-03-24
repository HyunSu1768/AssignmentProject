package com.signinproeject.domain.user.service.post;

import com.signinproeject.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostResponse {

    private String title;

    private String description;

    private Long memberId;




}
