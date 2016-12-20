package pub.string.http.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pub.string.http.pojo.NewsItem;
import pub.string.http.pojo.URL;

public class NewsUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogUtil.show(NewsUtil.getNewsByCategory(URL.URL_ENTAINMENT).toString());
	}
    
	public static List<NewsItem> getNewsByCategory(String newUrl){
		Document doc = null;
		List<NewsItem> result=new ArrayList<NewsItem>();
		try {
			 doc = Jsoup.connect(newUrl).get();
			 LogUtil.show("network access success");
		}catch(java.net.ConnectException e){	
			LogUtil.show("network access error:"+e.getMessage());
			return null;
		} catch (IOException e3) {
			// TODO Auto-generated catch block
//			e3.printStackTrace();
			LogUtil.show("io access error:"+e3.getMessage());
			return null;
		}
		System.out.println("系统更新时间:"+doc.getElementsByTag("pubdate").get(0).text());
		String item="item";
		Elements content = doc.getElementsByTag(item);
		System.out.println("本次更新条目数量:"+content.size());
		ArrayList<Elements> list=new ArrayList<Elements>();
		for (Element link : content) {
			NewsItem news=new NewsItem();
			String time = null;
			try {
				time = new String(link.getElementsByTag("pubdate").text().getBytes(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("发布时间:"+time);
			news.setTime(time);
			String title = null;
			try {
				title = new String(link.getElementsByTag("title").text().getBytes(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println("标题:"+title);
		    news.setTitle(title.replace("'", "+"));
		    String category=link.getElementsByTag("category").get(0).text();
			System.out.println("分类:"+category);
			news.setCategory(category);
			Elements description=link.getElementsByTag("description");
			String urlString=description.toString();
			if(urlString.indexOf("img src=")!=-1){
			//	LogUtil.show(urlString);
			//	LogUtil.show(urlString.lastIndexOf("img src=")+"_____start position");
				urlString=urlString.substring(urlString.lastIndexOf("img src=")+11, urlString.length()-1);
			//	LogUtil.show(urlString.indexOf("\" ")+"_____start position");
				urlString=urlString.substring(0,urlString.indexOf("\" "));
				LogUtil.show("图片："+urlString);
				news.setImg(urlString);
			}else{
				news.setImg("");
			}
			for (Element t : description) {
				Document d=Jsoup.parse(t.text());
				Elements e1=d.getElementsByClass("lh");
				for (Element t1 : e1) {
					String url=t1.getElementsByAttribute("href").get(0).attr("href");
					int ii=url.indexOf("url=");
					int length=url.length();
					url=url.substring(ii+4, length);
					System.out.println("链接:"+url);
					news.setLink(url);
					String description2=t1.getElementsByAttribute("size").get(1).text();
					System.out.println("摘要:"+description2);
					news.setDescription(description2.replace("'", "+"));
					System.out.println("______________________________");
					result.add(news);
				}
			}
	}
		return result;
	}
}
