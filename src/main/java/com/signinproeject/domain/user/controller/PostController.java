package com.signinproeject.domain.user.controller;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.service.member.MemberService;
import com.signinproeject.domain.user.service.post.PostCreateRequest;
import com.signinproeject.domain.user.service.post.PostListResponse;
import com.signinproeject.domain.user.service.post.PostService;
import com.signinproeject.domain.user.service.post.PostUpdateRequest;
import com.signinproeject.domain.user.service.post.like.LikeService;
import com.signinproeject.domain.user.service.post.like.SetLikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final MemberService memberService;

    private final PostService postService;

    private final LikeService likeService;

    @PostMapping("/like")
    public void addLike(
            @RequestBody SetLikeRequest setLikeRequest
            ){
        likeService.addLike(setLikeRequest.getPostId());
    }

    @PostMapping
    public void createPost(
            @RequestBody
            PostCreateRequest post
    ){
        postService.createPost(post);
    }

    @PutMapping("/edit/{postId}")
    public void editPost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest postUpdateRequest
            ){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberService.findOne(name);

        postService.updatePost(postId,member.getId(),postUpdateRequest);
    }


    @GetMapping
    public PostListResponse findAllPost(){
        return postService.findAllPost();
    }


}
