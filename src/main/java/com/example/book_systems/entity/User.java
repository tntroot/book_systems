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

	@Id
	@Column(name = "account")
	private String account;
	
	@Column(name = "user_name")
	private String user_name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "pwd")
	private String pwd;
	
	@Column(name = "born")
	private LocalDate born;
	
	@Column(name = "redate")
	private LocalDateTime redate;
	
	@Column(name = "locked_status")
	private int locked_status;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String account, String user_name, String email, String pwd, LocalDate born, LocalDateTime redate,
			int locked_status) {
		super();
		this.account = account;
		this.user_name = user_name;
		this.email = email;
		this.pwd = pwd;
		this.born = born;
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
