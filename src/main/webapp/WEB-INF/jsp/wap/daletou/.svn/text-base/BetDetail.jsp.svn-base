<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	Logger logger = Logger.getLogger("BetDetail");
	List<String> list = CommonUtil.getBetCode("T01001","5");
%>
<title>大乐透投注确认</title>
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
	logger.info("zhuma:" + zhuma);
	String type = (String) request.getAttribute("type");
	String zhushuStr = (String) request.getAttribute("zhushuStr");
	String certErr = (String) request.getAttribute("certErr");
	String oneMoney = request.getAttribute("oneMoney") == null ? "2"
			: (String) request.getAttribute("oneMoney");
	request.removeAttribute("certErr");
	request.getSession().setAttribute("zhushuStr", zhushuStr);
	request.getSession().setAttribute("newzhuma", newzhuma);
	String redSize = "", blueSize = "";
	if (type != null && type.equals("JXFS")) {
		redSize = (String) request.getAttribute("redSize");
		blueSize = (String) request.getAttribute("blueSize");

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
 	if (type == "DSJX" || type.equals("DSJX")) {
 %> <a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/SingleAutoSelection.jspx"
								)%>">大乐透单式机选</a>
<%
	}
	if (type == "FSZX" || type.equals("FSZX")) {
%> <a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/SingleSelfSelection.jspx"
								)%>">大乐透复式自选投注</a>
<%
	}
	if (type == "JXDD" || type.equals("JXFS")) {
%> <a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/MultipleAutoSelection.jspx"
								)%>">大乐透机选复式</a>
<%
	}
	if (type == "ZXDS" || type.equals("ZXDT")) {
%> <a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/DantuoSelfSelection.jspx"
								)%>">大乐透自选胆拖</a>
<%
	}
	if (type == "ZXFS" || type.equals("SXZX")) {
%> <a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/ShengXiaoSelection.jspx"
								)%>">大乐透12选2投注</a>
<%
	}
%> <%
 	out.print(CommonUtil.printChar());
 %>确认投注<br />
您的投注: <br />
大乐透<br />
金额: <%
	out.print(amount);
%>元<br />
注数: <%
	out.print(zhushu);
%>注<br />
倍数: <%
	out.print(beishu);
%>倍<br />
<%
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
%> <%
 	if (addNumber != null && !addNumber.trim().equals("")) {
 %> 追号: <%
 	out.print(addNumber);
 %>期<br />
<%
	}
%> <%
 	}
 %> <%
 	Integer zhushu1 = 0;
 	if (beishu.trim().equals("")) {
 		zhushu1 = zhushu / 1;
 	} else {
 		zhushu1 = zhushu / Integer.parseInt(beishu);
 	}
 %> 投注号码: <%
 	if (type == "DSJX" || type.equals("DSJX")) {
 		if (zhushu1 < 11) {
 %> 
 <form action="<%=response.encodeURL(path+ "/wap/daletou/ModifySingleAutoSelection.jspx")%>" method="post">
 	<input type="hidden" name="zhushu" value="<%=zhushu1%>"/>
 	<input type="hidden" name="beishu" value="<%=beishu%>"/>
 	<input type="hidden" name="zhuma" value="<%=zhuma%>"/>
 	<input type="hidden" name="oneMoney" value="<%=oneMoney%>"/>
 	<input type="hidden" name="newzhuma" value="<%=newzhuma.replace("<br/>", "~")%>"/>
 	<%
	logger.info("Form zhuma:" + zhuma);
	%>
	<%
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
%>
 	<input type="hidden" name="addNumber" value="<%=addNumber%>"/>
 	<%
	}
%>
 	<input type="hidden" name="type" value="<%=type%>"/>
 	<input type="hidden" name="token" value="<%=transctionId%>"/>
 	<input type="submit" value="修改"/>
 
 </form>
  <%
 	}
 	}

 	if (type == "JXFS" || type.equals("JXFS")) {
 		if (Integer.parseInt(zhushuStr) < 11) {
 %> 
 <form action="<%=response.encodeURL(path+ "/wap/daletou/ModifyMultipleAutoSelection.jspx")%>" method="post">
 	<input type="hidden" name="zhushuStr" value="<%=zhushuStr%>"/>
 	<input type="hidden" name="beishu" value="<%=beishu%>"/>
 	<input type="hidden" name="zhuma" value="<%=zhuma%>"/>
 	<input type="hidden" name="redSize" value="<%=redSize%>"/>
 	<input type="hidden" name="blueSize" value="<%=blueSize%>"/>
 	<input type="hidden" name="oneMoney" value="<%=oneMoney%>"/>
 	<input type="hidden" name="newzhuma" value="<%=newzhuma.replaceAll("<br/>", "~")%>"/>
 	<%
	logger.info("Form zhuma:" + zhuma);
	%>
	<%
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
%>
 	<input type="hidden" name="addNumber" value="<%=addNumber%>"/>
 	<%
	}
%>
 	<input type="hidden" name="type" value="<%=type%>"/>
 	<input type="hidden" name="token" value="<%=transctionId%>"/>
 	<input type="submit" value="修改"/>
 
 </form>
 <%
 	}
 	}
 %> <br />
