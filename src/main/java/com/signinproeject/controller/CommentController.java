package com.signinproeject.controller;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.service.member.MemberService;
import com.signinproeject.domain.user.Request.CommentRequest;
import com.signinproeject.service.comment.CommentService;
import com.signinproeject.domain.user.Request.CommentUpdateRequest;
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
}
