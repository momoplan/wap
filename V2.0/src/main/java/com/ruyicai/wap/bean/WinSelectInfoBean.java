package com.ruyicai.wap.bean;

/**
 * 开奖查询bean
 * author zhaokailong
 */
public class WinSelectInfoBean {
	private String batchcode ;//期号
	private String lotno ;//彩种编号
	private String winbasecode ;//注码
	private String winspecialcode ;//特殊号码 双色球蓝球
	private String opentime ;//开奖时间
	private String betCode ;//. 重组后的展示注码
	private String lotName;
	private String info;//本期详细信息(本期销量、奖池滚存、奖项、中奖注数、单注奖金等)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public WinSelectInfoBean() {
		super();
	}
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getWinbasecode() {
		return winbasecode;
	}
	public void setWinbasecode(String winbasecode) {
		this.winbasecode = winbasecode;
	}
	public String getWinspecialcode() {
		return winspecialcode;
	}
	public void setWinspecialcode(String winspecialcode) {
		this.winspecialcode = winspecialcode;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getBetCode() {
		return betCode;
	}
	public void setBetCode(String betCode) {
		this.betCode = betCode;
	}
	
	
}
