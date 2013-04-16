<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
%>
<title>如意彩平台</title>
<body>
 <a href="/wap/wapindex.jspx">首页</a><br/>
 网络异常!
			<a href="<%=response.encodeURL(path+"/wap/wapindex.jspx")%>"></a>
			<br/><br/>
</body>
	
