package com.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.member.MemberRole;
import com.shop.member.entity.Member;
import com.shop.member.repository.MemberRepository;

@SpringBootTest
class CatShopApplicationTests {

	@Autowired
	private MemberRepository memberRepository;
	@Test
	void contextLoads() {
	}

	
	@Test
	void insert() {
		Member q1 = new Member(); 
		q1.setName("이름입니다.");
		q1.setPassword("1234");
		q1.setRole(MemberRole.ADMIN); 
		q1.setUsersId("아이디입니다");
		q1.setZipcode("12312");
		q1.setStreetAdr("도로명주소");
		q1.setDetailAdr("상세주소");
		q1.setEmail("aaa@aa.aa");
		this.memberRepository.save(q1); 
				
	}
}
