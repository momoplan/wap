<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>
江西11选5胆拖
</title>
<body>
<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	request.getSession().setAttribute(transctionId + "", "false");
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String) request.getAttribute("addNumber");
	}
	Integer amount = Integer.parseInt(request.getAttribute("amount")
			.toString());
	Integer zhushu = Integer.parseInt(request.getAttribute("zhushu")
			.toString());
	String beishu = (String) request.getAttribute("beishu").toString();
	String zhuma = (String) request.getAttribute("zhuma");
	String term = (String) request.getAttribute("term");
	String newzhuma = (String) request.getAttribute("newzhuma");
	String danma = (String) request.getAttribute("danma");
	String tuoma = (String) request.getAttribute("tuoma");
	String type = (String) request.getAttribute("type");
	String certErr = (String) request.getAttribute("certErr");

	request.getSession().setAttribute("zhushu", zhushu);
	request.getSession().setAttribute("term", term);
	request.getSession().setAttribute("type", type);
	List<String> list = CommonUtil.getBetCode("T01010", "5");
	String name = null;
	if ("2".equals(type)) {
		name = "任二";
	} else if ("3".equals(type)) {
		name = "任三";
	} else if ("4".equals(type)) {
		name = "任四";
	} else if ("5".equals(type)) {
		name = "任五";
	} else if ("6".equals(type)) {
		name = "任六";
	} else if ("7".equals(type)) {
		name = "任七";
	} else if ("8".equals(type)) {
		name = "任八";
	} else if ("9".equals(type)) {
		name = "前二组选";
	} else if ("10".equals(type)) {
		name = "前三组选";
	}
%>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a>
<%
	out.print(CommonUtil.printChar());
%>
<a
	href="<%=response.encodeURL(path
							+ "/wap/11select5/dantuoselection.jspx" )%>">胆拖<%=name%></a>

<%
	out.print(CommonUtil.printChar());
%>确认投注
<br />

您的投注：
<br/>
<%=name%>第<%
	out.print(term);
%>期
<br />
金额：<%
	out.print(amount);
%>元
<br />
注数：<%
	out.print(zhushu);
%>注
<br />
倍数：<%
	out.print(beishu);
%>倍
<br />
<%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%>
<%
	if (addNumber != null && !addNumber.trim().equals("")) {
%>
追号：
<%
	out.print(addNumber);
%>期
<br />
<%
	}
%>
<%
	}
%>
<%
	out.print("投注注码：<br/>" +"胆码："+danma+"<br/>"+"拖码："+tuoma);
%><br />

<%
	if (beishu.trim().equals("")) {
		beishu = "1";
	}
	if (addNumber.trim().equals("")) {
		addNumber = "0";
	}
%>
<br />
<form
	action="<%=response.encodeURL(path + "/11select5/dantuoBet.jspa"
							)%>"
	method="post">
<input type="hidden" name="beishu" value="<%=beishu%>" />
<input type="hidden" name="zhuma" value="<%=zhuma%>" />
<input type="hidden" name="amount" value="<%=amount%>" />
<%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%>
<input type="hidden" name="addNumber" value="<%=addNumber%>" />
<%
	}
%>
<input type="hidden" name="certID"
	value="<%=request.getParameter("certID:noesc")%>" />
<input type="hidden" name="token" value="<%=transctionId%>" />
<input type="submit" value="确认投注" />
</form>
<br />
【最新开奖】
<br />
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%>
<%
	out.print(code);
%>
<br />
<%
	}
%></body>
