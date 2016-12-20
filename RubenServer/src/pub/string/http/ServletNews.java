package pub.string.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pub.string.http.db.DBHelp;
import pub.string.http.pojo.Category;
import pub.string.http.util.LogUtil;

public class ServletNews extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder sb = new StringBuilder();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		Enumeration paras = request.getParameterNames();
		String[] keys = new String[11];
		String type = "";
		while (paras.hasMoreElements()) {
			String key = (String) paras.nextElement();
			sb.append(key + ":");
			sb.append(request.getParameter(key) + "\n");
			if (key.equals("type")) {
				type = request.getParameter(key);
			}
		}
		LogUtil.show("ServletNews___" + sb.toString());

		DBHelp db = new DBHelp();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.println(gson.toJson(db.getItems(getStringById(Integer.parseInt(type)))));

		// String title = "title";
		// String docType =
		// "<!doctype html public \"-//w3c//dtd html 4.0 " +
		// "transitional//en\">\n";
		// out.println(docType +
		// "<html>\n" +
		// "<head><title>" + "login sample" + "</title></head>\n" +
		// "<body bgcolor=\"#f0f0f0\">\n" +
		// "<h1 align=\"center\">" + "Login" + "</h1>\n" +
		// "<ul>\n" +
		// " <li><b>username</b>:"
		// + request.getParameter("user") + "\n" +
		// " <li><b>passwd</b>:"
		// + request.getParameter("pass") + "\n" +
		// "</ul>\n" +
		// "</body></html>");

	}

	private String getStringById(int parseInt) {
		// TODO Auto-generated method stub
		String result = "焦点";
		switch (parseInt) {
		case Category.Entainment:
			result = "娱乐";
			break;
		case Category.Finance:
			result = "财经";
			break;
		case Category.Focus:
			result = "焦点新闻";
			break;
		case Category.Mainland:
			result = "国内";
			break;
		case Category.Native:
			result = "本地";
			break;
		case Category.Sport:
			result = "体育";
			break;
		case Category.Technology:
			result = "科技";
			break;
		case Category.World:
			result = "国际/港台";
			break;
		}
		return result;
	}
}