package com.shop.member.controller;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.member.dto.MemberFormDto;
import com.shop.member.entity.Member;
import com.shop.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
	
    private final MemberService memberService;

    //회원가입 뷰 페이지 출력
    @GetMapping(value = "/join")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "join";
    }
    
    //로그인 
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "login";
    }
    
//   @PostMapping("/join")
//    public String test(Model model, MemberFormDto memberFormDto) {
//    	try {
//    		this.memberService.saveMember(memberFormDto);
//    	}catch(Exception e) {
//    		return "join";
//    		
//    	}
//    	return "redirect:/"; 
//    }
    
    @PostMapping("/join")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
       
    	System.out.println("컨트롤러 호출됨 ");
    	System.out.println(memberFormDto.getName());
    	
    	if(bindingResult.hasErrors()){
            return "join";
        }
    	
       try {
    	 
    	  this.memberService.save(memberFormDto);
    	  
       }catch(Exception e) {
    	   model.addAttribute("save_errors","아이디 혹은 이메일 중복");
    	   return "join";
       }

        return "redirect:/";
    }
 
    
   

}