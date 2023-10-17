package com.example.book_systems.vo.respone;

import java.util.List;

import com.example.book_systems.entity.UserShow;

public class UserShowRespone {
	
	private String code;
	
	private String message;

	private UserShow userShows;

	public UserShowRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserShowRespone(String code, String message, UserShow userShows) {
		super();
		this.code = code;
		this.message = message;
		this.userShows = userShows;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserShow getUserShows() {
		return userShows;
	}

	public void setUserShows(UserShow userShows) {
		this.userShows = userShows;
	}
	
	
	
	
}
