package com.signinproeject.domain.user.service.post;

import com.signinproeject.domain.user.service.post.comment.CommentResponse;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponse {

    List<PostResponse> postCreateRequests;

}
