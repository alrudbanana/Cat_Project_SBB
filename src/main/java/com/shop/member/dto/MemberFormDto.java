package com.shop.member.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberFormDto {

	@NotEmpty(message = "아이디는 필수 입력 값입니다.")
    private String usersId;
	
	@NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;
   
 
	@NotEmpty(message = "주소는 필수 입력 값입니다.")
	private String zipcode;
    private String streetAdr;
    private String detailAdr;
    
   
    


}