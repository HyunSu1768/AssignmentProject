package com.signinproeject.domain.auth.controller;

import com.signinproeject.domain.auth.controller.dto.response.TokenResponse;
import com.signinproeject.domain.auth.service.MemberService;
import com.signinproeject.domain.auth.controller.dto.response.MemberListResponse;
import com.signinproeject.domain.auth.controller.dto.request.MemberLoginRequest;
import com.signinproeject.domain.auth.controller.dto.request.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;



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
