package com.springlab.dto;

import java.sql.Timestamp;

import com.springlab.domain.Board;

public class BoardForm {

	private String title;
	private String writer;
	private String content;
	private Timestamp regdate;
	private int cnt;

	public BoardForm() {
	}

	public BoardForm(String title, String writer, String content, Timestamp regdate, int cnt) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.cnt = cnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Board toEntity() {
		return new Board(title, writer, content, regdate, cnt);
	}
}
