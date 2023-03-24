package com.signinproeject.domain.user.controller;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.service.member.MemberListResponse;
import com.signinproeject.domain.user.service.member.MemberSignUpRequest;
import com.signinproeject.domain.user.service.member.MemberService;
import jakarta.validation.Valid;
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
    public MemberListResponse findMembers(){

        return memberService.findAllMembers();

    }

    @PostMapping
    public Member createMember(
            @Valid @RequestBody
            MemberSignUpRequest memberSignUpRequest
    ){
        return memberService.join(memberSignUpRequest);
    }
}
