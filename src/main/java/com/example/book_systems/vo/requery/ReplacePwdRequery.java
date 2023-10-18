package com.example.book_systems.vo.requery;

public class ReplacePwdRequery {

	private String email;
	
	private String token;
	
	private String newPwd;

	public ReplacePwdRequery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplacePwdRequery(String email, String token, String newPwd) {
		super();
		this.email = email;
		this.token = token;
		this.newPwd = newPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	
}
