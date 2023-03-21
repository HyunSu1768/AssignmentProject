package com.signinproeject.domain.user.service;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(MemberSignUpRequest member) {

        String EncodedPassword = passwordEncoder.encode(member.getPassword());

        validateDuplicateMember(member);

        Member member1 = Member.builder()
                .accountId(member.getAccountId())
                .password(EncodedPassword)
                .grade(member.getGrade())
                .studentNumber(member.getStudentNumber())
                .name(member.getName())
                .build();

        return memberRepository.save(member1);

    }

    public MemberListResponse findAllMembers(){

        List<MemberResponse> memberResponse = memberRepository.findAll().stream()
                .map(it -> MemberResponse.builder()
                        .name(it.getName())
                        .accountId(it.getAccountId())
                        .grade(it.getGrade())
                        .studentNumber(it.getStudentNumber())
                        .build()
                )
                .toList();

        return MemberListResponse.builder()
                .memberResponseList(memberResponse)
                .build();


    }


    private void validateDuplicateMember(MemberSignUpRequest member){

        if(memberRepository.existsByAccountId(member.getAccountId())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
