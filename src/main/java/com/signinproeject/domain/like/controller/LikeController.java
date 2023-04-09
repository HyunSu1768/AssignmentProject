package com.signinproeject.domain.like.controller;


import com.signinproeject.domain.like.controller.dto.request.SetLikeRequest;
import com.signinproeject.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/addLike")
    public void addLike(
            @RequestBody SetLikeRequest setLikeRequest
    ){
        likeService.addLike(setLikeRequest.getPostId());
    }
}
