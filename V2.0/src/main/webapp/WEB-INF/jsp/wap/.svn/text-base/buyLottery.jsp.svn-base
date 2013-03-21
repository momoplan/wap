<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取七乐彩开关
	String qilecaiDefine = rbint.getString("qilecaiDefine"); //取得七乐彩开关
	String TCDefine = rbint.getString("TCDefine"); //取得体彩开关
	String shishicaiSwitch = rbint.getString("shishicaiSwitch"); //取得时时彩开关
	String qixingcaiSwitch = rbint.getString("qixingcaiSwitch"); //取得七星彩开关
	String TCDefineArray5 = rbint.getString("TCDefineArray5");//获取体彩排列五的开关
	String duolecaiSwitch = rbint.getString("duolecaiSwitch"); //取得11选5 开关
	String duolecaiTBZHSwitch = rbint.getString("duolecaiTBZHSwitch"); //取得11选5特别追号 开关
	String elevenDuoJinSwitch = rbint.getString("elevenDuoJinSwitch");//获取山东十一运夺金开关
	String elevenDuoJinTBZHSwitch = rbint
			.getString("elevenDuojinTBZHSwitch");//获取山东十一运夺金特别追号开关
	//获取618的访问地址
	String requestUrl618 = request.getHeader("referer");
	String CHANNEL618 = request.getParameter("cn");
	if (requestUrl618 != null && !"".equals(requestUrl618)) {
		session.setAttribute("requestUrl618", requestUrl618);
	}
	if (CHANNEL618 != null && !"".equals(CHANNEL618)) {
		session.setAttribute("CHANNEL618", CHANNEL618);
	}
