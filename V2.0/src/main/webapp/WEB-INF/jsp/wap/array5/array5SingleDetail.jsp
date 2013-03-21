<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>排列五确认投注</title>
<body>

<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	request.getSession().setAttribute(transctionId + "", "false");
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	//页面跳转参数
	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String) request.getAttribute("addNumber");
	}
	String autoMethod = (String) request.getAttribute("autoMethod");
	int zhushu = Integer.parseInt(request.getAttribute("zhushu")
			.toString());
	String newzhuma = (String) request.getAttribute("newzhuma");
	int amount = Integer.parseInt(request.getAttribute("amount")
			.toString());
	String beishu = (String) request.getAttribute("beishu");
	int zhushuAll = zhushu * Integer.parseInt(beishu);
	String zhuma = (String) request.getAttribute("zhuma");
	String term = (String) request.getAttribute("term");
	String type = (String) request.getAttribute("type");

	String err_msg = null;
	if (request.getAttribute("err_msg") != null) {
		err_msg = (String) request.getAttribute("err_msg");
	}
%>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %> <%
 	if (type.trim().equals("DSJX")) {
 %> <a
	href="<%=response.encodeURL(path
								+ "/wap/array5/singleAutoSelection.jspx"
								)%>">排列五单式机选</a>
<%
	}
%> <%
 	out.print(CommonUtil.printChar());
 %>确认投注<br />
<%
	if (err_msg != null)
		out.println(err_msg);
%> 您的投注:<br />
排列五第<%
	out.print(term);
%>期<br />
金额: <%
	out.print(amount);
%>元<br />
注数: <%
	out.print(zhushuAll);
%>注<br />
倍数: <%
	out.print(beishu);
%>倍<br />
<%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%> <%
 	if (addNumber != null && !addNumber.trim().equals("")) {
 %> 追号: <%
 	out.print(addNumber);
 %>期<br />
<%
	}
%> <%
 	}
 %> 投注号码: <%
 	String s[] = newzhuma.split("<br/>");
 %> <%
 	if (type.trim().equals("DSJX")) {
 		if (s.length <= 10) {
 %><form action="/wap/array5/modifyAutoSelection.jspx" method="post" style="margin:0px;display:inline;">
 <input type="hidden" name="zhushu" value="<%=zhushu%>" />
 <input type="hidden" name="zhuma" value="<%=zhuma%>" />
 <input type="hidden" name="amount" value="<%=amount%>" />
 <input type="hidden" name="beishu" value="<%=beishu%>" />
 <input type="hidden" name="type" value="<%=type%>" />
 <input type="hidden" name="autoMethod" value="<%=autoMethod%>" />
 <%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%>
 <input type="hidden" name="addNumber" value="<%=addNumber%>" />
 <%
	}
%>
<input type="submit" value="修改" />
 </form><br />
<%
	out.print(newzhuma);
%> <%
 	} else {
 %> 
 <br />
<%
	out.print(newzhuma);
		}
%>
<%
	} else {

	}
%> <%
 	if (beishu.trim().equals("")) {
 		beishu = "1";
 	}
 	if (addNumber.trim().equals("")) {
 		addNumber = "0";
 	}
 %> <br/>
 <form action="/array5/bet.jspa" method="post" style="margin:0px;display:inline;">
 
 <input type="hidden" name="zhushu" value="<%=zhushuAll%>" />
 <input type="hidden" name="beishu" value="<%=beishu%>" />
 <input type="hidden" name="amount" value="<%=amount%>" />
 <input type="hidden" name="zhuma" value="<%=zhuma%>" />
 <input type="hidden" name="term" value="<%=term%>" />
 <input type="hidden" name="type" value="<%=type%>" />
 <%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%>
 <input type="hidden" name="addNumber" value="<%=addNumber%>" />
<%
	}
%>
 <input type="hidden" name="token" value="<%=transctionId%>" />
 <%if("1".equals(addNumber)){ %>
		<p style="color: red;">
			<input type="radio" checked="checked" value="daigou" name="buyways">普

通投注
		</p>
		<p style="color: red;">
			<input type="radio" value="presented" name="buyways">我要赠送给他人
		</p>
		<p style="color: red;">
			<input type="radio" value="hemai" name="buyways">发起合买
		</p>
		<%}else{ %>
		<p style="color: red;">您已经选择了追期，无法应用赠彩、合买功能</p>
		<input type="hidden" name="buyways" value="daigou" />
		<%} %>
 
 <input type="submit" value="确认投注" />
 </form>
 <%
 	if (type.trim().equals("DSJX")) {
 		String act = "/array5/"+autoMethod+".jspx";
 %> <%
 	if (rbint.getString("addNumberSwitch").equals("1")) {
 		
 %>
 <form action="<%=act %>" method="post" style="margin:0px;display:inline;">
 <input type="submit" value="重新选号" />
 <input type="hidden" name="beishu" value="<%=beishu%>" />
 <input type="hidden" name="zhushu" value="<%=zhushu%>" />
 <input type="hidden" name="addNumber" value="<%=addNumber%>" />
 <input type="hidden" name="type" value="<%=type%>" />
 </form>
<%
	} else {
%> 
<form action="<%=act %>" method="post" style="margin:0px;display:inline;">
 <input type="submit" value="重新选号" />
 <input type="hidden" name="beishu" value="<%=beishu%>" />
 <input type="hidden" name="zhushu" value="<%=zhushu%>" />
 <input type="hidden" name="type" value="<%=type%>" />
 </form>
<%
	}
%> <%
 	} else {
 %> <%
 	}
 %> <br/>
<%
List<String> list = CommonUtil.getBetCode("T01011","5");
%> 【最新开奖】<br/>
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%> <%
 	out.print(code);
 %> <br />
<%
	}
%> <br />
</body>
