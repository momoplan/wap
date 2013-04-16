package com.ruyicai.wap.bean;

import com.jrt.common.annotation.db.Table;

@Table(name = "news.News")
public class News implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String title2;
	private String content;
	private String pubDate;
	private String orderId;
	private String userId;
	private String soruceFrom;
	private String type;
	private String categoryId;
	private String channelId;
	private String checked;
	private String shareCount;

	public String getShareCount() {
		return shareCount;
	}

	public void setShareCount(String shareCount) {
		this.shareCount = shareCount;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content.replaceAll("\\r\\n|\\n|\\r", "<br/>");
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSoruceFrom() {
		return soruceFrom;
	}

	public void setSoruceFrom(String soruceFrom) {
		this.soruceFrom = soruceFrom;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	// private static SimpleDateFormat format = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	//
	// public String getNewsDateTime() {
	// return format.format(pubDate);// nb.getNewsDateTime().substring(0, 10);
	// }

}
