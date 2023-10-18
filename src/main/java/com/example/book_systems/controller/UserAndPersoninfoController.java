package com.example.book_systems.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
import com.example.book_systems.vo.requery.LoginRequery;
import com.example.book_systems.vo.requery.ReplacePwdRequery;
import com.example.book_systems.vo.respone.MsgRes;
import com.example.book_systems.vo.respone.UserRespone;
import com.example.book_systems.vo.respone.UserShowRespone;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin
@RequestMapping("book_systems")
public class UserAndPersoninfoController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "login")
	public UserShowRespone login(@RequestBody LoginRequery loginRequery, HttpSession http) {
		
		UserShowRespone res = userService.login(loginRequery.getAccount(), loginRequery.getPwd());
		if(StringUtils.hasText((String)http.getAttribute("account"))) {
			return res;
		}
		
		if(res.getCode().equals(UserAndPersonInfoRtnCode.SUCCESSFUL.getCode())) {
			return res;
		}
		
		http.setAttribute("account", loginRequery.getAccount());
		http.setAttribute("pwd", loginRequery.getPwd());
		
		http.setMaxInactiveInterval(60*60*24*7);
		
		return res;
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
	
	@PostMapping(value = "forget/check_pwd_token")
	public MsgRes checkPwdToken(@RequestBody ReplacePwdRequery pwdRequery, HttpSession http) {
		
		String token = (String) http.getAttribute("token");
		if(!StringUtils.hasText(token)) {
			return new MsgRes(UserAndPersonInfoRtnCode.TOKEN_NOT_FOUNT.getCode(),UserAndPersonInfoRtnCode.TOKEN_NOT_FOUNT.getMessage());
		}
		
		if(!token.equals(pwdRequery.getToken())) {
			return new MsgRes(UserAndPersonInfoRtnCode.TOKEN_ERROR.getCode(),UserAndPersonInfoRtnCode.TOKEN_ERROR.getMessage());
		}
		
		return new MsgRes(UserAndPersonInfoRtnCode.SUCCESSFUL.getCode(),UserAndPersonInfoRtnCode.SUCCESSFUL.getMessage());
	}
	
	@PostMapping(value = "forget/replacePwd")
	public MsgRes replacePwd(@RequestBody ReplacePwdRequery pwdRequery, HttpSession http) {
		
		String email = (String) http.getAttribute("email");
		if(StringUtils.hasText(email)) {
			return new MsgRes(UserAndPersonInfoRtnCode.TOKEN_NOT_FOUNT.getCode(),UserAndPersonInfoRtnCode.TOKEN_NOT_FOUNT.getMessage());
		}
		
		MsgRes msgRes = userService.replacePwd(email, pwdRequery.getNewPwd());
		if(msgRes.getCode().equals(UserAndPersonInfoRtnCode.SUCCESSFUL.getCode())) {
			http.invalidate();
		}
		
		return msgRes;
	}

}
