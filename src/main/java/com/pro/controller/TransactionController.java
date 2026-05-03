package com.pro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pro.config.AccountRepository;
import com.pro.config.TransactionRepository;
import com.pro.daoimpl.TransactionDaoImpl;
import com.pro.pojo.Account;
import com.pro.pojo.Transaction;
import com.pro.pojo.UserInfo;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {
	
	@Autowired
	private AccountRepository aRepository;
	
	@Autowired
	private TransactionRepository tRepository;
	
	@Autowired
	private TransactionDaoImpl daoimpl;
	
	@PostMapping("/requestTransaction")
	public ModelAndView requestTransaction(
	        @RequestParam int accno,
	        @RequestParam double amount,
	        @RequestParam String type)
	{
	    Transaction lastTx = daoimpl.getLastTransaction(accno);

	    
	    if(lastTx != null && "pending".equals(lastTx.getTstatus())){
	        return new ModelAndView("redirect:/customer?msg=pending");
	    }

	    
	    //Account acc = hTemplate.get(Account.class, accno);
	    Optional<Account>aop=aRepository.findById(accno);
    	Account acc=aop.get();
	    if("withdraw".equals(type) && acc.getBal() < amount){
	        return new ModelAndView("redirect:/customer?msg=insufficient");
	    }

	    daoimpl.requestTransaction(accno, amount, type);

	    return new ModelAndView("redirect:/customer?msg=success");
	}
	@GetMapping("/transactionHistory")
	public ModelAndView history(HttpSession session){

	    UserInfo u = (UserInfo)session.getAttribute("user");

	    
	    if(u == null){
	        return new ModelAndView("redirect:/login");
	    }

	    Account acc = daoimpl.getAccountByUser(u.getUsername());

	    List<Transaction> list = daoimpl.getTransactions(acc.getAccno());

	    ModelAndView mv = new ModelAndView("history");
	    mv.addObject("list", list);

	    return mv;
	}
	@PostMapping("/approveTx")
	public String approveTx(@RequestParam("tid") int tid){

	    daoimpl.approveTransaction(tid);

	    return "redirect:/admin";
	}
	@PostMapping("/rejectTx")
	public String rejectTx(@RequestParam("tid") int tid){

	    daoimpl.rejectTransaction(tid);

	    return "redirect:/admin";
	}
	@PostMapping("/adminTransaction")
	public String adminTransaction(
	        @RequestParam("accno") int accno,
	        @RequestParam("amount") double amount,
	        @RequestParam("type") String type)
	{
	    //Account acc = hTemplate.get(Account.class, accno);
		Optional<Account>aop=aRepository.findById(accno);
    	Account acc=aop.get();

	    boolean status = daoimpl.adminTransaction(accno, amount, type);

	    if(!status){
	        return "redirect:/accountprofiles?uid=" + acc.getUid() + "&msg=insufficient";
	    }

	    return "redirect:/accountprofiles?uid=" + acc.getUid() + "&msg=success";
	}
	
}
