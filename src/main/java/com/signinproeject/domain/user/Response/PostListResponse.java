package com.signinproeject.domain.user.Response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponse {

    List<PostResponse> postCreateRequests;

}
