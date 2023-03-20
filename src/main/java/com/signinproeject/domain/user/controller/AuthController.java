package com.signinproeject.domain.user.controller;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.service.MemberDTO;
import com.signinproeject.domain.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class AuthController {
    private final MemberService memberService;

    @GetMapping("/all")
    public List<MemberDTO> findMembers(){
        List<MemberDTO> list = memberService.findALlMembers();
        return list;

    }

    @PostMapping
    public Member createMember(
            @Valid @RequestBody
            MemberDTO memberDTO
    ){
        return memberService.join(memberDTO);
    }
}
