package com.signinproeject.repository;

import com.signinproeject.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByName(String name);
    Optional<Member> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}
