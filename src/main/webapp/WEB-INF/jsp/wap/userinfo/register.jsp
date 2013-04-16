<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	String userName = request.getAttribute("userName") == null ? ""
			: (String) request.getAttribute("userName");
	String fromSign = request.getParameter("fromSign") == null ? ""
			: (String) request.getParameter("fromSign");
%>
<title>新用户注册</title>
<body>

<a href="/wap/wapindex.jspx">首页</a>-新用户注册<br />

请填写用户名并设置一个密码<br />
<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
%> 提示:<a style="color: red"><%
	out.println(message);
%></a><br />
<%
	}
%>
<form action= "/userWap/register.jspx" method="post">
<input name="token" type="hidden" value="${token}" />
用户名(4-16个字符，可以使用字母、数字、下划线):<br />
<input name="userName" type="text" maxlength="16" size="10"
	format="*N" emptyok="true" value="<%=userName%>"/><br />
设置密码(6-16个字符，建议使用字母、数字组合、混合大小写):<br />
<input name="password" type="password" maxlength="16" size="10"
	format="*m" emptyok="true" /><br />
重复一次密码:<br />
<input name="realPass" type="password" maxlength="16" size="10"
	format="*m" emptyok="true" /><br />
<input name="userID" type="hidden" value="" /> 
<input
	name="fromSign" type="hidden" value="<%=fromSign%>" /> 
	<input type="checkbox" name="checkbox" value="1" checked="checked"/>我已满十八周岁且已阅读并同意<a
	href="<%=response.encodeURL(path + "/wap/userinfo/itemShow.jspx"
									)%>">《用户服务协议》</a><br />
	<input
	type="submit" value="确定" /></form>
<br />
<a href="<%=response.encodeURL(path + "/wap/userinfo/login.jspx" )%>">我已注册</a><br />

<a href="<%=response.encodeURL(path + "/wap/wapindex.jspx"
								)%>">返回上一页</a>
</body>
