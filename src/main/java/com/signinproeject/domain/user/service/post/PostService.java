package com.signinproeject.domain.user.service.post;

import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.domain.user.repository.MemberRepository;
import com.signinproeject.domain.user.repository.PostRepository;
import com.signinproeject.domain.user.service.member.MemberListResponse;
import com.signinproeject.domain.user.service.member.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createPost(PostCreateRequest postCreateRequest,Long memberId){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("멤버가 NULL입니다"));

        Post post = new Post(postCreateRequest.getTitle(),postCreateRequest.getDescription(),member);
        postRepository.save(post);
    }

    public PostListResponse findAllPost() {

        List<PostResponse> postResponses = postRepository.findAll().stream()
                .map(it -> PostResponse.builder()
                        .description(it.getDescription())
                        .title(it.getTitle())
                        .memberId(it.getMember().getId())
                        .build()).toList();

        return PostListResponse.builder()
                .postCreateRequests(postResponses)
                .build();
    }



}
