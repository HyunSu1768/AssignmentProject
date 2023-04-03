package com.signinproeject.domain.user.service.member;

import antlr.Token;
import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.repository.MemberRepository;
import com.signinproeject.domain.user.service.jwt.TokenResponse;
import com.signinproeject.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;


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

    @Transactional
    public TokenResponse login(MemberLoginRequest request) {

        Member member = memberRepository.findByAccountId(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("찾을 수 없는 멤버입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalStateException("잘못된 비밀번호 입니다.");
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createToken(member.getAccountId()))
                .expiredAt(LocalDateTime.now())
                .build();


    }

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


    private void validateDuplicateMember(MemberSignUpRequest member){

        if(memberRepository.existsByAccountId(member.getAccountId())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
