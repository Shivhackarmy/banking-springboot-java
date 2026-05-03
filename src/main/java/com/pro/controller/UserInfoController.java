package com.pro.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pro.daoimpl.UserInfoDaoImpl;
import com.pro.pojo.UserInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserInfoController {
	
	@Autowired 
	private UserInfoDaoImpl udaoimpl;
	
	@PostMapping("/userStatus")
	public String userStatus(
	        @RequestParam("uid")int uid,
	        @RequestParam("action")String action)
	{
	    String role = "";

	    switch(action){
	        case "block": role = "block"; break;
	        case "unblock": role = "CUSTOMER"; break;
	    }

	    udaoimpl.userStatus(uid, role);

	    return "redirect:/admin";
	} 
	@PostMapping("/updateProfile")
	@ResponseBody
	public String updateProfile(
	        @RequestParam("username") String username,
	        @RequestParam("dp") MultipartFile dp,
	        HttpSession session)
	{
	    try {

	        UserInfo u = (UserInfo) session.getAttribute("user");

	        // 🔥 file name generate (avoid duplicate)
	        String fileName = System.currentTimeMillis() + "_" + dp.getOriginalFilename();

	        // 🔥 save path
	        String path = session.getServletContext().getRealPath("C:\\Users\\shivg\\OneDrive\\Desktop\\Project\\Banking-My-App\\src\\main\\webapp\\resources\\dp\\") + fileName;

	        File file = new File(path);
	        dp.transferTo(file);

	        // 🔥 update object
	        u.setUsername(username);
	        u.setDp(fileName);

	        udaoimpl.updateUser(u);

	        session.setAttribute("user", u);

	        return "success";

	    } catch(Exception e){
	        e.printStackTrace();
	        return "fail";
	    }
	}
	
	@PostMapping("/openByCust")
	public ModelAndView openAccountByCust(
			@RequestParam("username")String username,
			@RequestParam("email")String email,
			@RequestParam("mobile")String mobile,
			@RequestParam("gender")String gender,
			@RequestParam("branch")String branch,
			@RequestParam("dob")String dob,
			@RequestParam("address")String address,
			@RequestParam("acctype")String acctype,
			@RequestParam("bal")double bal,
			@RequestParam("pan")String pan)
	{
		UserInfo u=new UserInfo();
		u.setUsername(username);
		u.setEmail(email);
		u.setMobile(mobile);
		u.setGender(gender);
		u.setBranch(branch);
		u.setDob(dob);
		u.setAddress(address);
		u.setAcctype(acctype);
		u.setBal(bal);
		u.setPan(pan);
		u.setPassword(username+"@1234");
		u.setDp("default.jpg");
		u.setRole("process");
		ModelAndView mv=null;
		if(udaoimpl.openAccountByCust(u))
		{
			mv =  new ModelAndView("reg","msg","<font size=5 color=green >Your Open Account Request Send<br>Wail Until Approval</font>");
		}
		else
		{
			mv =  new ModelAndView("reg","msg","<font size=5 color=red >User Could Not Be Register , Already Exist</font>");
		}
		return mv;
	}
	@PostMapping("/openByadmin")
	public ModelAndView openAccountByAdmin(
			@RequestParam("username")String username,
			@RequestParam("email")String email,
			@RequestParam("mobile")String mobile,
			@RequestParam("gender")String gender,
			@RequestParam("branch")String branch,
			@RequestParam("dob")String dob,
			@RequestParam("address")String address,
			@RequestParam("acctype")String acctype,
			@RequestParam("bal")double bal,
			@RequestParam("pan")String pan)
	{
		UserInfo u=new UserInfo();
		u.setUsername(username);
		u.setEmail(email);
		u.setMobile(mobile);
		u.setGender(gender);
		u.setBranch(branch);
		u.setDob(dob);
		u.setAddress(address);
		u.setAcctype(acctype);
		u.setBal(bal);
		u.setPan(pan);
		u.setPassword(username+"@1234");
		u.setDp("default.jpg");
		u.setRole("CUSTOMER");
		ModelAndView mv=null;
		if(udaoimpl.openAccountByAdmin(u))
		{
			mv =  new ModelAndView("regadmin","msg","<font size=5 color=green >User Register Successfully <br>Account Opened</font>");
		}
		else
		{
			mv =  new ModelAndView("regadmin","msg","<font size=5 color=red >User Could Not Be Register <br>Already Exist</font>");
		}
		return mv;
	}
	@PostMapping("/checkUser")
	public String checkUser(
			@RequestParam("email")String email,
			@RequestParam("password")String password,
			Model m,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
			session.invalidate();
		}
		session = request.getSession(true);
		UserInfo u = new UserInfo();
		u.setEmail(email);
		u.setPassword(password);
		
		UserInfo user = udaoimpl.checkUser(u);
		if(user!=null) 
		{
			if(user.getRole().equals("ADMIN"))
			{
				session.setAttribute("msg","Welcome TO Universal Informatics");
				//session.setAttribute("user",user.getUsername());
				//session.setAttribute("dp", user.getDp());
				session.setAttribute("user", user);
				
				return "redirect:/admin";
			}
			else
			{
				System.out.println(user);
				//session.setAttribute("msg","Welcome TO Universal Informatics");
				//session.setAttribute("user",user.getUsername());
				//session.setAttribute("dp", user.getDp());
				session.setAttribute("user", user);
				return "redirect:/customer";
			}
		}
		else
		{
			m.addAttribute("msg","<font color=red size=4>Invalid User Credentials</font>");
			return "login";
		}	
		
	}
	@PostMapping("/changePassword")
	@ResponseBody
	public String changePassword(
	        @RequestParam("oldpass") String oldpass,
	        @RequestParam("newpass") String newpass,
	        HttpSession session)
	{
	    UserInfo u = (UserInfo) session.getAttribute("user");

	    if(u == null){
	        return "Session Expired";
	    }

	    return udaoimpl.changePassword(u.getEmail(), oldpass, newpass);
	}
}
