package com.ruyicai.wap.bean;



public class Channel {
	int enable;
	String channelId, homeTemplate, nonTemplate, mobileReg, domain,
			chargeDescribe;

	public String getNonTemplate() {
		return nonTemplate;
	}

	public String getHomeTemplate() {
		return homeTemplate;
	}

	public void setHomeTemplate(String homeTemplate) {
		this.homeTemplate = homeTemplate;
	}

	public void setNonTemplate(String nonTemplate) {
		this.nonTemplate = nonTemplate;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain != null ? domain.trim() : domain;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getMobileReg() {
		return mobileReg;
	}

	public String getChargeDescribe() {
		return chargeDescribe;
	}

	public void setChargeDescribe(String chargeDescribe) {
		this.chargeDescribe = chargeDescribe;
	}

	public void setMobileReg(String mobileReg) {
		this.mobileReg = mobileReg;
	}

}
