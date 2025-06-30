package works.keyka.usermngserver.controller.model;

public class InputedUserParam {
	private String userName;
	private String email;
	
	public InputedUserParam(String userName ,String email) {
		this.userName = userName;
		this.email = email;
	}
	
	public String getName() {
		return this.userName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setName(String userName) {
		this.userName = userName;
	}
	
	public void setRmail(String email) {
		this.email = email;
	}
}
