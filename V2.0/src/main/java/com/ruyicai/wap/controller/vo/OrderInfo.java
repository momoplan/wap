package com.ruyicai.wap.controller.vo;

public class OrderInfo {
	
	/*
	 * 方案编号	唯一标识
	 */
	String id = "";
	/*
	 * 彩种编号	
	 */
	String lotNo = "";
	/*
	 * 方案发起时间	
	 */
	String caseStartTime = "";
	/*
	 * 方案认购截止时间	
	 */
	String caseEndTime = "";
	/*
	 * 期号	
	 */
	String batchCode = "";	
	/*
	 * 玩法	1为单式，2为复式，3为胆拖
	 */
	Integer drawWay = 0;
	/*
	 * 总金额	
	 */
	Integer caseAllAmt = 0;
	/*
	 * 倍数	
	 */
	Integer caseNum = 0;
	/*
	 * 每份金额	
	 */
	Integer caseOneAmt = 0;
	/*
	 * 发起人提成比例	
	 */
	Integer pushMoney = 0;
	/*
	 * 方案状态	
	 */
	Integer flag = 0;
	/*
	 * 方案进度	
	 */
	Integer caseBuyAfterAmt = 0;
	/*
	 * 保底份数	
	 */
	Integer caseBaoDiAmt = 0;
	/*
	 * 认购份数	
	 */
	Integer caseBuyAmtByUser = 0;
	/*
	 * 单式方案名称	
	 */
	String caseFileName = "";
	/*
	 * 方案标题	
	 */
	String caseTitle = "";
	/*
	 * 复式方案内容	
	 */
	String caseContent = "";
	/*
	 * 方案描述	
	 */
	String describe = "";
	/*
	 * 开放状态	
	 */
	Integer openFlag = 0;
	/*
	 * 如意彩保底份数	
	 */
	Integer baodiByRuYiCai = 0;
	/*
	 * 中小奖总金额	
	 */
	Integer winLittleAmt = 0;
	/*
	 * 中大奖总金额	
	 */
	Integer winBigAmt = 0;
	/*
	 * 方案投注失败信息	
	 */
	String caseLostInfo = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getCaseStartTime() {
		return caseStartTime;
	}
	public void setCaseStartTime(String caseStartTime) {
		this.caseStartTime = caseStartTime;
	}
	public String getCaseEndTime() {
		return caseEndTime;
	}
	public void setCaseEndTime(String caseEndTime) {
		this.caseEndTime = caseEndTime;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public Integer getDrawWay() {
		return drawWay;
	}
	public void setDrawWay(Integer drawWay) {
		this.drawWay = drawWay;
	}
	public Integer getCaseAllAmt() {
		return caseAllAmt;
	}
	public void setCaseAllAmt(Integer caseAllAmt) {
		this.caseAllAmt = caseAllAmt;
	}
	public Integer getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(Integer caseNum) {
		this.caseNum = caseNum;
	}
	public Integer getCaseOneAmt() {
		return caseOneAmt;
	}
	public void setCaseOneAmt(Integer caseOneAmt) {
		this.caseOneAmt = caseOneAmt;
	}
	public Integer getPushMoney() {
		return pushMoney;
	}
	public void setPushMoney(Integer pushMoney) {
		this.pushMoney = pushMoney;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getCaseBuyAfterAmt() {
		return caseBuyAfterAmt;
	}
	public void setCaseBuyAfterAmt(Integer caseBuyAfterAmt) {
		this.caseBuyAfterAmt = caseBuyAfterAmt;
	}
	public Integer getCaseBaoDiAmt() {
		return caseBaoDiAmt;
	}
	public void setCaseBaoDiAmt(Integer caseBaoDiAmt) {
		this.caseBaoDiAmt = caseBaoDiAmt;
	}
	public Integer getCaseBuyAmtByUser() {
		return caseBuyAmtByUser;
	}
	public void setCaseBuyAmtByUser(Integer caseBuyAmtByUser) {
		this.caseBuyAmtByUser = caseBuyAmtByUser;
	}
	public String getCaseFileName() {
		return caseFileName;
	}
	public void setCaseFileName(String caseFileName) {
		this.caseFileName = caseFileName;
	}
	public String getCaseTitle() {
		return caseTitle;
	}
	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}
	public String getCaseContent() {
		return caseContent;
	}
	public void setCaseContent(String caseContent) {
		this.caseContent = caseContent;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(Integer openFlag) {
		this.openFlag = openFlag;
	}
	public Integer getBaodiByRuYiCai() {
		return baodiByRuYiCai;
	}
	public void setBaodiByRuYiCai(Integer baodiByRuYiCai) {
		this.baodiByRuYiCai = baodiByRuYiCai;
	}
	public Integer getWinLittleAmt() {
		return winLittleAmt;
	}
	public void setWinLittleAmt(Integer winLittleAmt) {
		this.winLittleAmt = winLittleAmt;
	}
	public Integer getWinBigAmt() {
		return winBigAmt;
	}
	public void setWinBigAmt(Integer winBigAmt) {
		this.winBigAmt = winBigAmt;
	}
	public String getCaseLostInfo() {
		return caseLostInfo;
	}
	public void setCaseLostInfo(String caseLostInfo) {
		this.caseLostInfo = caseLostInfo;
	}
}
