package com.signinproeject.controller;

import com.signinproeject.domain.user.Response.TokenResponse;
import com.signinproeject.domain.user.Response.MemberListResponse;
import com.signinproeject.domain.user.Request.MemberLoginRequest;
import com.signinproeject.service.member.MemberService;
import com.signinproeject.domain.user.Request.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/all")
    public MemberListResponse findMembers() {

        return memberService.findAllMembers();

    }

    @PostMapping("/signup")
    public void createMember(
            @RequestBody
            MemberSignUpRequest request
    ) {
        memberService.join(request);
    }

    @PostMapping("/login")
    public TokenResponse Login(
            @RequestBody
            MemberLoginRequest request){
        return memberService.login(request);
    }
}
