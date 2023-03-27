package com.signinproeject.domain.user.service.post.like;

import com.signinproeject.domain.user.entity.Likes;
import com.signinproeject.domain.user.entity.Post;
import com.signinproeject.domain.user.repository.LikeRepository;
import com.signinproeject.domain.user.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    @Transactional
    public void addLike(Long memberId, Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));

        Likes likes = new Likes(memberId,post);

        likeRepository.save(likes);
    }
}
