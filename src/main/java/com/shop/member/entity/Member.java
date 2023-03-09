package com.shop.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
    private Long id; //자동으로 늘어나는값
    
    private String usersId; //아이디 
    private String name; 
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String password;
    
    //주소 
    @NotEmpty
    private String zipcode;
    @NotEmpty
    private String streetAdr;
    private String detailAdr;
   

    }

