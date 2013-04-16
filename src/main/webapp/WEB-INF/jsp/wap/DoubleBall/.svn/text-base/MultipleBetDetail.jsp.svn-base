<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	List<String> list = CommonUtil.getBetCode("F47104","5");
%>
<title>双色球复式投注确认</title>
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
	//页面跳转参数
	String redballCount = (String) request.getAttribute("redballCount");
	String blueballCount = (String) request
			.getAttribute("blueballCount");
	if (redballCount == null || "".equals(redballCount)) {
		redballCount = request.getParameter("redballCount");
		blueballCount = request.getParameter("blueballCount");
	}
	Integer zhushu = Integer.parseInt(request.getAttribute("zhushu").toString());
	String newzhuma = (String) request.getAttribute("newzhuma");
	Integer amount = Integer.parseInt(request.getAttribute("amount")
			.toString());
	String beishu = (String) request.getAttribute("beishu");
	String bet_No = (String) request.getAttribute("bet_No");
	String term = (String) request.getAttribute("term");
	String betType = (String) request.getAttribute("betType");
	String type = (String) request.getAttribute("type");
	String certErr = (String) request.getAttribute("certErr");
	if (betType == null) {
		betType = "S";
	}
	request.getSession().setAttribute("newzhuma", newzhuma);
	request.getSession().setAttribute("term", term);
	request.getSession().setAttribute("blueballCount", blueballCount);
	request.getSession().setAttribute("redballCount", redballCount);
	request.getSession().setAttribute("type", type);
%>
	<a href="/wap/wapindex.jspx">首页</a>-
	<a href="<%=request.getContextPath() %>/wap/buyLottery.jspx"> <%out.print(CommonUtil.printHall());%>
	</a>
	<% 	out.print(CommonUtil.printChar()); %>
	<% 	if (type.trim().equals("MA")) { %>
	<a
		href="<%=request.getContextPath() %>/wap/DoubleBall/MultipleAutoSelection.jspx">双色球复式机选</a>
	<%}%>
	<%	if (type.trim().equals("MS")) { %>
	<a
		href="<%=request.getContextPath() %>/wap/DoubleBall/MultipleSelfSelection.jspx">双色球复式自选</a>
	<%}%>
	<%out.print(CommonUtil.printChar());%>确认投注
	<br /> 您的投注:
	<br /> 双色球第<%out.print(term);%>期
	<br /> 金额:
	<%out.print(amount);%>元
	<br /> 注数:<%out.print(zhushu);%>注
	<br /> 倍数:
	<%out.print(beishu);%>倍
	<br /> 追号:
	<%out.print(addNumber);%>期
	<br /> 类型:<%out.print(LotteryAlgorithmUtil.getDoubleBallBetTypeString(betType.charAt(0))); %>
	<br /> 投注号码:
	<%out.print(newzhuma);%><br />
	<form	action="<%=request.getContextPath() %>/doubleballMultipleSelfSelection/placeBet.jspa"
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
		<input type="hidden" name="buyways" value="daigou" />
		<p style="color: red;">您已经选择了追期，无法应用赠彩、合买功能</p>
		<%} %>
		<input type="hidden" name="zhushu" value="<%=zhushu%>" /> <input
			type="hidden" name="beishu" value="<%=beishu%>" /> <input
			type="hidden" name="amount" value="<%=amount%>" /> <input
			type="hidden" name="zhuma" value="<%=newzhuma%>" /> <input
			type="hidden" name="bet_No" value="<%=bet_No%>" /> <input
			type="hidden" name="addNumber" value="<%=addNumber%>" /> <input
			type="hidden" name="betType" value="<%=betType%>" /> <input
			type="hidden" name="token" value="<%=transctionId%>" /> <input
			type="submit" value="确认投注" />
	</form>
	<%	if (type.trim().equals("MA")) { %>
	<form action="/doubleballMultipleSelfSelection/showAutoBetDetails.jspx"
		method="post">
		<input type="hidden" name="multNo" value="<%=beishu %>" /> <input
			type="hidden" name="type" value="MA" /> <input type="hidden"
			name="redballCount" value="<%=redballCount %>" /> <input
			type="hidden" name="blueballCount" value="<%=blueballCount %>" /> <input
			type="hidden" name="addNumber" value="<%=addNumber %>" /> <input
			type="hidden" name="transctionId" value="<%=transctionId %>" /> <input
			type="submit" value="重新选号" />
	</form>
	<%}%>
	
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