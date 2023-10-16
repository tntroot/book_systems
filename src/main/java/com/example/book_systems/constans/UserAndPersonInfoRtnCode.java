package com.example.book_systems.constans;

public enum UserAndPersonInfoRtnCode {

	SUCCESSFUL("200","成功"),
	INPUT_ISNULL("400","尚有欄位未輸入"),
	INPUT_ERROR("400","輸入錯誤"),
	INPUT_MAPERROR("400","格式錯誤"),
	ACCOUNT_NOT_FOUNT("400","找不到帳戶"),
	ACCOUNT_EXISTS("400","帳號已存在"),
	EMAIL_EXISTS("400","email 已存在");
	
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
