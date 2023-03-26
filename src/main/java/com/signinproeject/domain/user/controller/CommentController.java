package com.signinproeject.domain.user.controller;

import com.signinproeject.domain.user.entity.Comment;
import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.domain.user.service.post.PostService;
import com.signinproeject.domain.user.service.post.comment.CommentRequest;
import com.signinproeject.domain.user.service.post.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    @PostMapping("/{postId}")
    public void newComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest commentRequest
    ){
        commentService.newComment(commentRequest,postId);
    }
}
