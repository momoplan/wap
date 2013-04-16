<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	Logger logger = Logger.getLogger("BetDetail");
	List<String> list = CommonUtil.getBetCode("T01001","5");
%>
<title>大乐透机选投注</title>
<body>

<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	request.getSession().setAttribute(transctionId + "", "false");
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = "";
	String regex = "([1-9][0-9])";
	//页面跳转参数
	Integer zhushu = 0;
	String newzhuma = request.getParameter("newzhuma");
	String beishu = "";
	String oneMoney = "";
	if (newzhuma == null || "".equals(newzhuma)) {
		newzhuma = (String) request.getAttribute("newzhuma");
		//	zhuma = (String) request.getAttribute("zhuma");
		beishu = (String) request.getAttribute("beishu");
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = (String) request.getAttribute("addNumber");
		}

		zhushu = Integer.parseInt((String) request
				.getAttribute("zhushuStr"));
		oneMoney = request.getAttribute("oneMoney") == null ? "2"
				: (String) request.getAttribute("oneMoney");
	} else {
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
		}
		//	zhuma = request.getParameter("zhuma");
		beishu = request.getParameter("beishu");
		oneMoney = request.getParameter("oneMoney");
		zhushu = Integer.parseInt(request.getParameter("zhushu"));
		newzhuma = newzhuma.replaceAll("~", "<br/>");
	}
	String[] zhumazu = newzhuma.split("<br/>");
	int size = zhumazu.length;
	String certErr = (String) request.getAttribute("err_msg");
	request.removeAttribute("err_msg");
%>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %> <a
	href="<%=response
							.encodeURL(path
									+ "/wap/daletou/SingleAutoSelection.jspx"
									)%>">大乐透单式机选</a>
<%
	out.print(CommonUtil.printChar());
%>注码修改<br />
<a style="color: red"><%
	if (certErr != null) {
		out.print(CommonUtil.printWarningMessage(certErr) + "<br/>");
	}
%></a> 您的投注:<br />
大乐透<br />
<form action="<%=response.encodeURL(path + "/daletou/ModifySingleAutoBetDetails.jspx" )%>" method="post">
	倍数:<input name="beishu" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=beishu%>" />(最多50倍)<br />
<%
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
%> 追号:<input name="addNumber" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=addNumber%>" />期<br />
<%
	}
%> 投注号码:<br />
<%
	if (beishu.trim().equals("")) {
		beishu = "1";
	}
	if (addNumber.trim().equals("")) {
		addNumber = "0";
	}
%> <%
 	for (int i = 0; i < size; i++) {
 %> <input name="zhu<%=i%>" type="text" emptyok="false" maxlength="20"
	size="20" format="*N" value="<%=zhumazu[i]%>" /><br />

<%
	}
%>

<input type="hidden" name="size" value="<%=size%>"/>
<input type="hidden" name="zhushu" value="<%=zhushu%>"/>
<input type="hidden" name="numZJ" value="<%=oneMoney%>"/>
<input type="hidden" name="type" value="DSJX"/>
<input type="hidden" name="beishu" value="$(beishu)"/>
<input type="hidden" name="token" value="<%=transctionId%>"/>
<input type="submit" value="确认修改"/>

</form>
<%
 	if (rbint.getString("array3addNumberSwitch").equals("1")) {
 		if (!beishu.matches(regex)) {
 			beishu = "1";
 		}
 		if (!addNumber.matches(regex)) {
 			addNumber = "1";
 		}
 %> 
 <form action="<%=response.encodeURL(path + "/daletou/showAutoBetDetails.jspx"  )%>" method="post">
 	<input type="hidden" name="ZJnum" value="<%=oneMoney %>"/>
 	<input type="hidden" name="zhushuStr" value="<%=zhushu %>"/>
 	<input type="hidden" name="beishu" value="<%=beishu %>"/>
 	<input type="hidden" name="type" value="DSJX"/>
 	<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
 	<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
 	<input  type="submit" value="重新选号"/>
 </form>
<%
	} else {
		if (!beishu.matches(regex)) {
 			beishu = "1";
 		}
%> 
<form action="<%=response.encodeURL(path + "/daletou/showAutoBetDetails.jspx"  )%>" method="post">
 	<input type="hidden" name="ZJnum" value="<%=oneMoney %>"/>
 	<input type="hidden" name="zhushuStr" value="<%=zhushu %>"/>
 	<input type="hidden" name="beishu" value="<%=beishu %>"/>
 	<input type="hidden" name="type" value="DSJX"/>
 	<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
 	<input  type="submit" value="重新选号"/>
 </form>
<%
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
