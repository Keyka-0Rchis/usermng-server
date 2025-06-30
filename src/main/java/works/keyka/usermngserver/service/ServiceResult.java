package works.keyka.usermngserver.service;

import java.util.TreeMap;

import works.keyka.usermngserver.domain.UserData;


public class ServiceResult {

	private final String selectedMode;
	private final int targetedId;
	private final String targetedName;
	private final boolean success;
	private final String message;
	private final TreeMap<Integer, UserData> userMap;
	
	public ServiceResult(String selectedMode,int targetedId,String targetedName,boolean success,String message,TreeMap<Integer, UserData> userMap) {
		this.selectedMode =selectedMode;
		this.targetedId = targetedId;
		this.targetedName = targetedName;
		this.success = success;
		this.message =  message;
		this.userMap = userMap;
	}
	
	//エラーコード
	//エラー文
	//（ユーザーリスト）
	
	public String getSelectedMode() {
		return selectedMode;
	}
	
	public int getTargetedId() {
		return targetedId;
	}
	
	public String getTargetedName() {
		return targetedName;
	}
	
	public boolean isResultSuccess() {
		return success;
	}
	
	public String getResultMessage() {
		return message;
	}
	
	public TreeMap<Integer, UserData> getResultMap() {
		return userMap;
	}
	
}
