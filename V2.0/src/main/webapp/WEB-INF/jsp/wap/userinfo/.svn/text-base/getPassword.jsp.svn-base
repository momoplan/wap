<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.URLEncoder,com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%
	String channel = com.ruyicai.wap.util.WapUtil.getChannelId(request);
    if(channel!=null&&channel.equals("521")){
    	response.sendRedirect("http://hlxk.ruyicai.com/w/wap/userinfo/loginErrorHLXK.jsp");
    }

	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
%>
<title>密码找回</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-找回密码<br/><br />
<%
	String message = (String) request.getAttribute("message");
	String userName = (String) request.getAttribute("userName");
	String bindingMobile = (String) request.getAttribute("bindingMobile");
	if (userName==null) {
		userName = "";
	}
	if (bindingMobile==null) {
		bindingMobile = "";
	}
	if (message != null && message.trim().length() > 0) {
%> 
<a style = "color:red">
<%
 	out.println(message);
%></a><br />
<%
	}
%>
<form action="<%=response.encodeURL(path + "/userWap/getPassword.jspx")%>" method="post">
	用户名:<input name="userName" type="text" emptyok="true" maxlength="16" size="11" format="*N" value="<%=userName %>" /><br />
	手机号:<input name="bindingMobile" type="text" emptyok="true" maxlength="11" size="11" format="*m" value="<%=bindingMobile %>" /> <br />
	如果您尚未绑定手机或用户名忘记了，请拨打如意彩客服电话<a style = "color:#DE0201">400-655-1000</a>，让客服人员协助您找回密码。<br/><br/>
	
	<input type="submit" value="立即找回" />
</form>
<br />

<a href="/w/wap/wapindex.jspx">返回上一页</a>
</body>
