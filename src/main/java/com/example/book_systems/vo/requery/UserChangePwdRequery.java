package com.example.book_systems.vo.requery;

public class UserChangePwdRequery {

	private String account;
	
	private String oldPwd;
	
	private String newPwd;

	public UserChangePwdRequery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserChangePwdRequery(String account, String oldPwd, String newPwd) {
		super();
		this.account = account;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	
}
