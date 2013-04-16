<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.URLEncoder,com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%
	String channel = com.ruyicai.wap.util.WapUtil.getChannelId(request);
    if(channel!=null&&channel.equals("521")){
    	response.sendRedirect("http://hlxk.ruyicai.com/w/wap/userinfo/loginErrorHLXK.jspx");
    }
    String str = request.getHeader("Referer");
	if(str!=null&&str.indexOf("orderhm/caseLotDetail.jspx")>-1){
		request.getSession().setAttribute("strHM", str);
	}
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
%>
<title>用户登录</title>
<body>
<div class="contant">
 <a href="/w/wap/wapindex.jspx">首页</a>-用户登录<br/>
<%
	String message = (String) request.getAttribute("message");
	String userName = (String) request.getAttribute("userName");
	if (userName==null) {
		userName = "";
	}
	if (message != null && message.trim().length() > 0) {
%> <a style="color: red">
<%
 	out.println(message);
%></a><br />
<%
	}
%>
*我用如意彩账户登录<br/>
<form action="<%=response.encodeURL(path + "/userWap/login.jspx?random=" + transctionId)%>" method="post">
	用户名:<input name="userName" type="text" maxlength="16" size="16" value="<%=userName %>" /><br />
	密&nbsp;&nbsp;码:<input name="password" type="password" maxlength="16" size="16" value="" /> <br />
	<input type="submit" value="登录" />
</form>
<br />
<a href="<%=response.encodeURL(path + "/wap/userinfo/getPassword.jspx")%>">忘记密码</a>
<a href="/w/wap/userinfo/register.jspx">免费注册</a><br />
<a href="/w/wap/wapindex.jspx">返回上一页</a>
</div>
</body>
