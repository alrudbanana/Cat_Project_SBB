package com.shop.member.entity;


import com.shop.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Table(name = "member")
@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id; //자동으로 늘어나는값
    
    private String usersId; //아이디 
    private String name; 
    
    @Email
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

