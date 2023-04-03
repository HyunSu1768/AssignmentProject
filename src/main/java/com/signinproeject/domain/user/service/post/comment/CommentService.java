package com.signinproeject.domain.user.service.post.comment;

import com.signinproeject.domain.user.entity.Comment;
import com.signinproeject.domain.user.entity.Member;
import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.domain.user.repository.CommentRepository;
import com.signinproeject.domain.user.repository.MemberRepository;
import com.signinproeject.domain.user.repository.PostRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final MemberRepository memberRepository;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Transactional
    public Comment newComment(CommentRequest commentRequest,Long postId){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트 아이디 " + postId + "는 없는 포스트 아이디 입니다."));
        Comment comment = new Comment(commentRequest.getContent(),post, LocalDateTime.now(),member);

        return commentRepository.save(comment);

    }

    @Transactional
    public void updateComment(Long commentId,Long memberId,CommentUpdateRequest commentUpdateRequest){

        Member member = memberRepository.findById(memberId)
                .orElseThrow( () -> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));

        if(!Objects.equals(member.getId(), comment.getMember().getId())){
            throw new IllegalStateException("접근할 수 없습니다.");
        }

        comment.editComment(commentUpdateRequest.getContent());

        commentRepository.save(comment);

    }

}
