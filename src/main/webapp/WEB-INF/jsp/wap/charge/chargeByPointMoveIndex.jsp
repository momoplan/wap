<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>账户充值--电信卡充值</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
			out.print(CommonUtil.printChar());
		%>手机卡充值<br/><br/>
		    请选择充值卡类型<br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeByPointMoveEpayTofaceValue.jspx")%>">移动充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeByPointMoveLTEpayTofaceValue.jspx")%>">联通充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeByPointMoveDXEpayTofaceValue.jspx")%>">电信充值卡</a><br/>
 提醒:请用户充值时,认真查看.<br/>		
<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">充值首页</a>
</body>