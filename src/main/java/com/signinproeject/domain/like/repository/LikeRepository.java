package com.signinproeject.domain.like.repository;

import com.signinproeject.domain.like.entity.Likes;
import com.signinproeject.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {

    void deleteByMemberIdAndPost(Long memberId,Post post);

    Likes findByMemberIdAndPost(Long memberId, Post post);
}
