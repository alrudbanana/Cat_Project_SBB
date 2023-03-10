package com.shop.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.board.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	Board findBySubject(String subject);
	Board findBySubjectAndContent(String subject, String content);
	List<Board> findBySubjectLike(String subject);
	
	Page<Board> findAll(Pageable pageable); 
}
