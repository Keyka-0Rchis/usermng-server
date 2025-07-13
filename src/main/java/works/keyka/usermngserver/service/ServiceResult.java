package works.keyka.usermngserver.service;

import works.keyka.usermngserver.common.ErrorCode;

public class ServiceResult {

	private final String operation;
	private final boolean success;
	private final String message;
	private final ErrorCode errorCode;
	
	public ServiceResult(String operation,boolean success,String message,ErrorCode errorCode) {
		this.operation =operation;
		this.success = success;
		this.message =  message;
		this.errorCode = errorCode;
	}
	
	//エラーコード
	//エラー文
	//（ユーザーリスト）
	
	public String getSelectedMode() {
		return operation;
	}
	
	public boolean isResultSuccess() {
		return success;
	}
	
	public String getResultMessage() {
		return message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	
}
