<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ruyicai.wap.util.CommonUtil" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取七乐彩开关
	String qilecaiDefine = rbint.getString("qilecaiDefine");		//取得七乐彩开关
%><title>购彩大厅</title>
<body>
<a href="/wap/wapindex.jsp">首页</a>-购彩大厅<br/>
			两元博取大奖.彩票改变人生<br/>
			【福彩3D】<br/>
			直购：<a href="/single3D/autoSelectionDetail.jspx?beishu=1&amp;zhushu=1">2元</a>
			<a href="/single3D/autoSelectionDetail.jspx?beishu=1&amp;zhushu=2">4元</a>
			<a href="/single3D/autoSelectionDetail.jspx?beishu=1&amp;zhushu=3">6元</a>
			<a href="/single3D/autoSelectionDetail.jspx?beishu=1&amp;zhushu=4">8元</a>
			<br/>
			<a href="/wap/3D/3DDirectSingle.jspx">直选投注 </a><br/>
			<a href="/wap/3D/3DGroup3Single.jspx">组3单式</a> 　<a href="/wap/3D/3DGroup6Single.jspx">组6单式</a><br/>
			<a href="/wap/3D/3DGroup3Multiple.jspx">组3复式</a> 　<a href="/wap/3D/3DGroup6Multiple.jspx">组6复式</a><br/>
			<a href="/wap/3D/3DDantuoMultiple.jspx">胆拖复式</a> <a href="/wap/3D/3DDirectSelectionHeZhi.jspx">直选和值</a><br/>
			<a href="/wap/3D/3DGroup3HeZhi.jsp">组3和值</a> 　<a href="/wap/3D/3DGroup6HeZhi.jspx">组6和值</a><br/>
		     <br/>
			<a href="/wap/help/buySafe.jspx">购彩安全</a>　<a href="/wap/playIntroduced/introduce.jspx">玩法介绍</a><br/>
			<a href="/wap/help/howCharge.jspx">如何充值</a>　<a href="/wap/help/howBet.jspx">如何投注</a><br/>
			<a href="/wap/help/howReward.jspx">如何兑奖</a>　<a href="/wap/help/howDrawing.jspx">如何提款</a><br/>
</body>
