package pub.cpp.news.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xzh
 * 1.download google rss news by post
 * 2.download google rss news by get
 *
 */
public class NetUtils {

	public static void getByPost(String url,String path){
		Document doc = null;
		try {
			 doc = Jsoup.connect(url)
					  .data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(10000)
					  .post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outFile(doc,path);
	}
   public static void getByGet(String url,String path){
		Document doc = null;
		try {
			 doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outFile(doc,path);
   }
   public static String getNetStringByGet(String url){
		Document doc = null;
		try {
			 doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc.getElementsByTag("body").get(0).text();
   }
	public static String getNetStringByPost(String url ){
		Document doc = null;
		try {
			 doc = Jsoup.connect(url)
					  .data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(10000)
					  .post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc.getAllElements().toString();
	}
   private static void outFile(Document doc,String path){
	   	Elements outString=doc.getAllElements();
		String mFileName=doc.getElementsByTag("title").get(0).text();
		System.out.println("mFileName:"+mFileName);
		File mFile=new File(path+File.separator+mFileName+".html");
		System.out.println("mFile:"+mFile.getPath());
		FileOutputStream out = null;
		try {
			out=new FileOutputStream(mFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			out.write(outString.toString().getBytes("utf-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   }
   
   public static final void main(String[] args){
	   String url="http://cpp.pub/webnews/news?type=1";
	   String str=getNetStringByGet(url);
	   System.out.println(str);
   }
   
   
}
