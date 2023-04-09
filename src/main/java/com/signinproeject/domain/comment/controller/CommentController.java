package com.signinproeject.domain.comment.controller;

import com.signinproeject.domain.comment.controller.dto.request.CommentRequest;
import com.signinproeject.domain.comment.controller.dto.request.CommentUpdateRequest;
import com.signinproeject.domain.comment.service.CommentService;
import com.signinproeject.domain.user.entity.entity.Member;
import com.signinproeject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final UserService userService;

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public void newComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest commentRequest
    ){
        commentService.newComment(commentRequest,postId);
    }

    @PutMapping("/edit/{commentId}")
    public void editComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest commentUpdateRequest
            ){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = userService.findOne(name);

        commentService.updateComment(commentId,member.getId(),commentUpdateRequest);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId
    ){
        commentService.deleteComment(commentId);
    }
}
