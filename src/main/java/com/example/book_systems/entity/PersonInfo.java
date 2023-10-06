package com.example.book_systems.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class PersonInfo {

	// 人員編號
	@Id
	@Column(name = "id")
	private String id;
	
	// email
	@Column(name = "email")
	private String email;
	
	// 姓名
	@Column(name = "user_name")
	private String user_name;
	
	// 密碼
	@Column(name = "pwd")
	private String pwd;
	
	// 出生日期
	@Column(name = "born")
	private LocalDateTime born;
	
	// 權限
	@Column(name = "manager")
	private int manager;
	
	// 註冊時間
	@Column(name = "redate")
	private LocalDateTime redate;
	
	// 鎖定狀態
	@Column(name = "locked_status")
	private int locked_status;
	
	

	public PersonInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public PersonInfo(String id, String email, String user_name, String pwd, LocalDateTime born, int manager,
			LocalDateTime redate, int locked_status) {
		super();
		this.id = id;
		this.email = email;
		this.user_name = user_name;
		this.pwd = pwd;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public LocalDateTime getBorn() {
		return born;
	}

	public void setBorn(LocalDateTime born) {
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
