package com.pro.config;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.pro.pojo.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

	//@Query("from Account where accno = :accno")
	//List<Transaction> getTransactionByAccNo(int accno);
	List<Transaction> findByAccno(int accno);
}
