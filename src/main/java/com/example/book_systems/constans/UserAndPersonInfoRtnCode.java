package com.example.book_systems.constans;

public enum UserAndPersonInfoRtnCode {

	SUCCESSFUL("200","成功");
	
	private String code;
	
	private String message;

	private UserAndPersonInfoRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
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
	
	
}
