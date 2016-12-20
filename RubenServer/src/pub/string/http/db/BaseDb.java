package pub.string.http.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDb {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://cpp.pub";
	static final String TABLE_URL = "jdbc:mysql://cpp.pub/ruben_news?useUnicode=true&characterEncoding=utf8";
	// Database credentials

	static final String USER = "";
	static final String PASS = "";

	static BaseDb instance = null;

	private BaseDb() {

	}

	public static BaseDb getInstance() {
		if (instance == null) {
			instance = new BaseDb();
		}
		return instance;
	}

	public  Connection getDBConn() {
		Connection conn = null;
		// STEP 1: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 2: Open a connection
			System.out.println("BaseDb_Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
	public void closeConn(Connection conn) {
		try {
			if(conn!=null){
				conn.close();
			}else{
				System.out.println("BaseDb_Close connection error,connection is:null" );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("BaseDb_Close connection error,message is:" + e.getMessage());
		}
	}
//	CREATE DATABASE `test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
	public void createDatabase(String name) {
		Statement stmt = null;
		Connection conn = getDBConn();
		try {
			// STEP 4: Execute a query
			System.out.println("BaseDb_Creating database:" + name);
			stmt = conn.createStatement();
			String sql = "Create database if not exists " + name+" DEFAULT CHARACTER SET utf8";
			stmt.executeUpdate(sql);
			System.out.println("BaseDb_Database " + name + " created successfully...");
		} catch (SQLException e) {
			// Handle errors for JDBC
			e.printStackTrace();
			System.out.println("BaseDb_Create Database error,message is:" +e.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			System.out.println("BaseDb_Create Database error,message is:" +e.getMessage());
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			closeConn(conn);
		} // end try
		System.out.println("Goodbye!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseDb.getInstance().createDatabase("ruben_news");
	}
}
