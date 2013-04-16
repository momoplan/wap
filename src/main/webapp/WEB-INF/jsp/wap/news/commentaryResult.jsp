<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ruyicai.wap.util.*" %>
<%
String path = CommonUtil.removeTrailingSlash(request.getContextPath());
String message = (String)request.getAttribute("message");

%>
<title>新闻评论</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-新闻评论<br/><br/>
<%
if(message != null &&!"".equals(message)){%>
	<%=message %><br/>
<%}
%>
<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx")%>">继续</a><br/>
<a href="<%=response.encodeURL(path+"/wap/wapindex.jspx")%>">返回首页</a>
</body>