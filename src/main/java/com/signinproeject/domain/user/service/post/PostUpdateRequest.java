package com.signinproeject.domain.user.service.post;

import com.signinproeject.domain.user.entity.Post;
import lombok.Data;

@Data
public class PostUpdateRequest {
    private String title;
    private String description;

}
