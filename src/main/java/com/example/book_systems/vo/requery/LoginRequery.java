package com.example.book_systems.vo.requery;

public class LoginRequery {

	private String account;
	
	private String pwd;

	public LoginRequery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginRequery(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
}
