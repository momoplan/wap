package com.ruyicai.wap.controller.vo;

/*
 * 合买方案请求实体
 */
public class IssueInfo {

	/*
	 * 用户手机号码 唯一标识
	 */
	String mobile_code = "";
	
	String userno = "";
	/*
	 * 合买总金额
	 */
	Integer allamt = 0;
	/*
	 * 倍数
	 */
	Integer num = 0;
	/*
	 * 每份金额
	 */
	Integer oneamt = 0;
	/*
	 * 方案文件名称
	 */
	String name = "";
	/*
	 * 复式合买方案内容 默认为‘‘rychm’’，json串
	 */
	String muchcontent = "";
	/*
	 * 期号 数据库读取
	 */
	String batch = "";
	/*
	 * 彩种编号
	 */
	String lotno = "";
	/*
	 * 方案玩法
	 */
	String drawway = "";
	String bettype = "";//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
	/*
	 * 保底份数 默认为0
	 */
	Integer baodiamt = 0;
	/*
	 * 认购份数 默认为0
	 */
	Integer buyamt_by_user = 0;
	public String getBettype() {
		return bettype;
	}

	public void setBettype(String bettype) {
		this.bettype = bettype;
	}

	/*
	 * 方案总份数 客户端计算，如果值为非整数，则提示用户重新设置方案单份金额/方案总金额
	 */
	Integer allNum = 0;
	/*
	 * 发起人提成比例 默认为0，一般情况为0-10%，超过10%以后需要认购2倍于佣金的份数，客户端要进行严重
	 */
	Integer pushmoney = 0;
	/*
	 * 方案标题 默认为“如意彩合买”
	 */
	String title = "如意彩合买";
	/*
	 * 方案推荐描述 默认为‘‘rychm’’
	 */
	String descible = "rychm";
	/*
	 * 公开标识0为公开，1为方案结束后公开，2为认购后公开（0：页面浏览用户均可看到复式方案内容或者下载单式方案文件；1：
	 * 用户只能在方案认购时间截止后才能看到复式方案内容或者下载单式方案文件
	 * ；2：只有认购此方案，认购用户才能看到复式方案内容或者下载单式方案文件）,默认为0
	 */
	Integer open_flag = 0;
	/*
	 * 接入方式 接入方式有以下三种形式：”B”，’W’，’’C’， 默认为‘B’
	 */
	String acceptype = "B";

	public String getMobile_code() {
		return mobile_code;
	}

	public void setMobile_code(String mobile_code) {
		this.mobile_code = mobile_code;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public Integer getAllamt() {
		return allamt;
	}
	public void setAllamt(Integer allamt) {
		this.allamt = allamt;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getOneamt() {
		return oneamt;
	}

	public void setOneamt(Integer oneamt) {
		this.oneamt = oneamt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMuchcontent() {
		return muchcontent;
	}

	public void setMuchcontent(String muchcontent) {
		this.muchcontent = muchcontent;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getLotno() {
		return lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno;
	}

	public String getDrawway() {
		return drawway;
	}

	public void setDrawway(String drawway) {
		this.drawway = drawway;
	}

	public Integer getBaodiamt() {
		return baodiamt;
	}

	public void setBaodiamt(Integer baodiamt) {
		this.baodiamt = baodiamt;
	}

	public Integer getBuyamt_by_user() {
		return buyamt_by_user;
	}

	public void setBuyamt_by_user(Integer buyamt_by_user) {
		this.buyamt_by_user = buyamt_by_user;
	}

	public Integer getAllNum() {
		return allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getPushmoney() {
		return pushmoney;
	}

	public void setPushmoney(Integer pushmoney) {
		this.pushmoney = pushmoney;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescible() {
		return descible;
	}

	public void setDescible(String descible) {
		this.descible = descible;
	}

	public Integer getOpen_flag() {
		return open_flag;
	}

	public void setOpen_flag(Integer open_flag) {
		this.open_flag = open_flag;
	}

	public String getAcceptype() {
		return acceptype;
	}

	public void setAcceptype(String acceptype) {
		this.acceptype = acceptype;
	}
}
