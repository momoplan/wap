<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
	<title>如意彩平台显示</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
			out.print(CommonUtil.printChar());
		%>如意彩平台显示<br/>
		<%
			String message = (String)request.getAttribute("pmessage");
			if(message!=null && message.trim().length()>0)
			{%>
				<% out.println(message); %>
			<%}
		%>
</body>