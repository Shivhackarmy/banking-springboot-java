package com.pro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pro.config.AccountRepository;
import com.pro.config.TransactionRepository;
import com.pro.config.UserInfoRepository;
import com.pro.daoimpl.AccountDaoImpl;
import com.pro.daoimpl.TransactionDaoImpl;
import com.pro.pojo.Account;
import com.pro.pojo.Transaction;
import com.pro.pojo.UserInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class BaseController {
	
	@Autowired
	private UserInfoRepository uRepository;
	
	@Autowired
	private AccountRepository aRepository;
	
	@Autowired
	private TransactionRepository tRepository;
	
	@Autowired
	private AccountDaoImpl adaoimpl;
	@Autowired
	private TransactionDaoImpl tdaoimpl;
	
	@GetMapping("/")
	public String indexPage()
	{
		return "index";
	}
	@GetMapping("/reg")
	public String regPage()
	{
		return "reg";
	}
	@GetMapping("/regadmin")
	public String regAdminPage()
	{
		return "regadmin";
	}
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	@GetMapping("/changepass")
	public String passChange()
	{
		return "changepass";
	}
	@GetMapping("/logout")
	public String logoutPage(Model m,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
			session.invalidate();
		}
		
		return "login";
	}
	@GetMapping("/admin")
	public ModelAndView loadAdmin(HttpSession session)
	{
	    UserInfo u = (UserInfo) session.getAttribute("user");

	    
	    if(u == null){
	        return new ModelAndView("redirect:/login");
	    }

	    
	    if(!"ADMIN".equals(u.getRole())){
	        return new ModelAndView("redirect:/customer");
	    }

	    
	    List<UserInfo> users = adaoimpl.getAllUsers();
	    List<Account> accounts = adaoimpl.getAllAccounts();
	    List<Account> activeAccounts = adaoimpl.getActiveAccounts();

	    ModelAndView mv = new ModelAndView("admin"); 

	    mv.addObject("users", users);
	    mv.addObject("accounts", accounts);
	    mv.addObject("activeAccounts", activeAccounts);

	    return mv;
	}
	@GetMapping("/customer")
	public ModelAndView loadCustomer(HttpSession session){

	    UserInfo u = (UserInfo) session.getAttribute("user");

	    if(u == null){
	        return new ModelAndView("redirect:/login");
	    }

	    
	    Account acc = adaoimpl.getAccountByUser(u.getUid());

	    ModelAndView mv = new ModelAndView("customer");

	   
	    if(acc == null){
	        mv.addObject("account", null);
	        mv.addObject("tx", null);
	        mv.addObject("lastTx", null);
	        mv.addObject("msg", "No Account Found");
	        return mv;
	    }

	    
	    List<Transaction> tlist = tdaoimpl.getTransactions(acc.getAccno());
	    Transaction lastTx = tdaoimpl.getLastTransaction(acc.getAccno());

	    mv.addObject("account", acc);
	    mv.addObject("tlist", tlist);
	    mv.addObject("lastTx", lastTx);

	    return mv;
	}
	@RequestMapping("/accountprofiles")
	public ModelAndView userProfile(
	        @RequestParam("uid") int uid,
	        @RequestParam(value="msg", required=false) String msg)
	{
	    //UserInfo up = hTemplate.get(UserInfo.class, uid);
		Optional<UserInfo>uop=uRepository.findById(uid);
		UserInfo up=uop.get();

	    //Account ap = (Account) hTemplate.findByNamedParam(
	    //    "from Account where uid = :uid",
	    //    "uid",
	    //    uid).get(0);
		List<Account>aop=aRepository.getAccountByUser(uid);
		Account ap=aop.get(0);
	    
	    //List<Transaction> tlist = (List<Transaction>) hTemplate.findByNamedParam(
	    //    "from Transaction where accno = :accno",
	    //    "accno",
	    //    ap.getAccno());
		List<Transaction>tlist=tRepository.findByAccno(ap.getAccno());

	    ModelAndView mv = new ModelAndView("accountprofiles");

	    mv.addObject("up", up);
	    mv.addObject("ap", ap);
	    mv.addObject("tlist", tlist);
	    mv.addObject("msg", msg);   

	    return mv;
	}
}

