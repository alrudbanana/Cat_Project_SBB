package com.shop.board.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.board.Board;
import com.shop.board.service.BoardService;
import com.shop.member.entity.Member;
import com.shop.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final MemberService memberService;
	
	//글 리스트 
	@GetMapping("/member/community")
	public String mainPage(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
		Page<Board> paging = this.boardService.getList(page);
		model.addAttribute("paging", paging);
		
		return "/board/community";
	}
	//글 상세보기 
	//컨트롤러에서 호출시 객체를 템플릿에 전달 
	@GetMapping(value="/board/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Board board = this.boardService.getBoard(id);
		model.addAttribute("board", board);
		return "board/communityDetail";
	}
	
	//글쓰기 폼 전달
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/community/write")
	public String mainPage(Board board) {
		return "board/communityWrite";
	}
	
	//질문 저장 후 질문 목록 이동 
	 @PreAuthorize("isAuthenticated()")
	 @PostMapping("/community/write")
	 public String questionCreate(@Valid Board board, BindingResult bindingResult, Principal principal) {
	        if (bindingResult.hasErrors()) {
	            return "board/communityWrite";
	        }
	        Member member = this.memberService.getMember(principal.getName());
	        this.boardService.save(board.getSubject(), board.getContent(), member);
		 return "redirect:/member/community";
	 }

}
