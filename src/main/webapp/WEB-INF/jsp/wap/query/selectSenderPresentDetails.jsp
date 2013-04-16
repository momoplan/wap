<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>赠送查询</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
-<a href="/w/wap/query/selectGift.jspx">赠送查询</a>-已赠送彩票<br/><br/>
<c:if test="${not empty presents }">
		<c:forEach items="${presents }" var="present">
			好友手机号:${present.mobile }<br />
			购买金额:${present.amt }<br />             
			彩票期号:${present.batchCode }<br />
			彩票种类:${present.lotName }<br />
			投注方式:${present.betType }<br />
			倍数:${present.beishu }<br />
			注码:<br />${present.betCode }<br />
			赠彩日期:${present.createTime }<br />
		</c:forEach>
			<c:if test="${upPage }">
		<c:url value="/BetingSelectAction/selectSenderPresentDetails.jspa" var="start">
			<c:param name="startLine" value="0"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${start }">首页</a>
		<c:url value="/BetingSelectAction/selectSenderPresentDetails.jspa" var="up">
			<c:param name="startLine" value="${endLine*(nowPage-2) }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${up }">上一页</a>
	</c:if>
	<c:if test="${nextPage }">
		<c:url value="/BetingSelectAction/selectSenderPresentDetails.jspa" var="next">
			<c:param name="startLine" value="${endLine*nowPage }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${next }">下一页</a>
		<c:url value="/BetingSelectAction/selectSenderPresentDetails.jspa" var="end">
			<c:param name="startLine" value="${(totalPage-1)*endLine}"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${end }">尾页</a>
	</c:if>
	</c:if>
	<c:if test="${empty presents }">
		${messageError }
	</c:if>
</body>
