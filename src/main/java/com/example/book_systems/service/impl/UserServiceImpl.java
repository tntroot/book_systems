package com.example.book_systems.service.impl;

import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.book_systems.constans.UserRtnCode;
import com.example.book_systems.entity.User;
import com.example.book_systems.entity.UserShow;
import com.example.book_systems.repository.UserDao;
import com.example.book_systems.service.ifs.UserService;
import com.example.book_systems.vo.respone.MsgRes;
import com.example.book_systems.vo.respone.UserRespone;
import com.example.book_systems.vo.respone.UserShowRespone;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
	public UserShowRespone getBalance(String account) {
		
		if(!StringUtils.hasText(account)) {
			return new UserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
		
		Optional<User> op = userDao.findById(account);
		if(op.isEmpty()) {
			return new UserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
		
		// BeanUtils.copyProperties(User.class,UserShow.class);
		
		try {
			UserShow userShow = userToUserShow(op.get());
			return new UserShowRespone(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage(),userShow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new UserShowRespone(UserRtnCode.DATA_ERROR.getCode(),e.getMessage(),null);
		}
	}

	@Override
	public UserRespone addUser(User user) {
		if(user == null) {
			return new UserRespone(UserRtnCode.INPUT_ISNULL.getCode(),UserRtnCode.INPUT_ISNULL.getMessage(),null);
		}
		if(!StringUtils.hasText(user.getAccount()) || 
				!StringUtils.hasText(user.getEmail()) || 
				!StringUtils.hasText(user.getPwd()) || 
				!StringUtils.hasText(user.getUser_name())) {
			return new UserRespone(UserRtnCode.INPUT_ISNULL.getCode(),UserRtnCode.INPUT_ISNULL.getMessage(),null);
		}
		
		if(!user.getEmail().matches(checkEmail)) {
			return new UserRespone(UserRtnCode.INPUT_MAPERROR.getCode(),"Email "+UserRtnCode.INPUT_MAPERROR.getMessage(),null);
		}
		if(!user.getPwd().matches(checkPwd)) {
			return new UserRespone(UserRtnCode.INPUT_MAPERROR.getCode(),"密碼 "+UserRtnCode.INPUT_MAPERROR.getMessage(),null);
		}
		
		Optional<User> userShows = userDao.findByEmail(user.getEmail());
		if(!userShows.isEmpty()) {
			return new UserRespone(UserRtnCode.EMAIL_EXISTS.getCode(),UserRtnCode.EMAIL_EXISTS.getMessage(),null);
		}
		if(userDao.existsById(user.getAccount())) {
			return new UserRespone(UserRtnCode.ACCOUNT_EXISTS.getCode(),UserRtnCode.ACCOUNT_EXISTS.getMessage(),null);
		}
		
		user.setPwd(encoderPwd(user.getPwd()));
		user.setManager(2);
		userDao.save(user);
		
		return new UserRespone(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage(),null);
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
	public UserShowRespone login(String account, String pwd) {
		
		if(!StringUtils.hasText(account)||!StringUtils.hasText(pwd)) {
			return new UserShowRespone(UserRtnCode.INPUT_ISNULL.getCode(),UserRtnCode.INPUT_ISNULL.getMessage(),null);
		}
		if(!userDao.existsById(account)) {
			return new UserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
		Optional<User> thisAccount = userDao.findById(account);
		if(thisAccount.isEmpty()) {
			return new UserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
		if(!matchesPwdAndHashPass(pwd, thisAccount.get().getPwd())) {
			return new UserShowRespone(UserRtnCode.ACCANDPWD_ERROR.getCode(),UserRtnCode.ACCANDPWD_ERROR.getMessage(),null);
		}
		
		// Copy 另一種寫法，UserShow entity 可以不加 JsonIgnoreProperties 註釋(Annotation)
		// 將 User entity 複製到 UserShow entity
		// BeanUtils.copyProperties(thisAccount.get(), UserShow.class);
		
		try {
			UserShow userShow = userToUserShow(thisAccount.get());
			return new UserShowRespone(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage(),userShow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new UserShowRespone(UserRtnCode.DATA_ERROR.getCode(),e.getMessage(),null);
		}
	}


	@Override
	public MsgRes forgetPwd(String email, String token) throws Exception{
		// input is null & check input format
		if (!StringUtils.hasText(email) || !email.matches(checkEmail)) {
			return new MsgRes(UserRtnCode.EMAIL_EXISTS.getCode(), UserRtnCode.EMAIL_EXISTS.getMessage());
		}

		// user not found
		Optional<User> userShows = userDao.findByEmail(email);
		if(userShows.isEmpty()) {
			return new MsgRes(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage());
		}


		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		
		helper.setFrom("cherhorn538@gmail.com", "圖書管理系統");
		helper.setTo(email);

		String subject = "已要求重新設定密碼";

		String content = "<p>你好, </p>" + "<p>您已要求重新設定密碼</p>" + "<p>驗證碼:</p>" + "<p>" + token + "</p>" + "<br>"
				+ "<p>驗證碼有效時間為10分鐘</p>" + "<p>感謝您使用 圖書管理系統</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		// send to user email
		mailSender.send(message);	

		return new MsgRes(UserRtnCode.SUCCESSFUL.getCode(), UserRtnCode.SUCCESSFUL.getMessage());
	}
	

	
	// 將查到的 User 資訊經過篩選複製到 UserShow
	private UserShow userToUserShow(User user) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		// jackson 不支持時間類型，需而外引入
		mapper.registerModule(new JavaTimeModule());
		
		String json = mapper.writeValueAsString(user);
		return mapper.readValue(json,UserShow.class);
	}


	@Override
	public MsgRes replacePwd(String email, String newPwd) {
		
		 Optional<User> op = userDao.findByEmail(email);
		
		if(op.isEmpty()) {
			return new MsgRes(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage());
		}
		
		if(!StringUtils.hasText(newPwd) || !newPwd.matches(checkPwd)) {
			return new MsgRes(UserRtnCode.INPUT_MAPERROR.getCode(),"密碼 "+UserRtnCode.INPUT_MAPERROR.getMessage());
		}
		
		op.get().setPwd(encoderPwd(newPwd));
		userDao.save(op.get());
		
		return new MsgRes(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public UserShowRespone editUser(User user) {
		
		if(user == null) {
			return new UserShowRespone(UserRtnCode.INPUT_ISNULL.getCode(),UserRtnCode.INPUT_ISNULL.getMessage(),null);
		}
		
		if(!StringUtils.hasText(user.getAccount()) || 
				user.getBorn()==null || 
				!StringUtils.hasText(user.getUser_name())) {
			return new UserShowRespone(UserRtnCode.INPUT_ISNULL.getCode(),UserRtnCode.INPUT_ISNULL.getMessage(),null);
		}
		
		Optional<User> op = userDao.findById(user.getAccount());
		if(op.isEmpty()) {
			return new UserShowRespone(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage(),null);
		}
//		if(!op.get().getEmail().equals(user.getEmail())) {
//			return new UserShowRespone(UserRtnCode.EMAIL_UN_SUCCESSFUL.getCode(),UserRtnCode.EMAIL_UN_SUCCESSFUL.getMessage(),null);
//		}
		User thisEditUser = op.get();
		thisEditUser.setBorn(user.getBorn());
		thisEditUser.setUser_name(user.getUser_name());
		
//		user.setPwd(op.get().getPwd());
//		user.setRedate(op.get().getRedate());
//		user.setManager(op.get().getManager());
		
		userDao.save(thisEditUser);
		
		try {
			UserShow userShow = userToUserShow(thisEditUser);
			return new UserShowRespone(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage(),userShow);			
		} catch (Exception e) {
			e.printStackTrace();
			return new UserShowRespone(UserRtnCode.DATA_ERROR.getCode(),e.getMessage(),null);
		}
	}

	@Override
	public MsgRes editPwd(String account, String oldPwd, String newPwd) {
		
		if(!StringUtils.hasText(account) || !StringUtils.hasText(oldPwd) || !StringUtils.hasText(newPwd)) {
			return new MsgRes(UserRtnCode.INPUT_ISNULL.getCode(),UserRtnCode.INPUT_ISNULL.getMessage());
		}
		
		Optional<User> op = userDao.findById(account);
		if(op.isEmpty()) {
			return new MsgRes(UserRtnCode.ACCOUNT_NOT_FOUNT.getCode(),UserRtnCode.ACCOUNT_NOT_FOUNT.getMessage());
		}
		if(!matchesPwdAndHashPass(oldPwd, op.get().getPwd())) {
			return new MsgRes(UserRtnCode.PASSWORK_OLD_ERROR.getCode(),UserRtnCode.PASSWORK_OLD_ERROR.getMessage());
		}
		if(!newPwd.matches(checkPwd)) {
			return new MsgRes(UserRtnCode.PASSWORK_NEW_MAPERROR.getCode(),UserRtnCode.PASSWORK_NEW_MAPERROR.getMessage());
		}
		if(matchesPwdAndHashPass(newPwd, op.get().getPwd())) {
			return new MsgRes(UserRtnCode.PASSWORK_NEWANDOLD_EQUAL.getCode(),UserRtnCode.PASSWORK_NEWANDOLD_EQUAL.getMessage());
		}
		
		User user = op.get();
		user.setPwd(encoderPwd(newPwd));
		userDao.save(user);
		
		System.out.println(matchesPwdAndHashPass(newPwd, user.getPwd()));
		
		return new MsgRes(UserRtnCode.SUCCESSFUL.getCode(),UserRtnCode.SUCCESSFUL.getMessage());
	}

}
