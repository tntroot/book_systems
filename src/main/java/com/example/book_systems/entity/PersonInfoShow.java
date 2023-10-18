package com.example.book_systems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class PersonInfoShow {

private String id;
	
	// email
	private String email;
	
	// 姓名
	private String user_name;
	
	// 出生日期
	private LocalDate born;
	
	// 權限
	private int manager;
	
	// 註冊時間
	private LocalDateTime redate;
	
	// 鎖定狀態
	private int locked_status;

	public PersonInfoShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonInfoShow(String id, String email, String user_name, LocalDate born, int manager,
			LocalDateTime redate, int locked_status) {
		super();
		this.id = id;
		this.email = email;
		this.user_name = user_name;
		this.born = born;
		this.manager = manager;
		this.redate = redate;
		this.locked_status = locked_status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public LocalDate getBorn() {
		return born;
	}

	public void setBorn(LocalDate born) {
		this.born = born;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public LocalDateTime getRedate() {
		return redate;
	}

	public void setRedate(LocalDateTime redate) {
		this.redate = redate;
	}

	public int getLocked_status() {
		return locked_status;
	}

	public void setLocked_status(int locked_status) {
		this.locked_status = locked_status;
	}
	
	
}
