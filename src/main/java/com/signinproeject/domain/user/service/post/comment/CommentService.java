package com.signinproeject.domain.user.service.post.comment;

import com.signinproeject.domain.user.entity.Comment;
import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.domain.user.repository.CommentRepository;
import com.signinproeject.domain.user.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment newComment(CommentRequest commentRequest,Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트 아이디 " + postId + "는 없는 포스트 아이디 입니다."));
        Comment comment = new Comment(commentRequest.getContent(),post, LocalDateTime.now());

        return commentRepository.save(comment);

    }
}
