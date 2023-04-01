package com.signinproeject.domain.user.controller;

import com.signinproeject.domain.user.service.jwt.TokenResponse;
import com.signinproeject.domain.user.service.member.MemberListResponse;
import com.signinproeject.domain.user.service.member.MemberLoginRequest;
import com.signinproeject.domain.user.service.member.MemberService;
import com.signinproeject.domain.user.service.member.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class AuthController {

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
