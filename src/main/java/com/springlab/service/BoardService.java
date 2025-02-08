package com.springlab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springlab.domain.Board;
import com.springlab.repository.BoardRepository;

@Service
public class BoardService {

	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public void insert(Board board) {
		boardRepository.save(board);
	}

	public List<Board> findAll() {
		return (List<Board>) boardRepository.findAll();
	}

	public Optional<Board> findById(Long seq) {
		return boardRepository.findById(seq);
	}

	public void update(Board board) {
		boardRepository.save(board);
	}

	public void delete(Long seq) {
		boardRepository.deleteById(seq);
	}
}
