<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>排列五复式机选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>排列五<br />
<a href="/w/wap/array5/array5ByHand.jspx">自选</a>|机选<br/>
<a href="/wap/array5/singleAutoSelection.jspx">单式机选</a>|复式机选<br/>

<%
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String) request.getAttribute("addNumber");
		if (addNumber == null)
			addNumber = "1";
	}
	String err_msg = (String) request.getAttribute("err_msg");
	String beishu = (String) request.getAttribute("beishu");
	if (beishu == null)
		beishu = "1";
	String deadline = CommonUtil.getDeadline("T01011", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %><br />
<a style="color: red"><%
	if (err_msg != null) {
		out.print(CommonUtil.printWarningMessage(err_msg) + "<br/>");
	}
%> </a>选择号码的个数：<br />
<form action="/array5/mulipleAutoBetdetail.jspx" method="post">
①<select name="num1">
	<option value="1">1个</option>
	<option value="2">2个</option>
	<option value="3">3个</option>
	<option value="4">4个</option>
	<option value="5">5个</option>
	<option value="6">6个</option>
	<option value="7">7个</option>
	<option value="8">8个</option>
	<option value="9">9个</option>
</select> <br />
②<select name="num2">
	<option value="1">1个</option>
	<option value="2">2个</option>
	<option value="3">3个</option>
	<option value="4">4个</option>
	<option value="5">5个</option>
	<option value="6">6个</option>
	<option value="7">7个</option>
	<option value="8">8个</option>
	<option value="9">9个</option>
</select> <br />
③<select name="num3">
	<option value="1">1个</option>
	<option value="2">2个</option>
	<option value="3">3个</option>
	<option value="4">4个</option>
	<option value="5">5个</option>
	<option value="6">6个</option>
	<option value="7">7个</option>
	<option value="8">8个</option>
	<option value="9">9个</option>
</select> <br />
④<select name="num4">
	<option value="1">1个</option>
	<option value="2">2个</option>
	<option value="3">3个</option>
	<option value="4">4个</option>
	<option value="5">5个</option>
	<option value="6">6个</option>
	<option value="7">7个</option>
	<option value="8">8个</option>
	<option value="9">9个</option>
</select> <br />
⑤<select name="num5">
	<option value="1">1个</option>
	<option value="2">2个</option>
	<option value="3">3个</option>
	<option value="4">4个</option>
	<option value="5">5个</option>
	<option value="6">6个</option>
	<option value="7">7个</option>
	<option value="8">8个</option>
	<option value="9">9个</option>
</select> <br />

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
<input type="hidden" name="type" value="FS" />
<input type="submit" value="提交投注" />
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
