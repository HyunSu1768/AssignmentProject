package com.signinproeject.domain.user.repository;

import com.signinproeject.domain.user.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {
}
