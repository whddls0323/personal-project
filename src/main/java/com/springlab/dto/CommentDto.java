package com.springlab.dto;

import java.sql.Timestamp;

import com.springlab.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {

	private Long id;
	private Long boardId;
	private String nickname;
	private String body;
	private Timestamp regdate;

	public static CommentDto createCommentDto(Comment comment) {
		return new CommentDto(comment.getId(), comment.getBoard().getSeq(), comment.getNickname(), comment.getBody(),
				comment.getRegdate());
	}
}
