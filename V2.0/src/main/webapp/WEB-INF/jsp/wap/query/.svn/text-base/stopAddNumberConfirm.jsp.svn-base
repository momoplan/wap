<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	String flowNo = request.getParameter("flowNo")==null?"":(String)request.getParameter("flowNo");
%><title>追号信息</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
		<%out.print(CommonUtil.printChar());%>
		<a href="/BetingSelectAction/addNumberSelect.jspa">追号查询</a><%out.print(CommonUtil.printChar());%>停止追号<br/>
		<br/>是否确定停止追号?<br/><br/>
		<a href="/BetingSelectAction/addNumberDetail.jspa?flowNo=<%=flowNo %>">取消</a>
		<a href="/BetingSelectAction/stopAddNumber.jspa?flowNo=<%=flowNo %>">确定</a>
</body>
