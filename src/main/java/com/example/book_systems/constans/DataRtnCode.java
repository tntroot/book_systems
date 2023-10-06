package com.example.book_systems.constans;

public enum DataRtnCode {

	SUCCESSFUL("200","成功"),
	DATA_NOT_FOUND("400","查無資料"),
	DATA_ERROR("400","資料錯誤"),
	INPUT_NULL("400","輸入欄位不可為空");
	
	private String code;

	private String message;

	private DataRtnCode(String code, String message) {
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
