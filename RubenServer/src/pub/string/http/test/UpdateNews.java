package pub.string.http.test;

import java.util.ArrayList;
import java.util.List;

import pub.string.http.db.DBHelp;
import pub.string.http.pojo.NewsItem;
import pub.string.http.pojo.URL;
import pub.string.http.util.LogUtil;
import pub.string.http.util.NewsUtil;

public class UpdateNews {

	public static void main(String[] args){
		List<NewsItem> list=new ArrayList<NewsItem>();
		String[] urls={URL.URL_ENTAINMENT,URL.URL_FINANCE,URL.URL_FOCUS,URL.URL_LOCAL,URL.URL_MAINLAND,URL.URL_SPORT,URL.URL_TECH,URL.URL_WORLD};
		for(String url : urls){
			List<NewsItem> temp= NewsUtil.getNewsByCategory(url);
			if(temp!=null){
				list.addAll(temp);
			}
		}
		if(list.size()<=0){
			LogUtil.show("get news error,list size is 0,update news failed");
			return;
		}
		DBHelp db=new DBHelp();
		db.clearNews();
		db.insertNewItemList(list);
	}

}
