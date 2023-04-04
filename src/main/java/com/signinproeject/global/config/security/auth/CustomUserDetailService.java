package com.signinproeject.global.config.security.auth;

import com.signinproeject.domain.user.entity.entity.Member;
import com.signinproeject.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByAccountId(username)
                .orElseThrow(() -> new UsernameNotFoundException("찾을 수 없는 유저입니다."));
        return new CustomUserDetail(member.getName());
    }
}