%>
<title>购彩大厅</title>
<body> 
 <a href="/wap/wapindex.jspx">首页</a>-购彩大厅<br/>
 	[<a href="/w/wap/DoubleBall/SingleSelfSelection.jspx">双色球</a>]
		<a href="/w/wap/DoubleBall/SingleSelfSelection.jspx">自选</a>、<a 
		   href="/w/wap/DoubleBall/SingleAutoSelection.jspx">机选</a><br/>
	[<a href="/w/wap/3D/3DDirectSingle.jspx">福彩3D</a>]
		<a href="/w/wap/3D/3DDirectSingle.jspx">直选</a>、<a 
		   href="/w/wap/3D/3DGroup3Single.jspx">组三</a>、<a 
		   href="/w/wap/3D/3DGroup6Single.jspx">组六</a>、<a 
		   href="/w/wap/3D/3DDantuoMultiple.jspx">胆拖</a><br/>
  	[<a href="/w/wap/qilecai/SingleSelfSelection.jspx">七乐彩</a>]
		<a href="/w/wap/qilecai/SingleSelfSelection.jspx">自选</a>、<a 
		   href="/w/wap/qilecai/SingleAutoSelection.jspx">机选</a><br/>
 	[<a href="/w/wap/daletou/SingleSelfSelection.jspx">大乐透</a>]
		<a href="/w/wap/daletou/SingleSelfSelection.jspx">自选</a>、<a 
		   href="/w/wap/daletou/SingleAutoSelection.jspx">机选</a><br/>
 	[<a href="/w/wap/array3/Array3DirectSingle.jspx">排列三</a>]
		<a href="/w/wap/array3/Array3DirectSingle.jspx">直选</a>、<a 
		   href="/w/wap/array3/Array3GroupHeZhi.jspx">组选</a>、<a 
		   href="/w/wap/array3/Array3Group3BH.jspx">组三</a>、<a 
		   href="/w/wap/array3/Array3Group6BH.jspx">组六</a><br/>
	[<a href="/w/wap/array5/array5ByHand.jspx">排列五</a>]
		<a href="/w/wap/array5/array5ByHand.jspx">自选</a>、<a 
		   href="/w/wap/array5/singleAutoSelection.jspx">机选</a><br/>
	[<a href="/w/wap/sevenStar/sevenStarByHand.jspx">七星彩</a>]
		<a href="/w/wap/sevenStar/sevenStarByHand.jspx">自选</a>、<a 
		   href="/w/wap/sevenStar/SingleAutoSelection.jspx">机选</a>、<a 
		   href="/w/wap/sevenStar/ddSevenStar.jspx">定胆</a><br/>
	[<a href="/select5from22/selfSelect.jspx">22选5</a>]
		<a href="/select5from22/selfSelect.jspx">自选</a>、<a 
		   href="/select5from22/autoSelect.jspx">机选</a>、<a 
		   href="/select5from22/dantuoSelect.jspx">胆拖</a><br/>
	[<a href="/w/wap/cqshishicai/sscOneStar.jspx">重庆时时彩</a>]
		<a href="/w/wap/cqshishicai/sscOneStar.jspx">一星</a>、<a 
		   href="/w/wap/cqshishicai/sscTowStar.jspx">二星</a>、<a 
		   href="/w/wap/cqshishicai/sscThreeStar.jspx">三星</a>、<a 
		   href="/wap/cqshishicai/sscFiveStar.jspx">五星</a>、<a 
		   href="/w/wap/cqshishicai/xddsFiveStar.jspx">大小单双</a><br/>
	[<a href="/w/wap/11select5/optionOne.jspx">江西11选5</a>]
		<a href="/w/wap/11select5/optionOne.jspx">任选</a>、<a 
		   href="/w/wap/11select5/ForwardTwoZX.jspx">直选</a>、<a 
		   href="/w/wap/11select5/ForwardTwoGroup.jspx">组选</a>、<a 
		   href="/w/wap/11select5/dantuoselection.jspx?type=2">胆拖</a><br/>
	[<a href="/wap/ElevenDuoJin/optionOne.jspx">十一运夺金</a>]
		<a href="/wap/ElevenDuoJin/optionOne.jspx">任选</a>、<a 
		   href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选</a>、<a 
		   href= "/wap/ElevenDuoJin/ForwardTwoGroup.jspx">组选</a>、<a 
		   href="/wap/ElevenDuoJin/dantuoR2.jspx">胆拖</a>、<a 
		   href="/w/add/toAddNumberBet.jspx?flag=rx1">特别追号</a><br/>
	[<a href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">广东11选5</a>]
		<a href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">任选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/directSelectForwardTwoSingleSelf.jspx">直选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/groupSelectForwardTwoSelf.jspx">组选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br/>
	[<a href="/w//guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">广东快乐十分</a>]
		<a href="/w//guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">任选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkDirectSelf.jspx">直选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkGroupSelf.jspx">组选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br>
	[<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01003">足彩</a>]
		<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01003">胜负彩</a>、<a 
		   href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01004">任选九场</a>、<a 
		   href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01006">六场半</a>、<a 
		   href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01005">四场进球</a><br>
	[<a href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">竞彩足球</a>]
		<a href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>、<a 
		   href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>、<a 
		   href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>、<a 
		   href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
 	[<a href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">竞彩篮球</a>]
 		<a href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>、<a 
 		   href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>、<a 
 		   href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>、<a 
 		   href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a><br/>
 	<br/>
		<a href="/w/wap/help/buySafe.jspx">购彩安全</a>　<a href="/w/wap/playIntroduced/introduce.jspx">玩法介绍</a><br/>
		<a href="/w/wap/help/howCharge.jspx">如何充值</a>　<a href="/w/wap/help/howBet.jspx">如何投注</a><br/>
		<a href="/w/wap/help/howReward.jspx">如何兑奖</a>　<a href="/w/wap/help/howReward.jspx">如何提款</a><br/>
		<% if((session.getAttribute("requestUrl618") != null && session.getAttribute("requestUrl618").toString().contains("618618")) || "363".equals(session.getAttribute("CHANNEL618"))){%>
		<a href="http://wap.618618.cn/">返回618618财经门户</a> <%} %>
		<br/>
	 <a href="/w/wap/wapindex.jspx">返回上一页</a>
</body>
