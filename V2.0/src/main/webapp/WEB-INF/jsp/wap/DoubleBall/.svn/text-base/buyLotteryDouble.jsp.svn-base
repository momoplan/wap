<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取七乐彩开关
	String qilecaiDefine = rbint.getString("qilecaiDefine");		//取得七乐彩开关
%>
<title>购彩大厅</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-购彩大厅<br/>
			两元博取大奖.彩票改变人生<br/>
			【双色球】<br/>
			<a href="<%=response.encodeURL(path+"/wap/DoubleBall/SingleAutoSelection.jspx")%>">单式机选</a> 　<a href="<%=response.encodeURL(path+"/wap/DoubleBall/SingleSelfSelection.jspx")%>">单式自选</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/DoubleBall/MultipleAutoSelection.jspx")%>">复式机选</a>　 <a href="<%=response.encodeURL(path+"/wap/DoubleBall/MultipleSelfSelection.jspx")%>">复式自选</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoAutoSelection.jspx")%>">胆拖机选</a>  　<a href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoSelfSelection.jspx")%>">胆拖自选</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoDingdanSelection.jspx")%>">定胆机选</a><br/>
			
			<a href="<%=response.encodeURL(path+"/wap/help/buySafe.jspx")%>">购彩安全</a>　<a href="<%=response.encodeURL(path+"/wap/playIntroduced/introduce.jspx")%>">玩法介绍</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/help/howCharge.jspx")%>">如何充值</a>　<a href="<%=response.encodeURL(path+"/wap/help/howBet.jspx")%>">如何投注</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/help/howReward.jspx")%>">如何兑奖</a>　<a href="<%=response.encodeURL(path+"/wap/help/howDrawing.jspx")%>">如何提款</a><br/>

</body>