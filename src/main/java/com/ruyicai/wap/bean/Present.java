package com.ruyicai.wap.bean;

public class Present {
	private String id ;//ID
	private String mobile;//手机号赠送查询时显示被赠送者的手机号
	private String userName;//用户名被赠送查询的时候显示赠送者的用户名
	private String amt;//金额
	private String batchCode;//期号
	private String lotName;//彩种名称
	private String betType; //彩种类型
	private String beishu;//倍数
	private String betCode;//注码
	private String createTime;//时间
	private String reciveState;//是否领取彩票用于被赠送查询0:未领取1:已领取 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public String getBeishu() {
		return beishu;
	}
	public void setBeishu(String beishu) {
		this.beishu = beishu;
	}
	public String getBetCode() {
		return betCode;
	}
	public void setBetCode(String betCode) {
		this.betCode = betCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReciveState() {
		return reciveState;
	}
	public void setReciveState(String reciveState) {
		this.reciveState = reciveState;
	}
	
	
	
	
}
