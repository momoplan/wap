<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<title>账户明细</title>
<body>

<%
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
%><a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/userinfo/userCenter.jspx">用户中心</a> <%
 	out.print(CommonUtil.printChar());
 %>账户明细<br />

<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
%> 提示:<a style="color: red"> <%
	out.println(message);
%></a><br />
<%
	}
%> 日期格式:yyyymmdd<br />
例如:20100202<br />
解释:2010年02月02日<br />
<form action="<%=response.encodeURL(path + "/touzhu/accountDetailSelect.jspa" )%>"
	method="post">起始时间: <input name="begintime" type="text"
	emptyok="false" maxlength="40" size="12"
	value="<%=df.format(new Date(new Date().getTime() - 7 * 24 * 60
							* 60 * 1000))%>" /><br />
终止时间: <input name="endtime" type="text" emptyok="false" maxlength="40"
	size="12" value="<%=df.format(new Date())%>" /><br />
<INPUT name="beginId" type="hidden" value="0" /> <INPUT name="endId"
	type="hidden" value="10" /> <input type="submit" value="查询" /></form>
<br />
<a href="/wap/userinfo/userCenter.jspx">返回上一页</a>
</body>
