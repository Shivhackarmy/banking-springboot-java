package com.pro.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pro.config.AccountRepository;
import com.pro.config.UserInfoRepository;
import com.pro.daoimpl.AccountDaoImpl;
import com.pro.pojo.Account;
import com.pro.pojo.UserInfo;
@Controller
@Component
public class AccountController {
	
	@Autowired
	private UserInfoRepository uRepository;
	
	@Autowired
	private AccountRepository aRepository;
	
	@Autowired
	private AccountDaoImpl adaoimpl;

	AccountController(BaseController baseController, MailSender mailSender) {
	}
	
	@PostMapping("/updateStatus")
	public String updateStatus(
	        @RequestParam int accno,
	        @RequestParam String action)
	{
	    String status = "";

	    switch(action){
	        case "active": status = "active"; break;
	        case "freeze": status = "freeze"; break;
	        case "block": status = "block"; break;
	        case "unfreeze": status = "active"; break;
	        case "unfreeze_request": status = "active"; break;
	    }
	    if(action.equals("approve"))
	    {
	        if(adaoimpl.openAccount(accno))
	        {
	            //Account acc = hTemplate.get(Account.class, accno);
	        	Optional<Account>aop=aRepository.findById(accno);
	        	Account acc=aop.get();
	            
	            //UserInfo u = hTemplate.get(UserInfo.class, acc.getUid());
	        	Optional<UserInfo>uop=uRepository.findById(acc.getUid());
	        	UserInfo u=uop.get();

	            adaoimpl.approveAccount(u,accno);
	            MailSender.sendMail(acc, u);
	        }
	    }
	    else if(action.equals("reject"))
	    {
	    	//Account acc = hTemplate.get(Account.class, accno);
	    	Optional<Account>aop=aRepository.findById(accno);
        	Account acc=aop.get();
    		
			//List<UserInfo> user = (List<UserInfo>) hTemplate.findByNamedParam(
			//        "from userinfo where uid = :uid",
			//        "uid",
			//        acc.getUid());
        	Optional<UserInfo>uop=uRepository.findById(acc.getUid());
        	UserInfo u=uop.get();
    		//UserInfo u=(UserInfo)user;
    		rejectMail.sendMail(acc, u);
	    }
	    adaoimpl.updateStatus(accno, status);

	    return "redirect:/admin";
	}
	
	@PostMapping("/requestUnfreeze")
	public ModelAndView requestUnfreeze(@RequestParam int accno){

	    adaoimpl.requestUnfreeze(accno);

	    return new ModelAndView("redirect:/customer");
	}
	
	
}
