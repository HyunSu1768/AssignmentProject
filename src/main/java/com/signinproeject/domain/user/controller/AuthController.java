package com.signinproeject.domain.user.controller;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;
    @GetMapping("/members")
    public List<Member> findMembers(){
         return memberService.findMembers();
    }

    @PostMapping("/member/create")
    public Member createMember(@RequestBody Member member){
        return memberService.join(member);
    }
}
