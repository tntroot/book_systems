package com.example.book_systems.vo.respone;

import java.util.List;

import com.example.book_systems.entity.User;

public class UserRespone {

	private String code;
	
	private String message;
	
	private List<User> users;

	public UserRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRespone(String code, String message, List<User> users) {
		super();
		this.code = code;
		this.message = message;
		this.users = users;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
