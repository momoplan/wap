<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>投注结果</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-投注结果<br />
<c:if test="${not empty betResult }">
提示信息：<span style="color: red;">${betResult }</span>
</c:if>
<br />
			
<a href="/w/wap/buyLottery.jspx">继续投注</a> <a
	href="/w/wap/query/betingOrdersSelect.jspx">查看记录详细</a><br />
	<c:if test="${not empty account }">
	冻结金额：${account.freezeBalance }<br/>
	可用金额：${account.betBalance}<br/>	
	</c:if>
	
</body>
