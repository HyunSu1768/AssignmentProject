package com.signinproeject.domain.user.service.member;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.repository.CommentRepository;
import com.signinproeject.domain.user.repository.MemberRepository;
import com.signinproeject.domain.user.repository.PostRepository;
import com.signinproeject.domain.user.service.post.PostResponse;
import com.signinproeject.domain.user.service.post.comment.CommentResponse;
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

    private final CommentRepository commentRepository;

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

        List<MemberResponse> memberResponseList = memberRepository.findAll()
                .stream()
                .map(MemberResponse::of).toList();


        return MemberListResponse.builder()
                .memberResponseList(memberResponseList)
                .build();
    }


    private void validateDuplicateMember(MemberSignUpRequest member){

        if(memberRepository.existsByAccountId(member.getAccountId())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
