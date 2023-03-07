package com.shop.member.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.OverridesAttribute;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.member.MemberRole;
import com.shop.member.dto.MemberFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String usersId;
    private String name;
    @Column(unique = true)
    private String email;

    private String password;

    @Embedded
    private Address address;
    

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    
    
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setUsersId(memberFormDto.getUsersId());
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(MemberRole.ADMIN);
        return member;

    }
}
