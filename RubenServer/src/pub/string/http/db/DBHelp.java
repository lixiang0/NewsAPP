package pub.string.http.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pub.string.http.pojo.LoginResult;
import pub.string.http.pojo.NewsItem;
import pub.string.http.pojo.URL;
import pub.string.http.pojo.UserAccount;
import pub.string.http.util.LogUtil;
import pub.string.http.util.NewsUtil;

/**
 * @author ruben
 *提供数据库服务：
 *1.创建数据库           public void createDatabase(String name)；
 *2.创建新的新闻表 	   public void createNewTable()
 *3.插入新的新闻         public void insertNewItem(NewsItem news)
 *4.得到所有的新闻        public List<NewsItem> getItems(String category)
 *5.去除重复的新闻：      public void clearDuplicate()
 *6.清空新闻表：         public void clearNews()
 */
public class DBHelp {
	
	public static void main(String[] args){
		DBHelp db=new DBHelp();
		db.createUserTable();
		db.insertUser(new UserAccount("dd","test","test"));
		if(db.login(new UserAccount("dd","test","test")).isResult()){
			LogUtil.show("login successfully");
		}else{
			LogUtil.show("login failed");
		}
	//	db.clearUsers();
//		db.clearNews();
//		db.createDatabase("ruben_news");
//		db.createNewTable();
//		List<NewsItem> list=new ArrayList<NewsItem>();
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_ENTAINMENT));
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_FINANCE));
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_FOCUS));
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_LOCAL));
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_MAINLAND));
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_SPORT));
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_TECH));
//		list.addAll(NewsUtil.getNewsByCategory(URL.URL_WORLD));
////		for(NewsItem news :list ){
////			db.insertNewItem(news);
////		}
//		db.insertNewItemList(list);
//		String str=db.getItems("娱乐").toString();
//		LogUtil.show(str);
////		db.clearDuplicate();
	}
	// create database;
	public void createDatabase(String name) {
		BaseDb.getInstance().createDatabase(name);
	}

	public void createNewTable() {
		Connection conn = BaseDb.getInstance().getTBConn();
		System.out.println("Creating news table in news table...");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String itemSQL = "CREATE TABLE IF NOT EXISTS `news`(" + "`id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`time` text," 
					+ "`title` text,"
					+ "`category` text,"
					+ "`link` text,"
					+ "`description` text,"
					+ "`img` text,"
					+" PRIMARY KEY (`id`)"
					+ ")character set = utf8;";

			stmt.executeUpdate(itemSQL);
			System.out.println("Created table in given database...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertNewItem(NewsItem news){
		String sqlString=
				"INSERT INTO news(time,title,category,link,description,img) " +
				        "VALUES ('"+news.getTime()+"', '"+news.getTitle()
				        +"', '"+news.getCategory()+"', '"+news.getLink()+"','"+news.getDescription()+"','"+news.getImg()+"');";
		try {
			LogUtil.show("insertNewItem:"+sqlString);
			BaseDb.getInstance().getTBConn().createStatement().executeUpdate(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.show("insertNewItem error,message:"+e.getMessage());
		}
	}
	public void insertNewItemList(List<NewsItem> newsList){
		Connection conn=BaseDb.getInstance().getTBConn();
		for(NewsItem news:newsList){
			String sqlString=
					"INSERT INTO news(time,title,category,link,description,img) " +
					        "VALUES ('"+news.getTime()+"', '"+news.getTitle()
					        +"', '"+news.getCategory()+"', '"+news.getLink()+"','"+news.getDescription()+"','"+news.getImg()+"');";
			try {
				LogUtil.show("insertNewItem:"+sqlString);
				conn.createStatement().executeUpdate(sqlString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.show("insertNewItemList error,message:"+e.getMessage());
			}
		}
		LogUtil.show("end insert newsItem,count:"+newsList.size());
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//clearDuplicate();
	}
	public List<NewsItem> getItems(String category){
		List<NewsItem> result=new ArrayList<NewsItem>();	
		Connection conn = BaseDb.getInstance().getTBConn();
		System.out.println("select table ...category:"+category);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String itemSQL = "SELECT * FROM news WHERE category='"+category+"' order by id desc";

			ResultSet rs= stmt.executeQuery(itemSQL);
			while(rs.next()){
				NewsItem item=new NewsItem();
				item.setTime(rs.getString("time"));
				item.setTitle(rs.getString("title"));
				item.setCategory(rs.getString("category"));
				item.setLink(rs.getString("link"));
				item.setDescription(rs.getString("description"));
				item.setImg(rs.getString("img"));
				result.add(item);
			}
			System.out.println("end select table :"+"num:"+result.size()+"___"+result.toString());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public void clearDuplicate(){
		LogUtil.show("start clear duplicate news...");
		String sql1="create temporary table tmp_news select * from news group by title having count(title)>=1;";
		String sql2="truncate table news";
		String sql3="insert into news select * from tmp_news;";
		Connection conn=BaseDb.getInstance().getTBConn();
		try {
			conn.createStatement().execute(sql1);
			conn.createStatement().execute(sql2);
			conn.createStatement().execute(sql3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogUtil.show("end clear duplicate news...");
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clearNews(){
		LogUtil.show("start clear news in database...");
		String sql2="truncate table news;";
		Connection conn=BaseDb.getInstance().getTBConn();
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
	//login operation bellow
	public void createUserTable() {
		Connection conn = BaseDb.getInstance().getTBConn();
		LogUtil.show("Creating user table in news table...");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String itemSQL = "CREATE TABLE IF NOT EXISTS `user`(" + "`id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`userName` text," 
					+ "`userPwd` text,"
					+ "`description` text,"
					+" PRIMARY KEY (`id`)"
					+ ")character set = utf8;";
			stmt.executeUpdate(itemSQL);
			System.out.println("Created table in given database...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean insertUser(UserAccount userAccount){
		String sqlString=
				"INSERT INTO user(userName,userPwd,description) " +
				        "VALUES ('"+userAccount.getUserName()+"', '"+userAccount.getUserPwd()
				        +"', '"+userAccount.getDescription()+"');";
		try {
			if(isUserExist(userAccount)){
				LogUtil.show("insertNewUser error,message: user is exist,name="+userAccount.getUserName());
				return false;
			}
			LogUtil.show("insertNewUser:"+sqlString);
			BaseDb.getInstance().getTBConn().createStatement().executeUpdate(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.show("insertNewUser error,message:"+e.getMessage());
			return false;
		}
		return true;
	}
	public LoginResult login(UserAccount userAccount) {
		// TODO Auto-generated method stub
		LogUtil.show("start login with userName="+userAccount.getUserName()+",userPwd="+userAccount.getUserPwd());
		String sql2="select userName from user where userName='"+userAccount.getUserName()+"' and userPwd='"+userAccount.getUserPwd()+"'";
		LoginResult item=new LoginResult();
		Connection conn = BaseDb.getInstance().getTBConn();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(sql2);
			if(rs.next()){
				String nValue = rs.getString("userName");
				if(rs.wasNull()){
					item.setUserName(rs.getString("userName"));
					item.setResult(false);
				}
				item.setUserName(rs.getString("userName"));
				item.setResult(true);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	public boolean isUserExist(UserAccount userAccount) {
		// TODO Auto-generated method stub
		boolean result=false;
		LogUtil.show("start check userName="+userAccount.getUserName()+",userPwd="+userAccount.getUserPwd());
		String sql2="select userName from user where userName='"+userAccount.getUserName()+"'";
		LoginResult item=new LoginResult();
		Connection conn = BaseDb.getInstance().getTBConn();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(sql2);
			if(rs.next()){
				String nValue = rs.getString("userName");
				if(rs.wasNull()){
					result=false;
				}
				result=true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void clearUsers(){
		LogUtil.show("start clear user in database...");
		String sql2="truncate table user;";
		Connection conn=BaseDb.getInstance().getTBConn();
		try {
			conn.createStatement().execute(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogUtil.show("end clear user in database...");
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
