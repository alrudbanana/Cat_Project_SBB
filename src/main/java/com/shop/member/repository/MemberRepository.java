package com.shop.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
}
