package com.signinproeject.domain.post.controller;

import com.signinproeject.domain.post.controller.dto.request.PostCreateRequest;
import com.signinproeject.domain.post.controller.dto.request.PostUpdateRequest;
import com.signinproeject.domain.post.controller.dto.response.PostListResponse;
import com.signinproeject.domain.like.service.LikeService;
import com.signinproeject.domain.auth.service.MemberService;
import com.signinproeject.domain.post.service.PostService;
import com.signinproeject.domain.user.entity.entity.Member;
import com.signinproeject.domain.like.controller.dto.request.SetLikeRequest;
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
