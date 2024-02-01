package com.example.book_systems.vo.respone;

import java.util.List;

import com.example.book_systems.entity.UserShow;

public class AllUserShowRespone {

	private String code;
	
	private String message;
	
	private List<UserShow> userShow;

	public AllUserShowRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AllUserShowRespone(String code, String message, List<UserShow> userShow) {
		super();
		this.code = code;
		this.message = message;
		this.userShow = userShow;
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

	public void setMsg(String message) {
		this.message = message;
	}

	public List<UserShow> getUserShow() {
		return userShow;
	}

	public void setUserShow(List<UserShow> userShow) {
		this.userShow = userShow;
	}
	
	
}
