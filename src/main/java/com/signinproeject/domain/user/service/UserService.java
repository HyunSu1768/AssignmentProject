package com.signinproeject.domain.user.service;

import com.signinproeject.domain.auth.controller.dto.response.MemberListResponse;
import com.signinproeject.domain.auth.controller.dto.response.MemberResponse;
import com.signinproeject.domain.auth.repository.MemberRepository;
import com.signinproeject.domain.user.entity.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    public Member findOne(String memberName){
        return memberRepository.findByName(memberName)
                .orElseThrow(()-> new UsernameNotFoundException("찾을 수 없는 멤버이름 입니다."));
    }

    public MemberListResponse findAllMembers(){

        List<MemberResponse> memberResponseList = memberRepository.findAll()
                .stream()
                .map(MemberResponse::of).toList();

        return MemberListResponse.builder()
                .memberResponseList(memberResponseList)
                .build();
    }

}
