package com.ruyicai.wap.rss.bean;

/**
 * 
 * @author Administrator
 *
 */
public class Items {
	private String title;
	private String author;
	private String description;
	private String pubDate;   //发布时间
	private String lastBuildDate; //最后一次修改时间
	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	private String link;
	private String guid;
	
	
	public Items() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
