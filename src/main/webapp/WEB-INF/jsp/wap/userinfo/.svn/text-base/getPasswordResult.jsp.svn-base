<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.URLEncoder,com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%

	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>密码找回</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-找回密码结果<br/><br/>
<%
	String message = (String) request.getAttribute("message");
	String userName = (String) request.getAttribute("userName");
	if (userName==null) {
		userName = "";
	}
%> 
<a style = "color:#DE0201"><%=userName %></a>,<%=message %><br/><br/>

马上去<a href="/w/wap/userinfo/login.jspx">登录</a><br/>

<a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
