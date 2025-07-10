package works.keyka.usermngserver.domain;

public class UserData {
	
	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private boolean deleteFlag;
	
	public UserData(Integer userId,String userName,String email,String password,boolean deleteFlag) {
		//バリデーションはここで。ガード節でブロックするよ！
		//・・・フロント側でもすると思うんだけど
		
		//名前は空じゃなければOK
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("ユーザー名は必須です");
        }
        //メールの形式についてのチェックが入ってるよ。正規表現ってやつ。
        //^[\\w.%+-]　　最初から、英数字、_、.、%、+、-が一文字以上
        //@[\\w.-]　　@の後に英数字、.、-が一文字以上
        //\\.[a-zA-Z]{2,6}$　　.の後に英字が2文字以上6文字以下。
        //読み解いたけど、GPT先生に聞いたから、合致しないドメインが存在するかも。
        //でも、仕組みを知ってればどうとでもなるよね。
        if (email == null || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("メールアドレスの形式が不正です");
        }
        //パスワードがなかったり、スペースだけだったりするものをはじくよ。
        //アルファベットだけだったりするのをはじきたければさっきみたいに正規表現。
        //フロントでもはじくけどサーバーでもチェックするほうがいいらしい。
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("パスワードは8文字以上必要です");
        }
		
		this.setUserId(userId);
		this.setUserName(userName);
		this.seteMail(email);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String eMail) {
		this.email = eMail;
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
