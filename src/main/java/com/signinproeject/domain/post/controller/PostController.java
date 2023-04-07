package com.signinproeject.domain.post.controller;

import com.signinproeject.domain.post.controller.dto.request.PostCreateRequest;
import com.signinproeject.domain.post.controller.dto.request.PostUpdateRequest;
import com.signinproeject.domain.post.controller.dto.response.PostListResponse;
import com.signinproeject.domain.post.controller.dto.response.PostResponse;
import com.signinproeject.domain.post.entity.SortType;
import com.signinproeject.domain.post.service.PostService;
import com.signinproeject.domain.user.entity.entity.Member;
import com.signinproeject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final UserService userService;

    private final PostService postService;

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

        Member member = userService.findOne(name);

        postService.updatePost(postId,member.getId(),postUpdateRequest);
    }


    @GetMapping
    public PostListResponse findAllPost(Pageable pageable,@RequestParam("sort_type") SortType sortType){
        return postService.findAllPostSort(pageable,sortType);
    }


    @GetMapping("/{postId}")
    public PostResponse findOnePost(
            @PathVariable Long postId
    ){
        return postService.findOne(postId);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(
            @PathVariable Long postId
    ){
        postService.deletePost(postId);
    }


}
