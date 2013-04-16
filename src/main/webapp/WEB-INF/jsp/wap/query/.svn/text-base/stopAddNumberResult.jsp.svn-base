<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat,net.sf.json.JSONObject" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	String json = (String)request.getAttribute("json");
	String error_code = "";
	if (json!=null && !json.trim().equals("")) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		if (jsonObject.has("errorCode")) {
			error_code = jsonObject.getString("errorCode");
		}
	}
%>
<title>追号信息</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
		<%out.print(CommonUtil.printChar());%>停止追号<br/>
		<br/>
		<%
			if (error_code.trim().equals("0")) {
				out.println("成功停止追号!");
			} else {
				out.println("停止追号失败,请稍后再试!");
			}
		%>
</body>
