package com.signinproeject.domain.post.service;

import com.signinproeject.domain.comment.controller.dto.response.CommentResponse;
import com.signinproeject.domain.like.controller.dto.response.LikeResponse;
import com.signinproeject.domain.post.controller.dto.request.PostCreateRequest;
import com.signinproeject.domain.post.controller.dto.response.PostListResponse;
import com.signinproeject.domain.post.controller.dto.response.PostResponse;
import com.signinproeject.domain.post.entity.SortType;
import com.signinproeject.domain.post.repository.PostRepository;
import com.signinproeject.domain.post.controller.dto.request.PostUpdateRequest;
import com.signinproeject.domain.user.entity.entity.Member;
import com.signinproeject.domain.post.entity.Post;
import com.signinproeject.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public void updatePost(Long postId, Long memberId, PostUpdateRequest request){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));
        if(!Objects.equals(post.getMember().getId(), memberId)){
            throw new IllegalStateException();
        }

        post.editPost(request);

        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    public PostListResponse findAllPost() {

        List<PostResponse> postResponses = postRepository.findAll().stream()
                .map(it -> PostResponse.builder()
                        .like(it.getLikes().size())
                        .description(it.getDescription())
                        .title(it.getTitle())
                        .memberId(it.getMember().getId())
                        .build()).toList();

        return PostListResponse.builder()
                .postResponseList(postResponses)
                .build();
    }
    public PostListResponse findAllPostSort(Pageable pageable, SortType sortType){
        Page<Post> posts = postRepository.findAllByOrderByLikeCountDesc(pageable);
        if(sortType==SortType.like){
            posts = postRepository.findAllByOrderByLikeCountDesc(pageable);
        }else if(sortType==SortType.view){
            posts = postRepository.findAllByOrderByViewCountDesc(pageable);
        }else{
            posts = postRepository.findAllByOrderByIdAsc(pageable);
        }

        List<PostResponse> postResponses = posts
                .stream()
                .map(it -> PostResponse.builder()
                        .viewCount(it.getViewCount())
                        .like(it.getLikes().size())
                        .description(it.getDescription())
                        .title(it.getTitle())
                        .memberId(it.getMember().getId())
                        .commentResponses(it.getComments().stream()
                                .map(CommentResponse::of)
                                .toList())
                        .likeResponses(it.getLikes().stream()
                                .map(LikeResponse::of)
                                .toList())
                        .build()).toList();

        return PostListResponse.builder()
                .postResponseList(postResponses)
                .build();
    }

    @Transactional
    public PostResponse findOne(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트를 찾을 수 없습니다."));

        post.addView();

        return PostResponse.builder()
                .commentResponses(post.getComments().stream()
                        .map(CommentResponse::of)
                        .toList())
                .likeResponses(post.getLikes().stream()
                        .map(LikeResponse::of)
                        .toList())

                .viewCount(post.getViewCount())
                .like(post.getLikeCount())
                .title(post.getTitle())
                .description(post.getDescription())
                .memberId(post.getId())
                .build();


    }


}
