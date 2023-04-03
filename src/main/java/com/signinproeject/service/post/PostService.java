package com.signinproeject.service.post;

import com.signinproeject.domain.user.Request.PostCreateRequest;
import com.signinproeject.domain.user.Request.PostUpdateRequest;
import com.signinproeject.domain.user.Response.PostListResponse;
import com.signinproeject.domain.user.Response.PostResponse;
import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.repository.MemberRepository;
import com.signinproeject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createPost(PostCreateRequest postCreateRequest){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));

        Post post = new Post(postCreateRequest.getTitle(),postCreateRequest.getDescription(),member);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long postId, Long memberId, PostUpdateRequest postUpdateRequest){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));
        if(!Objects.equals(post.getMember().getId(), memberId)){
            throw new IllegalStateException();
        }

        post.editPost(postUpdateRequest);

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
