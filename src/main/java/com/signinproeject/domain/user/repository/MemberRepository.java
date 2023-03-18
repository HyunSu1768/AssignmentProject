package com.signinproeject.domain.user.repository;

import com.signinproeject.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByAccountId(String accountId);
}
