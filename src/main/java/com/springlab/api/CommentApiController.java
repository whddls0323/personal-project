package com.springlab.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springlab.dto.CommentDto;
import com.springlab.service.CommentService;

@RestController
public class CommentApiController {

	private final CommentService commentService;

	public CommentApiController(CommentService commentService) {
		this.commentService = commentService;
	}

//	@GetMapping("/api/board/{boardId}/comments")
//	public ResponseEntity<List<CommentDto>> comments(@PathVariable("boardId") Long boardId) {
//		List<CommentDto> dtos = commentService.comments(boardId);
//		return ResponseEntity.status(HttpStatus.OK).body(dtos);
//	}

	@PostMapping("/api/board/{boardId}/comments")
	public ResponseEntity<CommentDto> create(@PathVariable("boardId") Long boardId, @RequestBody CommentDto dto) {
		CommentDto createdDto = commentService.create(boardId, dto);
		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}

	@PatchMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> update(@PathVariable("id") Long id, @RequestBody CommentDto dto) {
		System.out.println("dto 테스트중: " + dto);
		CommentDto updatedDto = commentService.update(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}

	@DeleteMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> delete(@PathVariable("id") Long id) {
		CommentDto deletedDto = commentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
	}
}
