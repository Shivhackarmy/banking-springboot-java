package com.pro.dao;

import java.util.List;

import com.pro.pojo.UserInfo;

public interface UserInfoDao {
	
	UserInfo checkUser(UserInfo u);
	void updateUser(UserInfo u);
	boolean openAccountByCust(UserInfo u);
	boolean openAccountByAdmin(UserInfo u);
	boolean userStatus(int uid, String role);
	String changePassword(String email, String oldpass, String newpass);
	
}
