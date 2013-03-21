package com.ruyicai.wap.controller.vo;

import java.util.Map;

public class BetBean implements Comparable<BetBean> {
	
	private String error_code;//返回响应码
	private String play_name;//彩种 
	private String batchcode;//期号
	private String sell_cate;//投注时间
	private long amt;//投注金额
	private String betcodeAll;//注码串
	private Map betcode;//注码所有类型
	private int maxLine;//总条数
	private String cash_date_time;//结束时间
	private String state;//出票状态
	private String betType;//投注方式
	private String mulNo;//倍数
	private String orderBetType;//订单投注类型zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
	private String winCode;//中奖号码
	private String wanfa;//玩法竞彩用
	private String type;//标记状态区分竞彩和普通彩种
	private String prizeamt;
	
	public BetBean()
	{
		this.error_code="";
		this.play_name="";
		this.batchcode="";
		this.amt=0;
		this.betcode=null;
		this.sell_cate="";
		this.betcodeAll="";
		this.maxLine=0;
		this.cash_date_time="";
		this.betType="";
		this.mulNo="";
		this.orderBetType="";
		this.winCode = "";
		this.wanfa = "";
		this.prizeamt = "";
	}
	
	public String getPrizeamt() {
		return prizeamt;
	}

	public void setPrizeamt(String prizeamt) {
		this.prizeamt = prizeamt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWanfa() {
		return wanfa;
	}

	public void setWanfa(String wanfa) {
		this.wanfa = wanfa;
	}

	public String getWinCode() {
		return winCode;
	}

	public void setWinCode(String winCode) {
		this.winCode = winCode;
	}

	public String getOrderBetType() {
		return orderBetType;
	}

	public void setOrderBetType(String orderBetType) {
		this.orderBetType = orderBetType;
	}

	public String getBetcodeAll() {
		return betcodeAll;
	}
	public void setBetcodeAll(String betcodeAll) {
		this.betcodeAll = betcodeAll;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public String getMulNo() {
		return mulNo;
	}
	public void setMulNo(String mulNo) {
		this.mulNo = mulNo;
	}
	public String getError_code() {
		return error_code;
	}
	public String getSell_cate() {
		return sell_cate;
	}
	public void setSell_cate(String sell_cate) {
		this.sell_cate = sell_cate;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getPlay_name() {
		return play_name;
	}
	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public long getAmt() {
		return amt;
	}
	public void setAmt(long amt) {
		this.amt = amt/100;
	}
	public Map getBetcode() {
		return betcode;
	}
	public void setBetcode(Map betcode) {
		this.betcode = betcode;
	}
	public int getMaxLine() {
		return maxLine;
	}
	public void setMaxLine(int maxLine) {
		this.maxLine = maxLine;
	}
	public String getCash_date_time() {
		return cash_date_time;
	}
	public void setCash_date_time(String cash_date_time) {
		this.cash_date_time = cash_date_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		if(state.equals("1")){
		this.state = "出票成功";
		}
		if(state.equals("2")){
			this.state = "处理中";
		}
		else {
			this.state ="";
		}
	}

	@Override
	public int compareTo(BetBean o) {
		// TODO Auto-generated method stub
		  return o.getSell_cate().compareTo(this.getSell_cate()); 
	}
	
}
