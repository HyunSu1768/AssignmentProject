package com.signinproeject.domain.user.controller;

import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.domain.user.service.post.PostCreateRequest;
import com.signinproeject.domain.user.service.post.PostListResponse;
import com.signinproeject.domain.user.service.post.PostService;
import com.signinproeject.domain.user.service.post.like.LikeService;
import com.signinproeject.domain.user.service.post.like.SetLikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

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
    @GetMapping
    public PostListResponse findAllPost(){
        return postService.findAllPost();
    }


}
