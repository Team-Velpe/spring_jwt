package com.velpe.jwtAuth.member.dao;

import com.velpe.jwtAuth.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
