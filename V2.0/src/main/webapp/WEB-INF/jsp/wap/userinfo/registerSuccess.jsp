<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	String userName = request.getAttribute("userName") == null ? ""
			: (String) request.getAttribute("userName");
%>
<title>新用户注册</title>
<body>

<a href="/wap/wapindex.jspx">首页</a>-新用户注册<br />
<a style = "color:#DE0201"><%=userName %></a>，您已注册成功。<br/><br/>
为了您的账户安全，请先完善您的用户信息。<br/>
<a href="<%=response.encodeURL(path + "/userWap/getUserinfo.jspa" )%>">立即完善用户信息>></a><br />
以后再填，先做其他：<br/>
<a href="<%=response.encodeURL(path + "/wap/charge/chargeIndex.jspx" )%>">立即充值>></a>
<a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx" )%>">立即购彩>></a><br />
<a href="<%=response.encodeURL(path + "/orderhm/caseLotLottery.jspx" )%>">参与合买>></a>
<a href="<%=response.encodeURL(path + "/clientContrallor/clientDown.jspx" )%>">下载客户端>></a><br /><br />

<a href="<%=response.encodeURL(path + "/wap/wapindex.jspx"
								)%>">返回首页</a>
</body>
