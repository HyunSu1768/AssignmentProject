package com.signinproeject.domain.user.entity.controller;

import com.signinproeject.domain.auth.controller.dto.response.MemberListResponse;
import com.signinproeject.domain.auth.service.MemberService;
import com.signinproeject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public MemberListResponse findMembers() {

        return userService.findAllMembers();
    }
}
