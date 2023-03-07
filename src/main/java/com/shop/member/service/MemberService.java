package com.shop.member.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.member.MemberRole;
import com.shop.member.dto.MemberFormDto;
import com.shop.member.entity.Address;
import com.shop.member.entity.Member;
import com.shop.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void save(MemberFormDto memberFormDto) {
		
		Member member = new Member();
		member.setUsersId(memberFormDto.getUsersId());
		member.setName(memberFormDto.getName());
		member.setPassword(this.passwordEncoder.encode(memberFormDto.getPassword()));
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(new Address());
		
		this.memberRepository.save(member);
		
	}
	
	//로그인을 처리하는 메소드 
	//user : 
	//오버라이딩 된 메소드 이므로 수정 불가 
	
}