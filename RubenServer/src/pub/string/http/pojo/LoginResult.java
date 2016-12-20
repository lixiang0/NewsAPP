package pub.string.http.pojo;

public class LoginResult {
	String userName="user";
	boolean result=false;
	public LoginResult(String userName,boolean result){
		this.userName=userName;
		this.result=result;
	}
	public LoginResult(){
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
