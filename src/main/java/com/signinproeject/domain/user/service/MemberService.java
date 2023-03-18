package com.signinproeject.domain.user.service;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);

    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    private void validateDuplicateMember(Member member){
        if(memberRepository.existsByAccountId(member.getAccountId())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
