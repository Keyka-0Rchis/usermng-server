package works.keyka.usermngserver.controller.model;

public class InputedUserParam {
	private String userName;
	private String email;
	private String password;
	
	public InputedUserParam(String userName ,String email,String password) {
		this.userName = userName;
		this.email = email;
		this.setPassword(password);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
