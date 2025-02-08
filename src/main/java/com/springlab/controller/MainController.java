package com.springlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlab.domain.Account;
import com.springlab.dto.AccountForm;
import com.springlab.service.AccountService;
import com.springlab.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController { // 처음 화면,로그인

	private final AccountService accountService;
	private final BoardService boardService;

	public MainController(AccountService accountService, BoardService boardService) {
		this.accountService = accountService;
		this.boardService = boardService;
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

//	@PostMapping("/login")
//	public String accountVerification(@RequestParam("username") String username,
//			@RequestParam("password") String password, Model model, HttpSession session) {
//		Account account = new Account();
//		account.setUsername(username);
//		account.setPassword(password);
//
//		boolean isVerified = accountService.verification(account);
//
//		if (isVerified) {
//			session.setAttribute("username", username);
//			return "redirect:/boardList";
//		} else {
//			model.addAttribute("errorMessage", "아이디(로그인 전화번호, 로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
//			return "login";
//		}
//	}

	@PostMapping("/login") // entity에서 dto,entity 사용으로 변경.
	public String accountVerification(AccountForm form, Model model, HttpSession session) {
		Account account = form.toEntity();
		boolean isVerified = accountService.verification(account);
		System.out.println(isVerified);
		if (isVerified) {
			session.setAttribute("username", form.getUsername());
			return "redirect:/boardList";
		} else {
			model.addAttribute("errorMessage", "아이디(로그인 전화번호, 로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
			return "login";
		}
	}
}
