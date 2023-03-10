package com.shop.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.DataNotFoundException;
import com.shop.board.Board;
import com.shop.board.repository.BoardRepository;
import com.shop.member.entity.Member;
import com.shop.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardService {
	 
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	
	public List<Board> getboardList(){
		return this.boardRepository.findAll();
	}
	
	public void save(String subject , String content, Member member) {
		Board b = new Board();
		b.setAuthor(member);
		b.setContent(content);
		b.setCreateDate(LocalDateTime.now());
		b.setSubject(subject);
		this.boardRepository.save(b)
		;
		
	}
	//id값으로 글 데이터 조회
	 public Board getBoard(Integer id) {  
	        Optional<Board> board = this.boardRepository.findById(id);
	        if (board.isPresent()) {
	            return board.get();
	        } else {
	            throw new DataNotFoundException("question not found");
	        }
	    }

	 
	//페이징처리 
	 public Page<Board> getList(int page) {
		 List<Sort.Order> sorts = new ArrayList<>();
	        sorts.add(Sort.Order.desc("createDate"));
	        Pageable pageable = PageRequest.of(page, 10);
	        return this.boardRepository.findAll(pageable);
	    }
	
	
	}
	

