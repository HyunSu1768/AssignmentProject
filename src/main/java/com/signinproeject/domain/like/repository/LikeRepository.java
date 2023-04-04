package com.signinproeject.domain.like.repository;

import com.signinproeject.domain.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {
}
