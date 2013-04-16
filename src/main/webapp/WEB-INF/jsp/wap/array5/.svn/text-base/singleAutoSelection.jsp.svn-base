<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = request.getContextPath();
%>
<title>排列五单式机选</title>
<body>
<%
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = "";//追期
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String) request.getAttribute("addNumber");
		if (addNumber == null)
			addNumber = "1";
	}
	String err_msg = (String) request.getAttribute("err_msg");
	String zhuma = (String) request.getAttribute("zhuma");
	if (zhuma == null)
		zhuma = "";
	String zhushu = (String) request.getAttribute("zhushu");
	if (zhushu == null)
		zhushu = "1";//默认1注
	String beishu = (String) request.getAttribute("beishu");
	if (beishu == null)
		beishu = "1";
	String deadline = CommonUtil.getDeadline("T01011", 0);
	if (deadline == null)
		deadline = "";
%><a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>排列五<br />
<a href="/w/wap/array5/array5ByHand.jspx">自选</a>|机选<br/>
单式机选|<a href="/wap/array5/MulipleAutoSelection.jspx">复式机选</a><br/>
<%
	if (deadline != null) {
%> <%
 	out.print(deadline);
 %><br />
<%
	}
%> <a style="color: red"><%
 	if (err_msg != null) {
 		out.print(CommonUtil.printWarningMessage(err_msg) + "<br/>");
 	}
 %> </a>
<form action="/array5/singleAutoBetDetail.jspx" method="post"> 
 机选注数:<input name="zhushu" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=zhushu%>" /><br />
倍数:<input name="beishu" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=beishu%>" />(最多99倍)<br />
<%
	if (rbint.getString("addNumberSwitch").equals("1")) {
%> 追号:<input name="addNumber" type="text" emptyok="true" maxlength="2"
	size="2" format="*N" value="<%=addNumber%>" />期<br />
(最高99期,默认追1期,即买当前期)<br />
<%
	}
%>
<input type="hidden" name="type" value="DSJX" />
<input type="submit" value="确定机选" />
</form>
<%
	List<String> list = CommonUtil.getBetCode("T01011","5");
%> <br/>【最新开奖】<br/>
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%> <%
 	out.print(code);
 %> <br />
<%
	}
%> 
<br />
		   <a href="<%=response.encodeURL(path + "/wap/array5/array5Index.jspx"
								)%>">返回上一页</a>
</body>
