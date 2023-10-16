package com.example.book_systems.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.book_systems.constans.UserAndPersonInfoRtnCode;
import com.example.book_systems.entity.User;
import com.example.book_systems.entity.UserShow;
import com.example.book_systems.repository.UserDao;
import com.example.book_systems.service.ifs.UserService;
import com.example.book_systems.vo.requery.ForgotPwdReq;
import com.example.book_systems.vo.respone.MsgRes;
import com.example.book_systems.vo.respone.UserRespone;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{
	
	
	private String checkEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private String checkPwd = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$";
	private String chexkPhone = "^0[\\d]{9}";
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public UserRespone addUser(User user) {
		if(user == null) {
			return new UserRespone(UserAndPersonInfoRtnCode.INPUT_ISNULL.getCode(),UserAndPersonInfoRtnCode.INPUT_ISNULL.getMessage(),null);
		}
		if(!StringUtils.hasText(user.getAccount()) || 
				!StringUtils.hasText(user.getEmail()) || 
				!StringUtils.hasText(user.getPwd()) || 
				!StringUtils.hasText(user.getUser_name())) {
			return new UserRespone(UserAndPersonInfoRtnCode.INPUT_ISNULL.getCode(),UserAndPersonInfoRtnCode.INPUT_ISNULL.getMessage(),null);
		}
		
		if(!user.getEmail().matches(checkEmail)) {
			return new UserRespone(UserAndPersonInfoRtnCode.INPUT_MAPERROR.getCode(),"Email "+UserAndPersonInfoRtnCode.INPUT_MAPERROR.getMessage(),null);
		}
		if(!user.getPwd().matches(checkPwd)) {
			return new UserRespone(UserAndPersonInfoRtnCode.INPUT_MAPERROR.getCode(),"密碼 "+UserAndPersonInfoRtnCode.INPUT_MAPERROR.getMessage(),null);
		}
		
		List<UserShow> userShows = userDao.findByEmail(user.getEmail());
		if(!CollectionUtils.isEmpty(userShows)) {
			return new UserRespone(UserAndPersonInfoRtnCode.EMAIL_EXISTS.getCode(),UserAndPersonInfoRtnCode.EMAIL_EXISTS.getMessage(),null);
		}
		if(userDao.existsById(user.getAccount())) {
			return new UserRespone(UserAndPersonInfoRtnCode.ACCOUNT_EXISTS.getCode(),UserAndPersonInfoRtnCode.ACCOUNT_EXISTS.getMessage(),null);
		}
		
		user.setPwd(encoderPwd(user.getPwd()));
		userDao.save(user);
		
		return new UserRespone(UserAndPersonInfoRtnCode.SUCCESSFUL.getCode(),UserAndPersonInfoRtnCode.SUCCESSFUL.getMessage(),null);
	}
	
	
	// encoder pwd
	private String encoderPwd(String pwd) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(pwd);
	}

	private boolean matchesPwdAndHashPass(String pwd, String hashPass) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(pwd, hashPass);
	}


	@Override
	public MsgRes forgetPwd(String email, String token) throws Exception{
		// input is null & check input format
		if (!StringUtils.hasText(email) || !email.matches(checkEmail)) {
			return new MsgRes(UserAndPersonInfoRtnCode.EMAIL_EXISTS.getCode(), UserAndPersonInfoRtnCode.EMAIL_EXISTS.getMessage());
		}

		// user not found
		List<UserShow> userShows = userDao.findByEmail(email);
		if(!CollectionUtils.isEmpty(userShows)) {
			return new MsgRes(UserAndPersonInfoRtnCode.EMAIL_EXISTS.getCode(),UserAndPersonInfoRtnCode.EMAIL_EXISTS.getMessage());
		}

//					SimpleMailMessage message = new SimpleMailMessage();
//					message.setFrom("javae7278@gmail.com");
//					message.setTo(userEmail);
//					message.setSubject("Speed接案網:重製密碼");
//					
//					String content = "<p>你好, </p>"
//					            + "<p>您已要求重新設定密碼</p>"
//					            + "<p>驗證碼:</p>"
//					            + "<p>" + token + "</p>"
//					            + "<br>"
//					            + "<p>驗證碼有效時間為10分鐘</p>"
//					            + "<p>感謝您使用Speed接案網</p>";
//					
//					message.setText(content);
//					
//					// send to user email
//					mailSender.send(message);

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom("cherhorn538@gmail.com", "圖書管理系統");
			helper.setTo(email);
	
			String subject = "已要求重新設定密碼";
	
			String content = "<p>你好, </p>" + "<p>您已要求重新設定密碼</p>" + "<p>驗證碼:</p>" + "<p>" + token + "</p>" + "<br>"
					+ "<p>驗證碼有效時間為10分鐘</p>" + "<p>感謝您使用 圖書管理系統</p>";
	
			helper.setSubject(subject);
	
			helper.setText(content, true);
	
			// send to user email
			mailSender.send(message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return new MsgRes(UserAndPersonInfoRtnCode.SUCCESSFUL.getCode(), UserAndPersonInfoRtnCode.SUCCESSFUL.getMessage());
	}

}
