package com.network.bean;

public class NewsData {

    public NewsData(String time, String category, String title, String img, int id, String text, String link) {
        this.time = time;
        this.category = category;
        this.title = title;
        this.img = img;
        this.id = id;
        this.text = text;
        this.link = link;
    }

    /**
     * time : Fri, 08 Dec 2017 06:13:00 GMT
     * category : 1
     * title : 内蒙古24个项目入选“2017全国优选旅游项目” - 新浪网
     * img : t0.gstatic.com/images?q\u003dtbn:ANd9GcRJc023FTqF3wtzBsmcYiikyr7kKQeY9L7FPu7CUO-WcIU6f9aH7_dO-z5LX-hJgD700LZz86QF
     * id : 1
     * text : 在近日召开的2017年全国旅游投融资促进大会上，国家旅游局会同国家开发银行等12家金融机构综合考虑项目成熟度、开工条件、市场前景、示范引领和带动作用，共同遴选推出了全国680个优选旅游项目，主要包括景区提升改造 ...
     * link : http://news.sina.com.cn/c/2017-12-08/doc-ifypnqvn1474616.shtml
     */

    private String time;
    private String category;
    private String title;
    private String img;
    private int id;
    private String text;
    private String link;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "time='" + time + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
