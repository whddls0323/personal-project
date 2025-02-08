package com.springlab.service;

import org.springframework.stereotype.Service;

import com.springlab.domain.Account;
import com.springlab.repository.AccountRepository;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public boolean verification(Account account) {
		Account check = accountRepository.findByAccount(account.getUsername(), account.getPassword());

		if (check != null)
			return true;
		else
			return false;
	}

	public void insert(Account account) {
		accountRepository.save(account);
	}
}
