package com.example.book_systems.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_systems.constans.UserAndPersonInfoRtnCode;
import com.example.book_systems.entity.User;
import com.example.book_systems.service.ifs.UserService;
import com.example.book_systems.vo.requery.ForgotPwdReq;
import com.example.book_systems.vo.respone.MsgRes;
import com.example.book_systems.vo.respone.UserRespone;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin
@RequestMapping("book_systems")
public class UserAndPersoninfoController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "login")
	public void login() {
		
	}
	
	@PostMapping(value = "sign")
	public UserRespone sign(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PostMapping(value = "forgetPwd")
	public MsgRes forgotPwd(@RequestBody ForgotPwdReq req , HttpSession http) {
		try {
			String token = RandomString.make(20);
			
			// save to HttpSession
			http.setAttribute("resetPwdToken", token);
			http.setAttribute("email", req.getEmail());
			
			// set time 10 minute
			http.setMaxInactiveInterval(10 * 60);
			
			//send token to user email
			MsgRes res = userService.forgetPwd(req.getEmail(), token);
			
			return res;
		} catch (Exception e) {
			return new MsgRes(UserAndPersonInfoRtnCode.INPUT_MAPERROR.getCode(),e.getMessage());
		}
	}
}
