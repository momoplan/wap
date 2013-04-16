package com.ruyicai.wap.bean;

public class Account {
	private String balance;//总金额
	private String freezeBalance;//冻结金额
	private String drawBalance;//可提现金额
	private String betBalance;//可投注金额
	private String amt;
	private String memo;
	private String plattime;
	public Account(){
		this.balance="0.00";
		this.freezeBalance="0.00";
		this.drawBalance="0.00";
		this.betBalance="0.00";
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getFreezeBalance() {
		return freezeBalance;
	}
	public void setFreezeBalance(String freezeBalance) {
		this.freezeBalance = freezeBalance;
	}
	public String getDrawBalance() {
		return drawBalance;
	}
	public void setDrawBalance(String drawBalance) {
		this.drawBalance = drawBalance;
	}
	public String getBetBalance() {
		return betBalance;
	}
	public void setBetBalance(String betBalance) {
		this.betBalance = betBalance;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPlattime() {
		return plattime;
	}
	public void setPlattime(String plattime) {
		this.plattime = plattime;
	}
	
}
