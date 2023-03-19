package com.signinproeject.domain.user.service;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public Member join(MemberDTO member) {

        String EncodedPassword = passwordEncoder.encode(member.getPassword());
        validateDuplicateMember(member);
        Member member1 =  Member.builder()
                .accountId(member.getAccountId())
                .password(EncodedPassword)
                .grade(member.getGrade())
                .studentNumber(member.getStudentNumber())
                .name(member.getName())
                .build();

        return memberRepository.save(member1);

    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    private void validateDuplicateMember(MemberDTO member){
        if(memberRepository.existsByAccountId(member.getAccountId())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
