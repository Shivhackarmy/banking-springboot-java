package com.pro.dao;

import java.util.List;

import com.pro.pojo.Account;
import com.pro.pojo.Transaction;

public interface TransactionDao {

	boolean doTransaction(int accno, double tamount, String ttype);

	List<Account> getAllAccounts();

	Account getAccountByUser(String username);

	List<Transaction> getTransactions(int accno);

	Transaction getLastTransaction(int accno);

	boolean requestTransaction(int accno, double amount, String type);

	void approveTransaction(int tid);

	void rejectTransaction(int tid);

	boolean adminTransaction(int accno, double amount, String type);
	
	
}
