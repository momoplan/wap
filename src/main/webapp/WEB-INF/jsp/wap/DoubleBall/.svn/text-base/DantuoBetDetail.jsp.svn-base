<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>双色球胆拖</title>
<body>

	<%
			Random rdm = new Random();
			int transctionId = rdm.nextInt();
			request.getSession().setAttribute(transctionId+"", "false");
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = ""; 
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
			}
			Integer amount = Integer.parseInt(request.getAttribute("amount").toString());
			Integer zhushu = Integer.parseInt(request.getAttribute("zhushu").toString());
			String beishu = (String)request.getAttribute("beishu").toString();
			String newRedballDanma = (String)request.getAttribute("newRedballDanma").toString();
			String newRedballTuoma = (String)request.getAttribute("newRedballTuoma").toString();
			String newBlueball = (String)request.getAttribute("newBlueball").toString();
			String zhuma = (String)request.getAttribute("zhuma");
			String term = (String)request.getAttribute("term");
			Integer blueballNumber = Integer.parseInt(request.getAttribute("blueballNumber").toString());
			String redballDanma = (String)request.getAttribute("redballDanma");
			String redballTuoma = (String)request.getAttribute("redballTuoma");
			String pageType = (String)request.getAttribute("pageType");
			String certErr = (String)request.getAttribute("certErr");
			
			request.getSession().setAttribute("zhushu",zhushu);
			request.getSession().setAttribute("newRedballDanma",newRedballDanma);
			request.getSession().setAttribute("newRedballTuoma",newRedballTuoma);
			request.getSession().setAttribute("newBlueball",newBlueball);
			request.getSession().setAttribute("term",term);
			request.getSession().setAttribute("redballDanma",redballDanma);
			request.getSession().setAttribute("redballTuoma",redballTuoma);
			request.getSession().setAttribute("pageType",pageType);
			List<String> list = CommonUtil.getBetCode("F47104","5");
		%>
	<a href="/wap/wapindex.jspx">首页</a>-
	<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">
		<%
			out.print(CommonUtil.printHall());
		%>
	</a>
	<%
			out.print(CommonUtil.printChar());
		%>
	<%
			if (pageType.trim().equals("autoSelection")) {
		%>
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoAutoSelection.jspx")%>">双色球胆拖机选</a>
	<%
			}
		%>
	<%
			if (pageType.trim().equals("selfSelection")) {
		%>
	<a
		href="<%=response.encodeURL(path+"/wap/DoubleBall/DantuoSelfSelection.jspx")%>">双色球胆拖自选</a>
	<%
			}
		%>
	<%
			out.print(CommonUtil.printChar());
		%>确认投注
	<br /> 您的投注：
	<br /> 双色球第<%out.print(term); %>期
	<br /> 金额：<%out.print(amount); %>元
	<br /> 注数：<%out.print(zhushu); %>注
	<br /> 倍数：<%out.print(beishu); %>倍
	<br />
	<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
	<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>
	追号：<% out.print(addNumber); %>期
	<br />
	<%} %>
	<%} %>
	红球胆码：
	<br />
	<%out.print(newRedballDanma); %><br /> 红球拖码：
	<br />
	<%out.print(newRedballTuoma); %><br /> 蓝球号码：
	<br />
	<%out.print(newBlueball); %><br />

	<%
				if (beishu.trim().equals("")) {
					beishu = "1";
				}
				if (addNumber.trim().equals("")) {
					addNumber = "0";
				}
			%>

	<form
		action="<%=response.encodeURL(path+"/doubleBallDantuo/dantuoBet.jspa")%>"
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
			type="hidden" name="beishu" value="<%=beishu %>" /> <input
			type="hidden" name="zhuma" value="<%=zhuma %>" /> <input
			type="hidden" name="amount" value="<%=amount %>" /> <input
			type="hidden" name="addNumber" value="<%=addNumber %>" /> <input
			type="hidden" name="blueballNumber" value="<%=blueballNumber %>" /> <input
			type="hidden" name="token" value="<%=transctionId %>" /> <input
			type="submit" value="确认投注" />
	</form>
	<%
			if (pageType.trim().equals("autoSelection")) {
			%>
	<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
	<form
		action="<%=response
										.encodeURL(path
												+"/doubleBallDantuo/showAutoBetDetails.jspx")%>"
		method="post">
		<input type="hidden" name="beishu" value="<%=beishu %>" /> <input
			type="hidden" name="pageType" value="<%=pageType %>" /> <input
			type="hidden" name="redballDanma" value="<%=redballDanma %>" /> <input
			type="hidden" name="redballTuoma" value="<%=redballTuoma %>" /> <input
			type="hidden" name="blueballCount" value="<%=blueballNumber %>" /> <input
			type="hidden" name="addNumber" value="<%=addNumber %>" /> <input
			type="hidden" name="transctionId" value="<%=transctionId %>" /> <input
			type="submit" value="重新选号" />
	</form>
	<%} else { %>
	<form
		action="<%=response
										.encodeURL(path
												+"/doubleBallDantuo/showAutoBetDetails.jspx")%>"
		method="post">
		<input type="hidden" name="beishu" value="<%=beishu %>" /> <input
			type="hidden" name="pageType" value="<%=pageType %>" /> <input
			type="hidden" name="redballDanma" value="<%=redballDanma %>" /> <input
			type="hidden" name="redballTuoma" value="<%=redballTuoma %>" /> <input
			type="hidden" name="blueballCount" value="<%=blueballNumber %>" /> <input
			type="hidden" name="transctionId" value="<%=transctionId %>" /> <input
			type="submit" value="重新选号" />
	</form>
	<%} %>
	<%} %>
	<br /> 【最新开奖】
	<br />
	<% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %>
	<%out.print(code);%><br />
	<%}%><br />
</body>