<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%><title>投注查询</title>
<body>

		<%
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String now = format.format(date);
		%>
		<form action="<%=response.encodeURL(path+"/BetingSelectAction/bettingSelect.jspa")%>" method="get" >
			开始日期<input name="startDate" type="text" maxlength="10" size="10" format="*m" emptyok="true" value="<%=now %>"/>
			<br/>
			结束日期<input name="endDate" type="text" maxlength="10" size="10" format="*m" emptyok="true" value="<%=now %>"/>
			<br/>
			<input type="submit" value="查询"/>
			</form>
</body>
