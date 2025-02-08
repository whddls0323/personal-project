package com.springlab.dto;

import com.springlab.domain.Account;

public class AccountForm {

	private String username;
	private String password;

	public AccountForm() {
	}

	public AccountForm(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account toEntity() {
		return new Account(username, password);
	}
}
