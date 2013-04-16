<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>江西11选5</title>
<body> 
<%
 	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
 	String addNumber = "";
 	if (rbint.getString("addNumberSwitch").equals("1")) {
 		addNumber = (String) request.getAttribute("addNumber");
 		if (addNumber == null)
 			addNumber = "1";
 	}

 	String message = (String) request.getAttribute("message");
 	String danma = (String) request.getAttribute("danma");
 	String tuoma = (String) request.getAttribute("tuoma");
 	String type = (request.getParameter("type") == null || ""
 			.equals(request.getParameter("type"))) ? (String) request
 			.getAttribute("type") : request.getParameter("type");
 	String name = null;
 	String num = null;
 	int value = 0;
 	if ("2".equals(type)) {
 		name = "任二";
 		num = "1";
 		value = 2;
 	} else if ("3".equals(type)) {
 		name = "任三";
 		num = "1~2";
 		value = 3;
 	} else if ("4".equals(type)) {
 		name = "任四";
 		num = "1~3";
 		value = 4;
 	} else if ("5".equals(type)) {
 		name = "任五";
 		num = "1~4";
 		value = 5;
 	} else if ("6".equals(type)) {
 		name = "任六";
 		num = "1~5";
 		value = 6;
 	} else if ("7".equals(type)) {
 		name = "任七";
 		num = "1~6";
 		value = 7;
 	} else if ("8".equals(type)) {
 		name = "任八";
 		num = "1~7";
 		value = 8;
 	} else if ("9".equals(type)) {
 		name = "前二组选";
 		num = "1";
 		value = 2;
 	} else if ("10".equals(type)) {
 		name = "前三组选";
 		num = "1~2";
 		value = 3;
 	}
 	if (danma == null)
 		danma = "";
 	if (tuoma == null)
 		tuoma = "";
 	String beishu = (String) request.getAttribute("beishu");
 	if (beishu == null)
 		beishu = "1";
 	String deadline = CommonUtil.getDeadline("T01010", 0);
 	if (deadline == null)
 		deadline = "";
 %><a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>江西11选5<br />
 <a href="/w/wap/11select5/optionOne.jspx">任选</a>|<a 
		   href="/w/wap/11select5/ForwardTwoZX.jspx">直选</a>|<a 
		   href="/w/wap/11select5/ForwardTwoGroup.jspx">组选</a>|胆拖<br/>
		   <%if("2".equals(type)){%>
			任二|   
		   <%} else{%>
		   <a href="/wap/11select5/dantuoselection.jspx?type=2">任二</a>|
		   <%} %>
		   <%if("3".equals(type)){%>
			 任三|   
		   <%} else{%>
		     <a href="/wap/11select5/dantuoselection.jspx?type=3">任三</a>|
		   <%} %>
		   <%if("4".equals(type)){%>
			   任四|
		   <%} else{%>
		   		<a href="/wap/11select5/dantuoselection.jspx?type=4">任四</a>|
		   <%} %>
		   <%if("5".equals(type)){%>
			   任五|
		   <%} else{%>
		   		<a href="/wap/11select5/dantuoselection.jspx?type=5">任五</a>|
		   <%} %>
		   <%if("6".equals(type)){%>
			   任六|
		   <%} else{%>
		   		<a href="/wap/11select5/dantuoselection.jspx?type=6">任六</a>|
		   <%} %>
		   <%if("7".equals(type)){%>
			   任七|
		   <%} else{%>
		   		<a href="/wap/11select5/dantuoselection.jspx?type=7">任七</a>|
		   <%} %>
		   <%if("8".equals(type)){%>
			   任八|
		   <%} else{%>
		   		<a href="/wap/11select5/dantuoselection.jspx?type=8">任八</a>|
		   <%} %>
		   <%if("9".equals(type)){%>
			   前二组选|
		   <%} else{%>
		   		<a href="/wap/11select5/dantuoselection.jspx?type=9">前二组选</a>|
		   <%} %>
		    <%if("10".equals(type)){%>
			   前三组选
		   <%} else{%>
		   		<a href="/wap/11select5/dantuoselection.jspx?type=10">前三组选</a>
		   <%} %>
<br/>
  <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %> <form action="/wap/11select5/dantuoselection.jspx"
	method="post"> <input type="hidden" name="type" value="<%=type%>" />
<input type="submit" value="刷新" /> </form><br />
(号码间无分隔,数字不可重复,小于10则补0,如选择2则填写02)<BR /> (胆码限选<%=num%>个,胆码和拖码个数之和大于<%=value%>但小于12并且不能重复)<br />
<form
	action="<%=response.encodeURL(path + "/11select5/dantuoBetDetail.jspx" )%>"
	method="post"> <a style="color: red"><%
 	if (message != null) {
 		out.print(CommonUtil.printWarningMessage(message) + "<br/>");
 	}
 %> </a>胆码： <input name="danma" type="text" emptyok="false" 
	size="20" format="*N" value="<%=danma%>" /><br /> 拖码： <input
	name="tuoma" type="text" emptyok="false"  size="20"
	format="*N" value="<%=tuoma%>" /><br /> 倍数：<input name="beishu"
	type="text" emptyok="false" maxlength="2" size="2" format="*N"
	value="<%=beishu%>" />(最多99倍)<br /> <%
 	if (rbint.getString("addNumberSwitch").equals("1")) {
 %> 追号：<input name="addNumber" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=addNumber%>" />期<br />
(最高99期,默认追1期,即买当前期)<br /> <%
 	}
 %>  <input
	type="hidden" name="type" value="<%=type%>" /> <input type="submit"
	value="提交投注" /> </form> <br /> <%
 	List<String> list = CommonUtil.getBetCode("T01010","5");
 %> 【最新开奖】<br /> <%
 	String code = "";
 	for (int i = 0; i < list.size(); i++) {
 		code = list.get(i);
 %> <%
 	out.print(code);
 %> <br /> <%
 	}
 %> </body>
