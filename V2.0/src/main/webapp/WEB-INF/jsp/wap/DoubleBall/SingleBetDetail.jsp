<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>双色球投注详细</title>


<body>
	<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	request.getSession().setAttribute(transctionId + "", "false");
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = request.getAttribute("addNumber")==null?"1":(String)request.getAttribute("addNumber");
	//页面跳转参数
	Integer zhushu = Integer.parseInt(request.getAttribute("zhushu").toString());
	String newzhuma = (String) request.getAttribute("newzhuma");
	Integer amount = Integer.parseInt(request.getAttribute("amount").toString());
	String beishu = (String) request.getAttribute("beishu");
	String bet_No = (String) request.getAttribute("bet_No");
	String term = (String) request.getAttribute("term");
	String type = (String) request.getAttribute("type");
	String certErr = (String) request.getAttribute("certErr");
	request.getSession().setAttribute("newzhuma", newzhuma);
	request.getSession().setAttribute("term", term);
	List<String> list = CommonUtil.getBetCode("F47104","5");
	if (beishu.trim().equals("")) {
		beishu = "1";
	}
%>
	<a href="/wap/wapindex.jspx">首页</a>-
	<a href="/wap/buyLottery.jspx"> <%out.print(CommonUtil.printHall());%>
	</a>
	<%	out.print(CommonUtil.printChar()); %>
	<%if (type.trim().equals("SA")) { %>
	<a href="/wap/DoubleBall/SingleAutoSelection.jspx">双色球单式机选</a>
	<%}%>
	<%
 	if (type.trim().equals("SS")) {
 %>
	<a href="/wap/DoubleBall/SingleSelfSelection.jspx">双色球单式自选</a>
	<%}%>
	<%out.print(CommonUtil.printChar());%>确认投注
	<br /> 您的投注:
	<br /> 双色球第<%out.print(term);%>期
	<br /> 金额:
	<%	out.print(amount);%>元
	<br /> 注数:
	<%	out.print(zhushu);%>注
	<br /> 倍数:
	<%	out.print(beishu);%>倍
	<br />
	追号:
	<% out.print(addNumber); %>期
	<br />
	<%Integer zhushu1 = zhushu / Integer.parseInt(beishu); %>
	投注号码:
	<%
 	if ("SA".equals(type) && zhushu <= 10) {
 %>
	<form action="/wap/DoubleBall/ModifySingleAutoSelection.jspx" method="post">
		<input type="hidden" name="zhushu" value="<%=zhushu1%>" /> <input
			type="hidden" name="beishu" value="<%=beishu%>" /> <input
			type="hidden" name="term" value="<%=term%>" /> <input type="hidden"
			name="amount" value="<%=amount%>" /> <input type="hidden"
			name="zhushu" value="<%=zhushu%>" /> <input type="hidden"
			name="zhuma" value="<%=bet_No%>" /> <input type="hidden"
			name="newzhuma" value="<%=newzhuma.replaceAll("<br/>", "~")%>" /> <input
			type="hidden" name="addNumber" value="<%=addNumber%>" /> <input
			type="hidden" name="token" value="<%=transctionId%>" /> <input
			type="submit" value="修改" />
	</form>
	<%}%>
	<br />
	<%out.print(newzhuma); %>
	<br />
	<form action="/doubleballSingleSelfSelection/placeBet.jspa"
		method="post">
		<%if("1".equals(addNumber)){ %>
		<p style="color: red;">
			<input type="radio" checked="checked" value="daigou" name="buyways">普通投注
		</p>
		<p style="color: red;">
			<input type="radio" value="presented" name="buyways">我要赠送给他人
		</p>
		<p style="color: red;">
			<input type="radio" value="hemai" name="buyways">发起合买
		</p>
		<%}else{ %>
		<p style="color: red;">您已经选择了追期，无法应用赠彩、合买功能</p>
		<%} %>
		<input type="hidden" name="zhushu" value="<%=zhushu%>" /> <input
			type="hidden" name="beishu" value="<%=beishu%>" /> <input
			type="hidden" name="amount" value="<%=amount%>" /> <input
			type="hidden" name="bet_No" value="<%=bet_No%>" /> <input
			type="hidden" name="addNumber" value="<%=addNumber%>" /> <input
			type="hidden" name="type" value="<%=type%>" /> <input type="hidden"
			name="token" value="<%=transctionId%>" /> <input type="submit"
			value="确认投注 " />
	</form>
	<%
	if("SA".equals(type)){%>
		<form
		action="<%=request.getContextPath() %>/doubleballSingleSelfSelection/showAutoBetDetails.jspx"
		method="post">
		<input type="hidden" name="multNo" value="<%=beishu %>" /> <input
			type="hidden" name="type" value="SA" /> <input type="hidden"
			name="zhushu" value="<%=zhushu %>" /> <input type="hidden"
			name="addNumber" value="<%=addNumber %>" /> <input type="hidden"
			name="transctionId" value="<%=transctionId %>" /> <input
			type="submit" value="重新选号" />
	</form>
	<%}
	%>
	
	<br /> 【最新开奖】
	<br />
	<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%>
	<%out.print(code);%><br />
	<%
	}
%><br />
</body>