package com.example.book_systems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "pwd" })
public class UserShow {

	private String account;
	
	private String user_name;
	
	private String email;
	
	private LocalDate born;
	
	private int manager;
	
	private LocalDateTime redate;
	
	private int locked_status;
	
	private String img;

	public UserShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserShow(String account, String user_name, String email, LocalDate born, int manager, LocalDateTime redate,
			int locked_status, String img) {
		super();
		this.account = account;
		this.user_name = user_name;
		this.email = email;
		this.born = born;
		this.manager = manager;
		this.redate = redate;
		this.locked_status = locked_status;
		this.img = img;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	

	
	
}
