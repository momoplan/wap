<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>大乐透复式机选</title>
<body>

<%
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");

	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String) request.getAttribute("addNumber");
		if (addNumber == null)
			addNumber = "1";
	}
	String redSize = request.getAttribute("redSize") == null ? ""
			: (String) request.getAttribute("redSize");
	String blueSize = request.getAttribute("blueSize") == null ? ""
			: (String) request.getAttribute("blueSize");
	String message = (String) request.getAttribute("message");
	String zhushu = (String) request.getAttribute("zhushu");
	if (zhushu == null)
		zhushu = "5";
	String beishu = (String) request.getAttribute("beishu");
	if (beishu == null)
		beishu = "1";
	String deadline = CommonUtil.getDeadline("T01001", 0);
	if (deadline == null)
		deadline = "";
%> <a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%>
</a>
<%
	out.print(CommonUtil.printChar());
%>大乐透<br />
<a href="/w/wap/daletou/SingleSelfSelection.jspx">自选</a>|机选<br/>
<a href="/wap/daletou/SingleAutoSelection.jspx">单式机选</a>|复式机选<br/>

<%
	if (deadline != null) {
%> <%
 	out.print(deadline);
 %><br />
<%
	}
%><a style="color: red"> <%
 	if (message != null) {
 		out.print(CommonUtil.printWarningMessage(message) + "<br/>");
 	}
 %></a>
<form action="<%=response.encodeURL(path + "/daletou/showAutoMultipleDetail.jspx" )%>" method="post">
  前区号码个数(5-15):<br />
<input name="redSize" type="text" emptyok="false" maxlength="2" size="2"
	format="*N" value="<%=redSize%>" /><br />
后区号码个数(2-12):<br />
<input name="blueSize" type="text" emptyok="false" maxlength="2"
	size="2" format="*N" value="<%=blueSize%>" /><br />
选择组数:<br />
<input name="zhushu" type="text" emptyok="false" maxlength="3" size="3"
	format="*N" value="<%=zhushu%>" /><br />
是否追加:<select name="ZJnum">
	<option value="2">否</option>
	<option value="3">是</option>
</select> (追加后每注3元)<br />
倍数:<input name="beishu" type="text" emptyok="false" maxlength="2"
	size="2" format="*N" value="<%=beishu%>" />(最多50倍)<br />
<%
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
%> 追号:<input
	name="addNumber" type="text" emptyok="true" maxlength="2" size="2"
	format="*N" value="<%=addNumber%>" />期<br />
(最高99期,默认追1期,即买当前期)<br />
<%
	}
%>
<input type="hidden" name="type" value="JXFS"/>
<input type="hidden" name="num" value="$(num)"/>
<input type="submit" value="提交投注 "/>
</form> 
<br/>
<%
	List<String> list = CommonUtil.getBetCode("T01001","5");
%> 【最新开奖】<br />
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
 <a href="<%=response.encodeURL(path + "/wap/daletou/DaletouIndex.jspx"
								)%>">返回上一页</a>
	</body>
