package com.shop.member.dto;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.shop.member.entity.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MemberFormDto {

	@NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String usersId;
	
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;
    
    private String Password2; //비밀번호확인 
   
    @Embedded
    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private Address address;
    
   
    


}