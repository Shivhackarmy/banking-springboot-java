package com.pro.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.pro.config.AccountRepository;
import com.pro.config.UserInfoRepository;
import com.pro.dao.AccountDao;
import com.pro.pojo.Account;
import com.pro.pojo.UserInfo;

import jakarta.transaction.Transactional;

@Component
public class AccountDaoImpl implements AccountDao{

	@Autowired
	private AccountRepository aRepository;
	
	@Autowired
	private UserInfoRepository uRepository;
	
	@Override
	@Transactional
	public boolean updateStatus(int accno, String accstatus) {
	    try {
	        //Account acc = hTemplate.get(Account.class, accno);
	    	Optional<Account>op=aRepository.findById(accno);
	        
	        if(op.isPresent()){
	        	Account acc=op.get();
	            acc.setAccstatus(accstatus);
	            aRepository.save(acc);
	            return true;
	        }
	        return false;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	@Override
	@Transactional
	public List<Account> getAllAccounts() {
	    //return hTemplate.loadAll(Account.class);
		return (List<Account>) aRepository.findAll();
	}
	@Override
	@Transactional
	public Account getAccountByUser(int uid){

	    //List<Account> list = (List<Account>) hTemplate.findByNamedParam(
	    //    "from Account where uid = :uid",
	    //    "uid",
	    //    uid);
		List<Account> list= aRepository.getAccountByUser(uid);

	    return list.isEmpty() ? null : list.get(0);
	}
	@Override
	@Transactional
	public void requestUnfreeze(int accno){
	    //Account acc = hTemplate.get(Account.class, accno);
		Optional<Account>op=aRepository.findById(accno);
		Account acc=op.get();
	    acc.setAccstatus("unfreeze_requested");
	    aRepository.save(acc);
	}

	@Override
	@Transactional
	public List<UserInfo> getAllUsers(){
	    //return hTemplate.loadAll(UserInfo.class);
		return (List<UserInfo>) uRepository.findAll();
	}

	@Override
	@Transactional
	public List<Account> getActiveAccounts(){
	    //return (List<Account>) hTemplate.findByNamedParam(
	    //    "from Account where accstatus = :status",
	    //    "status",
	    //    "active");
		
		return (List<Account>)aRepository.getAccountByAccountStatus("active");
	}
	@Override
	@Transactional
	public boolean openAccount(int accno) {
		
		//Account acc = hTemplate.get(Account.class, accno);
		Optional<Account>op=aRepository.findById(accno);
		Account acc=op.get();
	    acc.setAccstatus("active");
	    aRepository.save(acc);
	    return true;
	}
	@Override
	@Transactional
	public void approveAccount(UserInfo u,int accno) {
		
		//Account acc=hTemplate.get(Account.class, accno);
		Optional<Account>op=aRepository.findById(accno);
		Account acc=op.get();
		acc.setAccstatus("active");
		u.setRole("CUSTOMER");
		aRepository.save(acc);
		uRepository.save(u);
	}	
	
}
