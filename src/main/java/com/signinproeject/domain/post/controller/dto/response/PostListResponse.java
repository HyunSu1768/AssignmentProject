package com.signinproeject.domain.post.controller.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponse {

    List<PostResponse> postCreateRequests;

}
