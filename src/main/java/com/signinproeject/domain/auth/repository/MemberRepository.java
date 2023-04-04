package com.signinproeject.domain.auth.repository;

import com.signinproeject.domain.user.entity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByName(String name);
    Optional<Member> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}
