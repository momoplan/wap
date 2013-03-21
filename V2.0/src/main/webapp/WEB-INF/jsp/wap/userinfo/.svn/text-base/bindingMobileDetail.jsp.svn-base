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
String mobileid = request.getAttribute("mobileid")== null ? "" : (String) request.getAttribute("mobileid");
String userName = request.getAttribute("userName")==null ? "" : (String) request.getAttribute("userName");
String bindingMobile = request.getAttribute("bindingMobile") == null ?"":(String) request.getAttribute("bindingMobile");
%>
 <a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a> <%
 	out.print(CommonUtil.printChar());
 %>手机绑定<br />
<%
	if (message != null) {
%> <a style="color: red"><%
 	out.print(CommonUtil.printWarningMessage(message));
 %> </a> <%
 	}
 %><br />
 用户名：<%=userName %><br/>
 <%
 if(mobileid ==null ||"".equals(mobileid)||"null".equals(mobileid)){%>
 <form action="<%=response.encodeURL(path + "/userWap/bindingMobileGetCode.jspa" )%>"
	method="post">
 请输入要绑定的手机号：<br/>
 <input name="mobileid" type="mobileid"
	maxlength="11" size="11" format="*m" emptyok="true" value="<%=bindingMobile %>" /><br />
	<input type="hidden" name="userName" value="<%=userName %>" />
	<input type="hidden" name="type" value="binding" />
 <input	type="submit" value="绑定" /></form>
 <br />

	 
 <%}else{%>
 你绑定的手机号为：<%=mobileid.substring(0,7) %>****<br/>
  <form action="<%=response.encodeURL(path + "/userWap/cancelBindingMobile.jspa" )%>"
	method="post">
	<input type="hidden" name="mobileid" value="<%=mobileid %>" />
	<input type="hidden" name="userName" value="<%=userName %>" />
 	<input	type="submit" value="解绑" /></form>
 <br />
	 
 <%}
 %>

  <a href="/w/wap/userinfo/userCenter.jspx">返回上一页</a>
</body>
