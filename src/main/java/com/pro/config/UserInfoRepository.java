package com.pro.config;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pro.pojo.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

	@Query("from UserInfo where email = :email")
	List<UserInfo> getUserInfoByEmail(String email);
}
