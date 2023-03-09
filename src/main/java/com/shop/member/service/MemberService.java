package com.shop.member.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import com.shop.member.entity.Member;
import com.shop.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService  {
	
	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	
	 public void save(MemberFormDto memberFromDto){
		 	Member member = new Member();
		 	
		 	member.setUsersId(memberFromDto.getUsersId()); //아이디 
		 	member.setName(memberFromDto.getName()); //이름 
		 	member.setPassword(this.passwordEncoder.encode(memberFromDto.getPassword()));
		 	member.setEmail(memberFromDto.getEmail());//이메일
		 	member.setZipcode(memberFromDto.getZipcode()); //주소 -1
		 	member.setStreetAdr(memberFromDto.getStreetAdr()); //주소 -2
		 	member.setDetailAdr(memberFromDto.getDetailAdr()); //주소 -3
	        
	        this.memberRepository.save(member);
	    }


	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    	System.out.println(email); //콘솔에 정보를 출력함 : 개발 완료 시는 제거함 
			
			Optional<Member> _Member=this.memberRepository.findByEmail(email);
			
			if(_Member.isEmpty()) {
				
				System.out.println("비어있음");
				throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
				
			}
			
			Member member = _Member.get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			if("admin".equals(email)) {
				authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
			}else {
				authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
			}
			
	        	
	    	
	    	return new User(member.getEmail(),member.getPassword(),authorities);
	    }

	}