package com.example.book_systems.service.ifs;

import com.example.book_systems.entity.User;
import com.example.book_systems.vo.respone.MsgRes;
import com.example.book_systems.vo.respone.UserRespone;

public interface UserService {

	public UserRespone addUser(User user);
	
	public MsgRes forgetPwd(String email, String token) throws Exception;
}
