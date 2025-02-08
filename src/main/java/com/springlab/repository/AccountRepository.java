package com.springlab.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springlab.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	@Query("select a from Account a where a.username = ?1 AND a.password = ?2")
	Account findByAccount(String username, String password);
}
