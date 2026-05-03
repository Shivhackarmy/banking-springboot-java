package com.pro.dao;

import java.util.List;


import com.pro.pojo.Account;
import com.pro.pojo.UserInfo;

public interface AccountDao {
	
	boolean openAccount(int accno);

	void requestUnfreeze(int accno);

	List<Account> getAllAccounts();

	boolean updateStatus(int accno, String accstatus);

	List<Account> getActiveAccounts();

	List<UserInfo> getAllUsers();

	Account getAccountByUser(int uid);

	void approveAccount(UserInfo u,int accno);
	
}
