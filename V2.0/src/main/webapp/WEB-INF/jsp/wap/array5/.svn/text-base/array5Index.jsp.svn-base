<%@page import="com.buybal.lot.lottype.Array5Util"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.ruyicai.wap.util.*,net.sf.json.JSONObject"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	String type = "T01011";
	JSONObject re = CommonUtil.getAutoZhuma(type);
	String newzhuma = re.getString("newzhuma");
	List<String> list = CommonUtil.getBetCode("T01011","5");
	String deadline = "";
	Array5Util array5Util = new Array5Util();
%>
<title>排列五</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>排列五<br />
<%
	deadline = CommonUtil.getDeadline("T01011", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %> <br/>
天天开奖,头奖十万元<br/>
今日推荐号码:<%=newzhuma %><br/>
<form action="/array5/oneBetDetail.jspx" method="post" style="margin:0px;display:inline;">
<input type="hidden" name="beishu" value="1" />
<input type="hidden" name="addNumber" value="1" />
<input type="hidden" name="zhushu" value="1" />
<input type="hidden" name="zhuma" value="<%=newzhuma %>" />
<tr><td><input type="submit"  value="确认投注"/></td></tr>
</form>
<form action="/wap/array5/array5Index.jspx" method="post" style="margin:0px;display:inline;">
<tr><td><input type="submit" value="重新选号"/></td></tr>
</form>

<br/>
<a
	href="<%=response.encodeURL(path
							+ "/wap/array5/array5ByHand.jspx" )%>">手选投注</a>
<a
	href="<%=response.encodeURL(path
							+ "/wap/array5/singleAutoSelection.jspx"
							)%>">单式机选</a><br />
<a
	href="<%=response.encodeURL(path
							+ "/wap/array5/MulipleAutoSelection.jspx"
							)%>">复式机选</a><br/><br/>
<br/>【最新开奖】<br/>
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
		   <a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
</body>
