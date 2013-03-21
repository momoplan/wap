<%@ page pageEncoding="UTF-8"%>
<%@ page
	import="com.ruyicai.wap.util.*,java.util.*,java.math.BigDecimal,net.sf.json.JSONObject,net.sf.json.JSONArray"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String type = "F47104";	
	JSONObject re = CommonUtil.getAutoZhuma(type);
	String newzhuma = re.getString("newzhuma");
    String betNO = re.getString("betNoStr");
	String deadline = CommonUtil.getDeadlineForHM("F47104", 0);
    if (deadline==null) deadline = "";
%>
<%@page import="com.ruyicai.wap.controller.NewsActionWap"%>
<%@page import="com.ruyicai.wap.controller.OrderAction"%>
<title>双色球首页</title>
<body>
	<a href="/wap/wapindex.jspx">首页</a>-
	<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"> <%out.print(CommonUtil.printHall());
		%></a>
	<%out.print(CommonUtil.printChar());
			out.print(CommonUtil.printCZ());
		%><br /> 最受欢迎，销量最高，2元赢取1000万。
	<br /> 今日推荐号码：
	<%=newzhuma%><br />
	<form action="/doubleballSingleSelfSelection/AutoBetDetails.jspx"
		method="post">
		<input type="hidden" name="type" value="SA" /> <input type="hidden"
			name="betNo" value="<%=betNO%>" /> <input type="hidden"
			name="newzhuma" value="<%=newzhuma%>" /> <input type="submit"
			value="确认投注" />
	</form>
	<form action="/wap/DoubleBall/DoubleBallIndex.jspx" method="post">
		<input type="submit" value="重新选号" />
	</form>
	<br />
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/SingleAutoSelection.jspx")%>">单式机选</a>
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/SingleSelfSelection.jspx")%>">单式自选</a>
	<br />
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/MultipleAutoSelection.jspx")%>">复式机选</a>
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/MultipleSelfSelection.jspx")%>">复式自选</a>
	<br />
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoAutoSelection.jspx")%>">胆拖机选</a>
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoSelfSelection.jspx")%>">胆拖自选</a>
	<br />
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoDingdanSelection.jspx")%>">定胆机选</a>
	<br />
	<a
		href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
</body>