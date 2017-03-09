package pub.string.http.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pub.string.http.pojo.UserAccount;
import pub.string.http.util.LogUtil;

public class DBTest {

	public static void main(String[] args) {
		DBTest test=new DBTest();
		// TODO Auto-generated method stub
		test.testDBLoginWithNull();
		test.testUserLogin();

	}

	
	//测试用户登录
	public void testUserLogin(){
		DBHelp db=new DBHelp();
		//db.createUserTable();
		db.insertUser(new UserAccount("dd","test","test"));
		if(db.login(new UserAccount("dd","test","test")).isResult()){
			LogUtil.show("login successfully");
		}else{
			LogUtil.show("login failed");
		}
	}
	
	//测试用户名密码为空的情况能否登陆
	  String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	  String DB_URL = "jdbc:mysql://cpp.pub";
	  String TABLE_URL = "jdbc:mysql://cpp.pub/ruben_news?useUnicode=true&characterEncoding=utf8";
	  String USER = "";
	  String PASS = "";
	  public void testDBLoginWithNull(){

		  LogUtil.show("start clear news in database...");
			String sql2="truncate table news;";
			Connection conn=getTBConn();
			try {
				conn.createStatement().execute(sql2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LogUtil.show("end clear news in database...");
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public  Connection getTBConn() {
		Connection conn = null;
		// STEP 1: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 2: Open a connection
			System.out.println("BaseDb_Connecting to database...");
			conn = DriverManager.getConnection(TABLE_URL, USER, PASS);
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("BaseDb_Get connection error,message is:" +e.getMessage());
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("BaseDb_Get connection error,message is:" +e.getMessage());
		}
		return conn;
	}
}
