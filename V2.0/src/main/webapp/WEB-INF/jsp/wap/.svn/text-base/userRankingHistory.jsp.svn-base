<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>新闻资讯</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-中奖排行<br />
<c:choose>
	<c:when test="${type ==5 }">
		总排行|<a href="/winningRankingHistory/userRankingHistory.jspx?type=4">年排行</a>|<a href="/winningRankingHistory/userRankingHistory.jspx?type=3">月排行</a>|<a href="/winningRankingHistory/userRankingHistory.jspx?type=2">周排行</a><br/>
		<c:if test="${not empty userRankingHistoryList }">
		<table>
			<c:forEach varStatus="user" items="${userRankingHistoryList}" var="userRankingHistory">
				
					<tr>
						<td>${user.index+1 }.</td>
						<td>${userRankingHistory.nickName } </td>
						<td> ${userRankingHistory.prizeAmt }元</td>
					</tr>
			</c:forEach>
			</table>
		</c:if>
	</c:when>
	<c:when test="${type ==4 }">
		<a href="/winningRankingHistory/userRankingHistory.jspx?type=5">总排行</a>|年排行|<a href="/winningRankingHistory/userRankingHistory.jspx?type=3">月排行</a>|<a href="/winningRankingHistory/userRankingHistory.jspx?type=2">周排行</a><br/>
		<c:if test="${not empty userRankingHistoryList }">
		<table>
			<c:forEach varStatus="user" items="${userRankingHistoryList}" var="userRankingHistory">
				
					<tr>
						<td>${user.index+1 }.</td>
						<td>${userRankingHistory.nickName } </td>
						<td> ${userRankingHistory.prizeAmt }元</td>
					</tr>
			</c:forEach>
			</table>
		</c:if>
	</c:when>
	<c:when test="${type ==3 }">
		<a href="/winningRankingHistory/userRankingHistory.jspx?type=5">总排行</a>|<a href="/winningRankingHistory/userRankingHistory.jspx?type=4">年排行</a>|月排行|<a href="/winningRankingHistory/userRankingHistory.jspx?type=2">周排行</a><br/>
		<c:if test="${not empty userRankingHistoryList }">
		<table>
			<c:forEach varStatus="user" items="${userRankingHistoryList}" var="userRankingHistory">
				
					<tr>
						<td>${user.index+1 }.</td>
						<td>${userRankingHistory.nickName } </td>
						<td> ${userRankingHistory.prizeAmt }元</td>
					</tr>
			</c:forEach>
			</table>
		</c:if>
	</c:when>
	<c:when test="${type ==2 }">
		<a href="/winningRankingHistory/userRankingHistory.jspx?type=5">总排行</a>|<a href="/winningRankingHistory/userRankingHistory.jspx?type=4">年排行</a>|<a href="/winningRankingHistory/userRankingHistory.jspx?type=3">月排行</a>|周排行<br/>
		<c:if test="${not empty userRankingHistoryList }">
		<table>
			<c:forEach varStatus="user" items="${userRankingHistoryList}" var="userRankingHistory">
				
					<tr>
						<td>${user.index+1 }.</td>
						<td>${userRankingHistory.nickName } </td>
						<td> ${userRankingHistory.prizeAmt }元</td>
					</tr>
			</c:forEach>
			</table>
		</c:if>
	</c:when>
</c:choose>
<br/><a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
