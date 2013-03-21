<%@ page pageEncoding="UTF-8"%>
<%@ page
	import="com.ruyicai.wap.util.*,java.util.*"%>
<title>排列三确认投注</title>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	List<String> list = CommonUtil.getBetCode("T01002", "5");
%>
<body>

	<%
		Random rdm = new Random();
		int transctionId = rdm.nextInt();
		request.getSession().setAttribute(transctionId + "", "false");
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = (String) request.getAttribute("addNumber");
		}
		//页面跳转参数
		Integer zhushu = Integer.parseInt(request.getAttribute("zhushu")
				.toString());
		String newzhuma = (String) request.getAttribute("newzhuma");
		Integer amount = Integer.parseInt(request.getAttribute("amount")
				.toString());
		String beishu = (String) request.getAttribute("beishu");
		String zhuma = (String) request.getAttribute("zhuma");
		String pageType = (String) request.getAttribute("pageType");
		String certErr = (String) request.getAttribute("certErr");

		request.getSession().setAttribute("newzhuma", newzhuma);
	%>
	<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx")%>"> <%
 	out.print(CommonUtil.printHall());
 %> </a>
	<%
		out.print(CommonUtil.printChar());
	%>
	<%
		if (pageType.trim().equals("autoSelection")) {
	%>
	排列3机选投注
	<%
		}
	%>
	<%
		if (pageType.trim().equals("group3HZ")) {
	%>
	<a
		href="<%=response.encodeURL(path
						+ "/wap/array3/Array3Group3HeZhi.jspx")%>">排列3组3和值投注</a>
	<%
		}
	%>
	<%
		if (pageType.trim().equals("group6HZ")) {
	%>
	<a
		href="<%=response.encodeURL(path
						+ "/wap/array3/Array3Group6HeZhi.jspx")%>">排列3组6和值投注</a>
	<%
		}
	%>
	<%
		if (pageType.trim().equals("group6BH")) {
	%>
	<a
		href="<%=response.encodeURL(path
						+ "/wap/array3/Array3Group6BH.jspx")%>">排列3组6包号投注</a>
	<%
		}
	%>
	<%
		if (pageType.trim().equals("group3BH")) {
	%>
	<a
		href="<%=response.encodeURL(path
						+ "/wap/array3/Array3Group3BH.jspx")%>">排列3组3包号投注</a>
	<%
		}
	%>
	<%
		if (pageType.trim().equals("ZXFS")) {
	%>
	<a
		href="<%=response.encodeURL(path
						+ "/wap/array3/Array3DirectSingle.jspx")%>">排列3直选投注</a>
	<%
		}
	%>
	<%
		if (pageType.trim().equals("group6")) {
	%>
	<a href="<%=response.encodeURL(path
						+ "/wap/array3/Array3Group.jspx")%>">排列三组6单式</a>
	<%
		}
	%>
	<%
		if (pageType.trim().equals("groupHZ")) {
	%>
	<a
		href="<%=response.encodeURL(path
						+ "/wap/array3/Array3GroupHeZhi.jspx")%>">排列3组选和值</a>
	<%
		}
	%>
	<%
		if (pageType.trim().equals("directSelectionHZ")) {
	%>
	<a
		href="<%=response.encodeURL(path
						+ "/wap/array3/Array3DirectSelectionHeZhi.jspx")%>">排列3直选和值</a>
	<%
		}
	%>
	<%
		out.print(CommonUtil.printChar());
	%>确认投注
	<br /> 您的投注:
	<br /> 排列三
	<br /> 金额:
	<%
		out.print(amount);
	%>元
	<br /> 注数:
	<%
		out.print(zhushu);
	%>注
	<br /> 倍数:
	<%
		out.print(beishu);
	%>倍
	<br />
	<%
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
	%>
	<%
		if (addNumber != null && !addNumber.trim().equals("")) {
	%>
	追号:
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
	投注号码:<%
		if (pageType.trim().equals("autoSelection")) {
	%>
	<%
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
	%>
	<form action="/wap/array3/Array3modifyAutoSelection.jspx" method="post"
		style="margin: 0px; display: inline;">
		<input type="hidden" name="zhushu" value="<%=zhushu%>" /> <input
			type="hidden" name="zhuma" value="<%=newzhuma%>" /> <input
			type="hidden" name="amount" value="<%=amount%>" /> <input
			type="hidden" name="beishu" value="<%=beishu%>" /> <input
			type="hidden" name="type" value="<%=pageType%>" />
		<%
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
		%>
		<input type="hidden" name="addNumber" value="<%=addNumber%>" />
		<%
			}
		%>
		<input type="submit" value="修改" />
		</form>
		<%
			}
			}
		%>
	<br />
	<%
		out.print(newzhuma);
	%>
		<%
			if (beishu.trim().equals("")) {
				beishu = "1";
			}
			if (addNumber.trim().equals("")) {
				addNumber = "0";
			}
		%><br />
		<form action="<%=response.encodeURL(path + "/array3/bet.jspa")%>"
			method="post">
			 <input
				type="hidden" name="zhushu" value="<%=zhushu%>" /> <input
				type="hidden" name="beishu" value="<%=beishu%>" /> <input
				type="hidden" name="amount" value="<%=amount%>" /> <input
				type="hidden" name="zhuma" value="<%=zhuma%>" />
			<%
				if (rbint.getString("array3addNumberSwitch").equals("1")) {
			%>
			<input type="hidden" name="addNumber" value="<%=addNumber%>" />
			<%
				}
			%>
			<br/>
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
		<input type="hidden" name="buyways" value="daigou" />
		<%} %>
			<input type="hidden" name="pageType" value="<%=pageType%>" /> <input
				type="hidden" name="token" value="<%=transctionId%>" /> <input
				type="submit" value="确认投注" />

		</form>
		<%
			if (pageType.trim().equals("autoSelection")) {
		%>
		<%
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
		%>
		<form action="/array3/autoSelectionDetail.jspx" method="post">
			 <input
				type="hidden" name="beishu" value="<%=beishu%>" /> <input
				type="hidden" name="addNumber" value="<%=addNumber%>" /> <input
				type="hidden" name="transctionId" value="<%=transctionId%>" /> <input
				type="hidden" name="zhushu" value="<%=zhushu%>" /> <input
				type="submit" value="重新选号" />
		</form>
		<%
			} else {
		%>
		<form action="/array3/autoSelectionDetail.jspx" method="post">
			 <input
				type="hidden" name="beishu" value="<%=beishu%>" /> <input
				type="hidden" name="transctionId" value="<%=transctionId%>" /> <input
				type="hidden" name="zhushu" value="<%=zhushu%>" /> <input
				type="submit" value="重新选号" />
		</form>
		<%
			}
		%>
		<%
			}
		%>

		<br /> 【最新开奖】<br />
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
		%><br />
	
</body>
