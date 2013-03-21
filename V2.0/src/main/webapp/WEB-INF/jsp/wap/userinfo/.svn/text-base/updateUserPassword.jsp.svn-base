<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>用户中心-修改密码</title>
<body>

<%
	String message = (String) request.getAttribute("message");
%>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a> <%
 	out.print(CommonUtil.printChar());
 %>修改密码<br />
 <a style="color: red">
<%
	if (message != null) {
	out.print(CommonUtil.printWarningMessage(message));
 	}
 %></a><br />
<form action="<%=response.encodeURL(path + "/userWap/changePassword.jspa" )%>"
	method="post">
原密码:<input name="oldPassword" type="password"
	maxlength="16" size="16" format="*m" emptyok="true" value="" /><br />
新密码:<input name="newPassword" type="password" maxlength="16" size="16"
	format="*m" emptyok="true" value="" /><br />
确认密码:<input name="realNewPass" type="password" maxlength="16" size="16"
	format="*m" emptyok="true" value="" /><br />
 <input	type="submit" value="修改" /></form>
 <br />
  <a href="/w/wap/userinfo/userCenter.jspx">返回上一页</a>
</body>
