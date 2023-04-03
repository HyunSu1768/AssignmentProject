package com.signinproeject.service.like;

import com.signinproeject.domain.user.entity.Likes;
import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.repository.LikeRepository;
import com.signinproeject.repository.MemberRepository;
import com.signinproeject.repository.PostRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    @Transactional
    public void addLike(Long postId){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("찾을 수 없는 멤버 입니다."));

        Long memberId = member.getId();

        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));

        Likes likes = new Likes(memberId,post);

        likeRepository.save(likes);
    }
}
