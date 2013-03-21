<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<%
	
%>
<title>七星彩单式机选</title>
<body>
<<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%></a><%
	out.print(CommonUtil.printChar());%>七星彩<br />
<a href="/w/wap/sevenStar/sevenStarByHand.jspx">自选</a>|机选|<a 
		   href="/w/wap/sevenStar/ddSevenStar.jspx">定胆</a><br/>
单式机选|<a href="/wap/sevenStar/MulipleAutoSelection.jspx">复式机选</a><br/>

<%
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String) request.getAttribute("addNumber");
		if (addNumber == null)
			addNumber = "1";
	}
	String err_msg = (String) request.getAttribute("err_msg");
	String zhushuStr = (String) request.getAttribute("zhushuStr");
	if (zhushuStr == null)
		zhushuStr = "5";//默认5注

	String multNo = (String) request.getAttribute("beishu");
	if (multNo == null)
		multNo = "1";
	String deadline = CommonUtil.getDeadline("T01009", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %><br />
<%
	}
%> <a style="color: red"><%
 	if (err_msg != null) {
 		out.print(CommonUtil.printWarningMessage(err_msg) + "<br/>");
 	}
 %></a> 
<form action="/sevenStar/showAutoBetDetails.jspx" method="post"> 
 机选注数:<input name="zhushu" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=zhushuStr%>" /><br />
倍数:<input name="beishu" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=multNo%>" />(最多99倍)<br />
<%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%> 追号:<input name="addNumber" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=addNumber%>" />期<br />
(最高99期,默认追1期,即买当前期)<br />
<%
	}
%> 
<input type="submit" value="提交投注" />
</form><br/>
<%
	List<String> list = CommonUtil.getBetCode("T01009","5"); 
%> 【最新开奖】<br />
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%> <%
 	out.print(code);
 %> <br />
<%
	}
%> 
   <br/>
		   <a href="<%=response.encodeURL(path + "/wap/sevenStar/7StarIndex.jspx"
								)%>">返回上一页</a>
</body>