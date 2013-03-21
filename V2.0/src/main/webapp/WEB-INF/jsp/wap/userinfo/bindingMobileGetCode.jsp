<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>用户中心-手机绑定</title>
<body>


<%
	String message = (String) request.getAttribute("message");
String mobileid = (String) request.getAttribute("mobileid");
String userName = (String) request.getAttribute("userName");
String type = (String) request.getAttribute("type");
%>
 <a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a> <%
 	out.print(CommonUtil.printChar());
 %>手机绑定<br />
 <a style="color: red">
<%
	if (message != null) {
 	out.print(CommonUtil.printWarningMessage(message)+"<br>");
 	}
 %></a>
 用户名：<%=userName %><br/>
 你绑定的手机号为：<%=mobileid %><br/>
  <form action="<%=response.encodeURL(path + "/userWap/toBindingAndsendSMS.jspa" )%>"
	method="post">
 <input type="hidden" name="mobileid" value="<%=mobileid %>" />
	<input type="hidden" name="userName" value="<%=userName %>" />
	手机短信验证码：
<input type="text" name="checkcode" maxlength="10" size="10" value=""/>
<input type="hidden" name="type" value="<%=type %>" />
<input type="submit" name="bindingMobileGetCode" value="重新获取验证码" /><br/>
<input type="submit" name="bindingMobile" value="绑定" />
</form>
 <br />

  <a href="/w/wap/userinfo/userCenter.jspx">返回用户中心</a>
</body>
