package pub.string.http.pojo;

public class UserAccount {
	String userName="";
	String userPwd="";
	String description="";
	
	public UserAccount(String userName, String userPwd, String description) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.description = description;
	}
	
	public UserAccount(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.description = "no content";
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
