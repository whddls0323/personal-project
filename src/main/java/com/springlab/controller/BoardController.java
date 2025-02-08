package com.springlab.controller;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlab.domain.Board;
import com.springlab.dto.BoardForm;
import com.springlab.dto.CommentDto;
import com.springlab.service.BoardService;
import com.springlab.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;

	@GetMapping("/boardList")
	public String boardList(HttpSession session, Model model) {
		List<Board> boardList = boardService.findAll();
		boardList.sort(Comparator.comparingLong(Board::getSeq).reversed());

		// 서버가 재시작될 때마다 새로운 토큰 생성
		String serverToken = (String) session.getAttribute("serverToken");
		if (serverToken == null) {
			serverToken = UUID.randomUUID().toString();
			session.setAttribute("serverToken", serverToken);
		}

		model.addAttribute("boardList", boardList);
		model.addAttribute("serverToken", serverToken);
		return "boardList";
	}

	@GetMapping("/insert")
	public String insertPage(Model model) {
		return "insert";
	}

//	@PostMapping("/insert")
//	public String insertBoard(@RequestParam("title") String title, @RequestParam("content") String content,
//			HttpSession session, Model model) {
//		Board board = new Board();
//		board.setTitle(title);
//		String writer = (String) session.getAttribute("username");
//		board.setWriter(writer);
//		board.setContent(content);
//		boardService.insert(board);
//		return "redirect:/boardList";
//	}

	@PostMapping("/insert") // entity -> dto,entity 사용
	public String insertBoard(BoardForm form, HttpSession session, Model model) {
		String writer = (String) session.getAttribute("username");
		form.setWriter(writer);
		Board board = form.toEntity();
		boardService.insert(board);
		return "redirect:/boardList";
	}

	@GetMapping("/update/{seq}")
	public String updatePage(@PathVariable("seq") Long seq, Model model) {
		Optional<Board> board = boardService.findById(seq);
		List<CommentDto> commentDtos = commentService.comments(seq);

		if (board.isPresent()) {
			model.addAttribute("board", board.get());
			model.addAttribute("commentDtos", commentDtos);
		} else
			model.addAttribute("board", null);
		return "update";
	}

//	@PostMapping("/update/{seq}")
//	public String updateBoard(@PathVariable("seq") int seq, @RequestParam("title") String title,
//			@RequestParam("content") String content, HttpSession session, Model model) {
//		Optional<Board> optionalBoard = boardService.findById(seq);
//		if (optionalBoard.isPresent()) {
//			Board board = optionalBoard.get();
//			board.setTitle(title);
//			String writer = (String) session.getAttribute("username");
//			board.setWriter(writer);
//			board.setContent(content);
//			board.setRegdate(new Timestamp(System.currentTimeMillis()));
//
//			boardService.update(board);
//		}
//
//		return "redirect:/boardList";
//	}

	@PostMapping("/update/{seq}") // entity -> dto,entity 사용
	public String updateBoard(@PathVariable("seq") Long seq, BoardForm form, HttpSession session, Model model) {
		Optional<Board> optionalBoard = boardService.findById(seq);
		if (optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.setTitle(form.getTitle());
			String writer = (String) session.getAttribute("username");
			board.setWriter(writer);
			board.setContent(form.getContent());
			board.setRegdate(new Timestamp(System.currentTimeMillis()));

			boardService.update(board);
		}

		return "redirect:/boardList";
	}

	@GetMapping("/delete/{seq}")
	public String deletePage(@PathVariable("seq") Long seq, Model model) {
		boardService.delete(seq);
		return "redirect:/boardList";
	}
}