<%
	if (beishu.trim().equals("")) {
		beishu = "1";
	}
	if (addNumber.trim().equals("")) {
		addNumber = "0";
	}
%> <%
 	out.print(newzhuma);
 %><br/>
 <form action="<%=response.encodeURL(path + "/daletou/bet.jspa" )%>" method="post">
 	<input type="hidden" name="zhushu" value="<%=zhushu%>"/>
 	<input type="hidden" name="beishu" value="<%=beishu%>"/>
 	<input type="hidden" name="amount" value="<%=amount%>"/>
 	<input type="hidden" name="zhuma" value="<%=zhuma%>"/>
 	<input type="hidden" name="oneMoney" value="<%=oneMoney%>"/>
 	<input type="hidden" name="newzhuma" value="<%=newzhuma.replaceAll("<br/>", "~")%>"/>
 	<%
	logger.info("Form zhuma:" + zhuma);
	%>
	<%
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
%>
 	<input type="hidden" name="addNumber" value="<%=addNumber%>"/>
 	<%
	}
%>
 	<input type="hidden" name="type" value="<%=type%>"/>
 	<input type="hidden" name="token" value="<%=transctionId%>"/>
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
 	<input type="submit" value="确认投注"/>
 
 </form>
  <%
 	if (type == "DSJX" || type.equals("DSJX")) { //单式机选
 %> <%
 	if (rbint.getString("array3addNumberSwitch").equals("1")) {
 %> 
 <form action="<%=response.encodeURL(path + "/daletou/showAutoBetDetails.jspx" )%>" method="post">
 	<input type="hidden" name="ZJnum" value="<%=oneMoney%>"/>
 	<input type="hidden" name="beishu" value="<%=beishu%>"/>
 	<input type="hidden" name="zhushuStr" value="<%=zhushu1%>"/>
 	<input type="hidden" name="addNumber" value="<%=addNumber%>"/>
 	<input type="hidden" name="type" value="DSJX"/>
 	<input type="hidden" name="token" value="<%=transctionId%>"/>
 	<input type="submit" value="重新选号"/>
 
 </form>
<%
	} else {
%>  <form action="<%=response.encodeURL(path + "/daletou/showAutoBetDetails.jspx" )%>" method="post">
 	<input type="hidden" name="ZJnum" value="<%=oneMoney%>"/>
 	<input type="hidden" name="beishu" value="<%=beishu%>"/>
 	<input type="hidden" name="zhushuStr" value="<%=zhushu1%>"/>
 	<input type="hidden" name="type" value="DSJX"/>
 	<input type="hidden" name="token" value="<%=transctionId%>"/>
 	<input type="submit" value="重新选号"/>
 
 </form>
<%
	}
%> <%
 	}
 %> <%
 	if (type == "JXFS" || type.equals("JXFS")) { //复式机选
 %> <%
 	if (rbint.getString("array3addNumberSwitch").equals("1")) {
 %> 
 	 <form action="<%=response.encodeURL(path + "/daletou/showAutoMultipleDetail.jspx" )%>" method="post">
 	<input type="hidden" name="ZJnum" value="<%=oneMoney%>"/>
 	<input type="hidden" name="beishu" value="<%=beishu%>"/>
 	<input type="hidden" name="blueSize" value="<%=blueSize%>"/>
 	<input type="hidden" name="redSize" value="<%=redSize%>"/>
 	<input type="hidden" name="zhushu" value="<%=zhushuStr%>"/>
 	<input type="hidden" name="addNumber" value="<%=addNumber%>"/>
 	<input type="hidden" name="type" value="JXFS"/>
 	<input type="hidden" name="token" value="<%=transctionId%>"/>
 	<input type="submit" value="重新选号"/>
 
 </form>
<%
	} else {
%>  <form action="<%=response.encodeURL(path + "/daletou/showAutoMultipleDetail.jspx" )%>" method="post">
 	<input type="hidden" name="ZJnum" value="<%=oneMoney%>"/>
 	<input type="hidden" name="beishu" value="<%=beishu%>"/>
 	<input type="hidden" name="blueSize" value="<%=blueSize%>"/>
 	<input type="hidden" name="redSize" value="<%=redSize%>"/>
 	<input type="hidden" name="zhushu" value="<%=zhushuStr%>"/>
 	<input type="hidden" name="type" value="JXFS"/>
 	<input type="hidden" name="token" value="<%=transctionId%>"/>
 	<input type="submit" value="重新选号"/>
 
 </form>
<%
	}
%> <%
 	}
 %> <br />
【最新开奖】<br />
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%> <%
 	out.print(code);
 %> <br />
<%
	}
%><br />
	</body>
