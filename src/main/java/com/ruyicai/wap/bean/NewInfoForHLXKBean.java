package com.ruyicai.wap.bean;

import java.io.Serializable;

public class NewInfoForHLXKBean implements Serializable{

    String id;
	String title ;
	String url ;
	String date;

	public NewInfoForHLXKBean() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
