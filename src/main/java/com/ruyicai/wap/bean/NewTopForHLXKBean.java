package com.ruyicai.wap.bean;

import java.util.List;

public class NewTopForHLXKBean {
	String title ;
	String url ;
	List<NewInfoForHLXKBean> newInfoForHLXKBean;
	List<NewChannelForHLXKBean> channelForHLXKBeans;

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
	public List<NewInfoForHLXKBean> getNewInfoForHLXKBean() {
		return newInfoForHLXKBean;
	}
	public void setNewInfoForHLXKBean(List<NewInfoForHLXKBean> newInfoForHLXKBean) {
		this.newInfoForHLXKBean = newInfoForHLXKBean;
	}
	public List<NewChannelForHLXKBean> getChannelForHLXKBeans() {
		return channelForHLXKBeans;
	}
	public void setChannelForHLXKBeans(
			List<NewChannelForHLXKBean> channelForHLXKBeans) {
		this.channelForHLXKBeans = channelForHLXKBeans;
	}
	
	
}
