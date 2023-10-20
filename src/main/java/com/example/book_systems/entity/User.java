package com.example.book_systems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	// 帳號/員工帳號
	@Id
	@Column(name = "account")
	private String account;
	
	// 名稱
	@Column(name = "user_name")
	private String user_name;
	
	// 信箱
	@Column(name = "email")
	private String email;
	
	// 密碼
	@Column(name = "pwd")
	private String pwd;
	
	// 生日
	@Column(name = "born")
	private LocalDate born;
	
	// 權限
	@Column(name = "manager")
	private int manager;
	
	// 註冊日期
	@Column(name = "redate")
	private LocalDateTime redate;
	
	// 鎖定狀態
	@Column(name = "locked_status")
	private int locked_status;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String account, String user_name, String email, String pwd, LocalDate born, int manager,
			LocalDateTime redate, int locked_status) {
		super();
		this.account = account;
		this.user_name = user_name;
		this.email = email;
		this.pwd = pwd;
		this.born = born;
		this.manager = manager;
		this.redate = redate;
		this.locked_status = locked_status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
