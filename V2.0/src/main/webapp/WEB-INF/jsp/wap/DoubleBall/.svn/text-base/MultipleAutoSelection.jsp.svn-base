<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>双色球复式机选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>"></a><%
	out.print(CommonUtil.printHall());
%><%
 	out.print(CommonUtil.printChar());
 %>双色球<br />
 <a href="/w/wap/DoubleBall/SingleSelfSelection.jspx">自选</a>|机选<br/>
<a href="/wap/DoubleBall/SingleAutoSelection.jspx">单式机选</a>|复式机选|<a href="/wap/DoubleBall/DantuoAutoSelection.jspx">胆拖机选</a>|<a href="/wap/DoubleBall/DantuoDingdanSelection.jspx">定胆机选</a><br/>	
 
红复式:可选7-20个红球和1个蓝球<br />
蓝复式:可选6个红球和2-16蓝球<br />
红蓝复式:可选7-20个红球和2-16个蓝球<br />

<%
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String) request.getAttribute("addNumber");
		if (addNumber == null)
			addNumber = "1";
	}
	String err_msg = (String) request.getAttribute("err_msg");
	String redballCount = (String) request.getAttribute("redballCount");
	if (redballCount == null)
		redballCount = "7";

	String blueballCount = (String) request
			.getAttribute("blueballCount");
	if (blueballCount == null)
		blueballCount = "1";

	String multNo = (String) request.getAttribute("multNo");
	if (multNo == null)
		multNo = "1";
	String deadline = CommonUtil.getDeadline("F47104", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %><br />
<%
	}
%><a style="color: red"> <%
 	if (err_msg != null) {
 		out.print(CommonUtil.printWarningMessage(err_msg) + "<br/>");
 	}
 %> </a>
<form action="<%=response.encodeURL(path
									+ "/doubleballMultipleSelfSelection/showAutoBetDetails.jspx"
									)%>" method="post" > 
 红球个数:<input name="redballCount" type="text" emptyok="false"
	maxlength="2" size="2" format="*N" value="<%=redballCount%>" />(如:7)<br />
蓝球个数:<input name="blueballCount" type="text" emptyok="false"
	maxlength="2" size="2" format="*N" value="<%=blueballCount%>" />(如:2)<br />
倍数:<input name="multNo" type="text" emptyok="false" maxlength="2"
	size="2" format="*N" value="<%=multNo%>" />(最多50倍)<br />
<%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%> 追号:<input name="addNumber" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=addNumber%>" />期<br />
(最高99期,默认追1期,即买当前期)<br />
<%
	}
%>
<input type="hidden" name="type" value="MA" />
<input type="submit" value="提交投注 " />
			</form><br />
<%
	List<String> list =CommonUtil.getBetCode("F47104","5");
%> 【最新开奖】<br />
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%><%out.print(code);%><br/>
<%
	}
%>
   <br/>
		   <a href="<%=response.encodeURL(path + "/wap/DoubleBall/DoubleBallIndex.jspx"
								)%>">返回上一页</a>
</body>
