package com.example.book_systems.service.ifs;

import com.example.book_systems.entity.User;
import com.example.book_systems.vo.respone.MsgRes;
import com.example.book_systems.vo.respone.UserRespone;
import com.example.book_systems.vo.respone.UserShowRespone;

public interface UserService {

	public UserRespone addUser(User user);
	
	public UserShowRespone login(String account, String pwd);
	
	public MsgRes forgetPwd(String email, String token) throws Exception;
	
	public MsgRes replacePwd(String email, String newPwd);
	
	public UserShowRespone getBalance(String account);
	
	public UserShowRespone editUser(User user);
	
	public MsgRes editPwd(String account, String oldPwd, String newPwd);
}
