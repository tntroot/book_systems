package com.example.book_systems.constans;

public enum UserRtnCode {

	SUCCESSFUL("200","成功"),
	INPUT_ISNULL("400","尚有欄位未輸入"),
	INPUT_ERROR("400","輸入錯誤"),
	INPUT_MAPERROR("400","格式錯誤"),
	ACCOUNT_NOT_FOUNT("400","找不到帳戶"),
	ACCOUNT_EXISTS("400","帳號已存在"),
	EMAIL_EXISTS("400","email 已存在"),
	EMAIL_UN_SUCCESSFUL("400","檢測到此 email 發生變動，將禁止變更"),
	ACCANDPWD_ERROR("400","帳號密碼輸入錯誤"),
	ACCOUNT_NOT_LOGIN("400","帳號未登入"),
	PASSWORK_OLD_ERROR("400","舊密碼與原始密碼不相同"),
	PASSWORK_NEW_MAPERROR("400","新密碼格式錯誤"),
	PASSWORK_NEWANDOLD_EQUAL("400","新密碼不可與舊密碼相同"),
	DATA_ERROR("400","資料錯誤"),
	TOKEN_NOT_FOUNT("400","無此驗證碼或驗證碼已超時"),
	TOKEN_ERROR("400","驗證碼錯誤"),
	TOKEN_CHECK("400","驗證超時"),
	UPDATE_IMG_ERROR("400", "上傳圖片失敗");
	
	private String code;
	
	private String message;

	private UserRtnCode(String code, String message) {
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
