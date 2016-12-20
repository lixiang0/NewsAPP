package pub.string.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pub.string.http.db.DBHelp;
import pub.string.http.pojo.LoginResult;
import pub.string.http.pojo.UserAccount;
import pub.string.http.util.LogUtil;

public class ServletLogin extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		Enumeration paras = request.getParameterNames();
		String[] keys = new String[11];
		String userName="";
		String userPwd="";
		while (paras.hasMoreElements()) {
			String key = (String) paras.nextElement();
			sb.append(key + ":");
			sb.append(request.getParameter(key) + "\n");
			if (key.equals("userName")) {
				userName = request.getParameter(key);
			}			
			if (key.equals("userPwd")) {
				userPwd = request.getParameter(key);
			}
		}
		LogUtil.show("ServletLogin___" + sb.toString());

		DBHelp db = new DBHelp();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.println(gson.toJson(db.login(new UserAccount(userName,userPwd))));
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

	}

}
