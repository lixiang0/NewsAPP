package pub.string.http.pojo;

public class NewsItem {
	int id=0;
	String time="Thu Aug 11 14:47:31 CST 2016";
	String title="test";
	String category="test";
	String link="test";
	String description="test";
	String img="";
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "NewsItem [id=" + id + ", time=" + time + ", title=" + title + ", category=" + category + ", link="
				+ link + ", description=" + description + ", img=" + img + "]";
	}

}
