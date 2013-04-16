package com.ruyicai.wap.bean;
/**
 * 开奖信息实体类
 * @author 沈鹏兰
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：
* 创建日期：
* 修改记录：
 */

import java.io.Serializable;

public class Wininfo implements Serializable {
	
	private String id ;				//流水号
	private String lotNo;			// 彩种
	private String termCode;		// 期号
	private String agencyNo;		// 发行机构,渠道号
	private String playName;		// 玩法英文名字
	private String winBaseCode;		// 中奖号码
	private String winSpecialCode;	// 特别号码
	private String actSellAmt;		// 本期实际销售额
	private String validSellAmt;	// 本期有效销售额
	private String forWardAmt;		// 滚入下期
	private String grade0;			//一等奖
	private String money0;			//一等奖金额
	private String number0;			//一等奖注数
	private String grade1;
	private String money1;
	private String number1;
	private String grade2;
	private String money2;
	private String number2;
	private String grade3;
	private String money3;
	private String number3;
	private String grade4;
	private String money4;
	private String number4;
	private String grade5;
	private String money5;
	private String number5;
	private String grade6;
	private String money6;
	private String number6;
	private String grade7;
	private String money7;
	private String number7;
	private String grade8;
	private String money8;
	private String number8;	
	
	public Wininfo()
	{
		
	}
	
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getTermCode() {
		return termCode;
	}
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	public String getWinBaseCode() {
		return winBaseCode;
	}
	public void setWinBaseCode(String winBaseCode) {
		this.winBaseCode = winBaseCode;
	}
	public String getWinSpecialCode() {
		return winSpecialCode;
	}
	public void setWinSpecialCode(String winSpecialCode) {
		this.winSpecialCode = winSpecialCode;
	}
	public String getActSellAmt() {
		return actSellAmt;
	}
	public void setActSellAmt(String actSellAmt) {
		this.actSellAmt = actSellAmt;
	}
	public String getValidSellAmt() {
		return validSellAmt;
	}
	public void setValidSellAmt(String validSellAmt) {
		this.validSellAmt = validSellAmt;
	}
	public String getForWardAmt() {
		return forWardAmt;
	}
	public void setForWardAmt(String forWardAmt) {
		this.forWardAmt = forWardAmt;
	}
	public String getGrade0() {
		return grade0;
	}
	public void setGrade0(String grade0) {
		this.grade0 = grade0;
	}
	public String getMoney0() {
		return money0;
	}
	public void setMoney0(String money0) {
		this.money0 = money0;
	}
	public String getNumber0() {
		return number0;
	}
	public void setNumber0(String number0) {
		this.number0 = number0;
	}
	public String getGrade1() {
		return grade1;
	}
	public void setGrade1(String grade1) {
		this.grade1 = grade1;
	}
	public String getMoney1() {
		return money1;
	}
	public void setMoney1(String money1) {
		this.money1 = money1;
	}
	public String getNumber1() {
		return number1;
	}
	public void setNumber1(String number1) {
		this.number1 = number1;
	}
	public String getGrade2() {
		return grade2;
	}
	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}
	public String getMoney2() {
		return money2;
	}
	public void setMoney2(String money2) {
		this.money2 = money2;
	}
	public String getNumber2() {
		return number2;
	}
	public void setNumber2(String number2) {
		this.number2 = number2;
	}
	public String getGrade3() {
		return grade3;
	}
	public void setGrade3(String grade3) {
		this.grade3 = grade3;
	}
	public String getMoney3() {
		return money3;
	}
	public void setMoney3(String money3) {
		this.money3 = money3;
	}
	public String getNumber3() {
		return number3;
	}
	public void setNumber3(String number3) {
		this.number3 = number3;
	}
	public String getGrade4() {
		return grade4;
	}
	public void setGrade4(String grade4) {
		this.grade4 = grade4;
	}
	public String getMoney4() {
		return money4;
	}
	public void setMoney4(String money4) {
		this.money4 = money4;
	}
	public String getNumber4() {
		return number4;
	}
	public void setNumber4(String number4) {
		this.number4 = number4;
	}
	public String getGrade5() {
		return grade5;
	}
	public void setGrade5(String grade5) {
		this.grade5 = grade5;
	}
	public String getMoney5() {
		return money5;
	}
	public void setMoney5(String money5) {
		this.money5 = money5;
	}
	public String getNumber5() {
		return number5;
	}
	public void setNumber5(String number5) {
		this.number5 = number5;
	}
	public String getGrade6() {
		return grade6;
	}
	public void setGrade6(String grade6) {
		this.grade6 = grade6;
	}
	public String getMoney6() {
		return money6;
	}
	public void setMoney6(String money6) {
		this.money6 = money6;
	}
	public String getNumber6() {
		return number6;
	}
	public void setNumber6(String number6) {
		this.number6 = number6;
	}
	public String getGrade7() {
		return grade7;
	}
	public void setGrade7(String grade7) {
		this.grade7 = grade7;
	}
	public String getMoney7() {
		return money7;
	}
	public void setMoney7(String money7) {
		this.money7 = money7;
	}
	public String getNumber7() {
		return number7;
	}
	public void setNumber7(String number7) {
		this.number7 = number7;
	}
	public String getGrade8() {
		return grade8;
	}
	public void setGrade8(String grade8) {
		this.grade8 = grade8;
	}
	public String getMoney8() {
		return money8;
	}
	public void setMoney8(String money8) {
		this.money8 = money8;
	}
	public String getNumber8() {
		return number8;
	}
	public void setNumber8(String number8) {
		this.number8 = number8;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgencyNo() {
		return agencyNo;
	}

	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}

	public String getPlayName() {
		return playName;
	}

	public void setPlayName(String playName) {
		this.playName = playName;
	}
}
