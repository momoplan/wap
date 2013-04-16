<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>

<title>双色球单式机选</title>
<body>

<%
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = "";

	String err_msg = (String) request.getAttribute("err_msg");
	String multNo = "";
	String betNo = "";
	String token = "";
	String term = "";
	String regex = "([1-9][0-9])";
	//	int amount = 0;
	String zhushu = "";
	String newzhuma = request.getParameter("newzhuma");
	if (newzhuma == null || "".equals(newzhuma)) {
		newzhuma = (String) request.getAttribute("newzhuma");
		betNo = (String) request.getAttribute("zhuma");
		token = (String) request.getAttribute("token");
		multNo = (String) request.getAttribute("beishu");
		term = (String) request.getAttribute("term");
		//	amount = Integer.parseInt(request.getAttribute("amount").toString());
		zhushu = (String) request.getAttribute("zhushu");
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = (String) request.getAttribute("addNumber");
			if (addNumber == null)
				addNumber = "1";
		}
	} else {
		newzhuma = newzhuma.replaceAll("~", "<br/>");
		betNo = request.getParameter("zhuma");
		token = request.getParameter("token");
		multNo = request.getParameter("beishu");
		term = request.getParameter("term");
		//	amount = Integer.parseInt(request.getParameter("amount"));
		zhushu = request.getParameter("zhushu");
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null)
				addNumber = "1";
		}
	}
	String[] zhumazu = null;
	if (newzhuma != null || "".equals("newzhuma")) {
		zhumazu = newzhuma.split("<br/>");
	}

	if (multNo == null)
		multNo = "1";
	String deadline = CommonUtil.getDeadline("F47104", 0);
	if (deadline == null)
		deadline = "";
%>
<a href="/wap/wapindex.jsp">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a>
<%
 	out.print(CommonUtil.printChar());
 %><a
	href="<%=response.encodeURL(path
							+ "/wap/DoubleBall/SingleAutoSelection.jspx"
							)%>">双色球单式机选</a>
<%
	out.print(CommonUtil.printChar());
%>注码修改
<br />
<%
	if (err_msg != null)
		out.print(CommonUtil.printWarningMessage(err_msg) + "<br/>");
%>
您的投注:
<br />
双色球第<%
	out.print(term);
%>期
<br />
<form
	action="<%=response.encodeURL(path
							+ "/doubleballSingleSelfSelection/modifyAutoBetDetails.jspx" )%>"
	method="post">倍数:<input name="beishu" type="text"
	emptyok="false" maxlength="2" size="2" format="*N" value="<%=multNo%>" />(最多50倍)<br />
<%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%> 追号:<input name="addNumber" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=addNumber%>" />期<br />
<%
	}
%> 投注号码：<br />
<%
	int size = zhumazu.length;
	for (int i = 0; i < size; i++) {
%> <input name="zhuma<%=i%>" type="text" emptyok="false" maxlength="20"
	size="20" format="*N" value="<%=zhumazu[i]%>" /><br />
<%
	}
%> 			
	<input type="hidden" name="zhushu" value="<%=size%>" />
<input type="hidden" name="betNo" value="<%=betNo%>" />
<input type="hidden" name="term" value="<%=term%>" />
	 <input type="hidden" name="token" value="<%=token%>" /> 
 <input type="hidden" name="type" value="SA" /> <input type="submit"
	value="确认修改" /></form>
<%
 	if (rbint.getString("addNumberSwitch").equals("1")) {
 		if (!multNo.matches(regex)) {
 			multNo = "1";
 		}
 		if (!addNumber.matches(regex)) {
 			addNumber = "1";
 		}
 %>

<form
	action="<%=response
										.encodeURL(path
												+ "/doubleballSingleSelfSelection/showAutoBetDetails.jspx")%>"
	method="post">
	<input type="hidden" name="multNo" value="<%=multNo %>" />
	<input type="hidden" name="type" value="SA" />
	<input type="hidden" name="zhushu" value="<%=zhushu %>" />
	<input type="hidden" name="addNumber" value="<%=addNumber %>" />
	<input type="hidden" name="transctionId" value="<%=token %>" />
	<input type="submit" value="重新选号" /></form>
<%	} else {
		if (!multNo.matches(regex)) {
			multNo = "1";
 		}
%>
<form
	action="<%=response
										.encodeURL(path
												+ "/doubleballSingleAutoSelection/showAutoBetDetails.jspx")%>"
	method="post">
	<input type="hidden" name="multNo" value="<%=multNo %>" />
	<input type="hidden" name="type" value="SA" />
	<input type="hidden" name="zhushu" value="<%=zhushu %>" />
	<input type="hidden" name="transctionId" value="<%=token %>" />
	<input type="submit" value="重新选号" /></form>
<%
	}
%>
<br />
<%
	List<String> list = CommonUtil.getBetCode("F47104","5");
%>
【最新开奖】
<br/>
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%>
<%out.print(code);%><br/>
<%
	}
%>
</body>