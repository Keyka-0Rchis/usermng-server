package works.keyka.usermngserver.common.exception;

import org.springframework.http.HttpStatus;

import works.keyka.usermngserver.common.ErrorCode;

//その他の例外
public class UnexpectedException extends RuntimeException{
	private final ErrorCode errorCode;
	private final String operation;
	
	public UnexpectedException(String message,ErrorCode errorCode,String operation) {
		super(message);
		this.errorCode = errorCode;
		this.operation = operation;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public String getOperation() {
		return operation;
	}

	public HttpStatus getStatus() {
		return errorCode.getStatus();
	}
}