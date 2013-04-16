<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>遗漏</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/select/getWinningInfoBylottery.jspx">开奖</a>-遗漏<br />
<c:if test="${not empty leaveOutMap }">
	<c:forEach items="${leaveOutMap.leaveOutCodeList }" var="leaveOutCode">
	</c:forEach>
</c:if>
<br/><a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
