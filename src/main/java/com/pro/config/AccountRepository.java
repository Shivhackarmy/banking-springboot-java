package com.pro.config;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pro.pojo.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	
	@Query("from Account where uid = :uid")
	List<Account> getAccountByUser(int uid);
	@Query("from Account where accstatus = :accstatus")
	List<Account> getAccountByAccountStatus(String accstatus);
	@Query("from Account where accname = :accname")
	List<Account> getAccountByAccName(String accname);
	
}
