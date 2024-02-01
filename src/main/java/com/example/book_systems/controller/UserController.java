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

import com.example.book_systems.constans.UserRtnCode;
import com.example.book_systems.entity.User;
import com.example.book_systems.service.ifs.UserService;
import com.example.book_systems.vo.requery.LoginRequery;
import com.example.book_systems.vo.requery.ReplacePwdRequery;
import com.example.book_systems.vo.requery.UserChangePwdRequery;
import com.example.book_systems.vo.respone.AllUserShowRespone;
import com.example.book_systems.vo.respone.MsgRes;
import com.example.book_systems.vo.respone.UserRespone;
import com.example.book_systems.vo.respone.UserShowRespone;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin
@RequestMapping("book_systems")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "getBalance")
	private UserShowRespone getBalance(HttpSession http) {
		
		String account = (String) http.getAttribute("account");
		if(!StringUtils.hasText(account)) {
			return new UserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
		
		return userService.getBalance(account);	
	}

	@PostMapping(value = "login")
	public UserShowRespone login(@RequestBody LoginRequery loginRequery, HttpSession http) {
		
		UserShowRespone res = userService.login(loginRequery.getAccount(), loginRequery.getPwd());
		if(StringUtils.hasText((String)http.getAttribute("account"))) {
			return res;
		}
		
		if(!res.getCode().equals(UserRtnCode.SUCCESSFUL.getCode())) {
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
	
	@GetMapping(value = "logout")
	public UserRespone logout(HttpSession http) {
		
		http.invalidate();
		return new UserRespone(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage(),null);
	}
	
	@PostMapping(value = "forgetPwd")
	public MsgRes forgotPwd(@RequestBody ReplacePwdRequery req , HttpSession http) {
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
			return new MsgRes(UserRtnCode.INPUT_MAPERROR.getCode(),e.getMessage());
		}
	}
	
	@PostMapping(value = "forget/check_pwd_token")
	public MsgRes checkPwdToken(@RequestBody ReplacePwdRequery pwdRequery, HttpSession http) {
		
		String token = (String) http.getAttribute("resetPwdToken");
		if(!StringUtils.hasText(token)) {
			return new MsgRes(UserRtnCode.TOKEN_NOT_FOUNT.getCode(),UserRtnCode.TOKEN_NOT_FOUNT.getMessage());
		}
		
		if(!token.equals(pwdRequery.getToken())) {
			return new MsgRes(UserRtnCode.TOKEN_ERROR.getCode(),UserRtnCode.TOKEN_ERROR.getMessage());
		}
		
		return new MsgRes(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage());
	}
	
	@PostMapping(value = "forget/replacePwd")
	public MsgRes replacePwd(@RequestBody ReplacePwdRequery pwdRequery, HttpSession http) {
		
		String email = (String) http.getAttribute("email");
		if(!StringUtils.hasText(email)) {
			return new MsgRes(UserRtnCode.TOKEN_CHECK.getCode(),UserRtnCode.TOKEN_CHECK.getMessage());
		}
		
		MsgRes msgRes = userService.replacePwd(email, pwdRequery.getNewPwd());
		if(msgRes.getCode().equals(UserRtnCode.SUCCESSFUL.getCode())) {
			http.invalidate();
		}
		
		return msgRes;
	}
	
	@PostMapping(value = "setting/editUser")
	public UserShowRespone editUser(@RequestBody User user, HttpSession http) {
		
		String account = (String) http.getAttribute("account");
		if(!StringUtils.hasText(account)) {
			return new UserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
		
		return userService.editUser(user);
	}
	
	@PostMapping(value = "setting/editPwd")
	public MsgRes editPwd(@RequestBody UserChangePwdRequery userPwd,HttpSession http) {
		
		String account = (String) http.getAttribute("account");
		if(!StringUtils.hasText(account)) {
			return new MsgRes(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage());
		}
		
		return userService.editPwd(userPwd.getAccount(),userPwd.getOldPwd(),userPwd.getNewPwd());
	}
	
	@GetMapping(value = "setting/searchAllUser")
	public AllUserShowRespone searchAlluser(HttpSession http) {
		String account = (String) http.getAttribute("account");
		if(!StringUtils.hasText(account)) {
			return new AllUserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
		
		return userService.searchAllUser();
	}

}
