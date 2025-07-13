package works.keyka.usermngserver.common;


public enum ErrorCode {
	//不正な文字列
	VALIDATION_ERROR("E001"),
	//メールアドレスの重複
	DUPLICATE_EMAIL("E002"),
	//データベースの不具合
	DB_ERROR("E500"),
	//その他原因不明
	UNKNOWN_ERROR("E999");

	private final String code;

	ErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
