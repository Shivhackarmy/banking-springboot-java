package com.pro.daoimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pro.config.AccountRepository;
import com.pro.config.UserInfoRepository;
import com.pro.controller.MailSender;
import com.pro.dao.UserInfoDao;
import com.pro.pojo.Account;
import com.pro.pojo.UserInfo;
@Component
public class UserInfoDaoImpl implements UserInfoDao{

	@Autowired 
	private UserInfoRepository uRepository;
	
	@Autowired
	private AccountRepository aRepository;
	
	@Override
	public boolean userStatus(int uid, String role) {
	    try {
	        //UserInfo u = hTemplate.get(UserInfo.class, uid);
	        Optional<UserInfo>op=uRepository.findById(uid);
	        UserInfo u=op.get();
	        if(op.isPresent()){
	            u.setRole(role);
	            uRepository.save(u);
	            return true;
	        }
	        return false;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	@Override
	public boolean openAccountByAdmin(UserInfo u) {
		try {
			//List<UserInfo> list = (List<UserInfo>) hTemplate.findByNamedParam(
		    //      "from UserInfo where email = :email",
		    //      "email",
		    //      u.getEmail());
			List<UserInfo> list= uRepository.getUserInfoByEmail(u.getEmail());
			if(!list.isEmpty())
			{
				return false;
			}
			else
			{
				uRepository.save(u);
				Date dt= new Date();
				String sdt=dt.toString();
				
				Account a= new Account();
				a.setUid(u.getUid());
				a.setAccname(u.getUsername());
				a.setAcctype(u.getAcctype());
				a.setBal(u.getBal());
				a.setAccdate(sdt);
				a.setAccstatus("active");
				aRepository.save(a);
				
				MailSender.sendMail(a, u);
				return true;
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean openAccountByCust(UserInfo u) 
	{
		try {
			//@SuppressWarnings({ "unchecked", "deprecation" })
			//List<UserInfo> list = (List<UserInfo>) hTemplate.findByNamedParam(
		    //        "from UserInfo where email = :email",
		    //        "email",
		    //        u.getEmail());
			List<UserInfo> list= uRepository.getUserInfoByEmail(u.getEmail());
			if(!list.isEmpty())
			{
				return false;
			}
			else
			{
				uRepository.save(u);
				Date dt= new Date();
				String sdt=dt.toString();
				
				Account a= new Account();
				a.setUid(u.getUid());
				a.setAccname(u.getUsername());
				a.setAcctype(u.getAcctype());
				a.setBal(u.getBal());
				a.setAccdate(sdt);
				a.setAccstatus("request");
				aRepository.save(a);
				return true;
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public UserInfo checkUser(UserInfo u) {
	    try {

	        //List<UserInfo> list = (List<UserInfo>) hTemplate.findByNamedParam(
	        //    "from UserInfo where email = :email",
	        //    "email",
	        //    u.getEmail());
	    	
	    	List<UserInfo> list= uRepository.getUserInfoByEmail(u.getEmail());
	        if(!list.isEmpty()){
	            UserInfo user = list.get(0);

	            if(u.getPassword().equals(user.getPassword())){
	                return user;
	            }
	        }

	        return null;

	    } catch (Exception e){
	        e.printStackTrace();
	        return null;
	    }
	}
	@Override
	public void updateUser(UserInfo u){
	    uRepository.save(u);
	}
	
	
	@Override
	public String changePassword(String email, String oldpass, String newpass){

	    try{
	        //List<UserInfo> list = (List<UserInfo>) hTemplate.findByNamedParam(
	        //        "from UserInfo where email = :email",
	        //        "email",
	        //        email);

	    	List<UserInfo> list= uRepository.getUserInfoByEmail(email);
	        // 🔥 USER NOT FOUND
	        if(list.isEmpty()){
	            return "User Not Found";
	        }

	        UserInfo user = list.get(0);

	        // 🔥 CHECK OLD PASSWORD
	        if(!user.getPassword().equals(oldpass)){
	            return "Previous Password Not Match";
	        }

	        // 🔥 UPDATE PASSWORD
	        user.setPassword(newpass);
	        uRepository.save(user);

	        return "success";
	    }
	    catch(Exception e){
	        e.printStackTrace();
	        return "Error While Updating Password";
	    }
	}
		
}
