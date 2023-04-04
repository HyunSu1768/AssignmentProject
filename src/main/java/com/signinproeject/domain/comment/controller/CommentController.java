package com.signinproeject.domain.comment.controller;

import com.signinproeject.domain.comment.controller.dto.request.CommentRequest;
import com.signinproeject.domain.comment.controller.dto.request.CommentUpdateRequest;
import com.signinproeject.domain.comment.service.CommentService;
import com.signinproeject.domain.auth.service.MemberService;
import com.signinproeject.domain.user.entity.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final MemberService memberService;

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

        Member member = memberService.findOne(name);

        commentService.updateComment(commentId,member.getId(),commentUpdateRequest);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId
    ){
        commentService.deleteComment(commentId);
    }
}
