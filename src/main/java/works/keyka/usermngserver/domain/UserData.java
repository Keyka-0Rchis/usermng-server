package works.keyka.usermngserver.domain;

public class UserData {
	
	private Integer userId;
	private String userName;
	private String eMail;
	private String password;
	private boolean deleteFlag;
	
	public UserData(Integer userId,String userName,String eMail,String password,boolean deleteFlag) {
		this.setUserId(userId);
		this.setUserName(userName);
		this.seteMail(eMail);
		this.setPassword(password);
		this.setDeleteFlag(deleteFlag);
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


}
