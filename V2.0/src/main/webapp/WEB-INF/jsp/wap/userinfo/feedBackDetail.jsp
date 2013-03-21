<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>用户留言</title>
<body>

<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-用户留言<br/><br/>

<c:if test="${not empty msgRequests }">
		<c:forEach items="${msgRequests }" var="msgRequest">
			我的留言:${msgRequest.content }<br />
			留言回复:
			<c:if test="${not empty msgRequest.reply}">${msgRequest.reply}</c:if>
			<br /><br />
		</c:forEach>
			<c:if test="${upPage }">
		<c:url value="/userWap/selectMsgs.jspa" var="start">
			<c:param name="startLine" value="0"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${start }">首页</a>
		<c:url value="/userWap/selectMsgs.jspa" var="up">
			<c:param name="startLine" value="${endLine*(nowPage-2) }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${up }">上一页</a>
	</c:if>
	<c:if test="${nextPage }">
		<c:url value="/userWap/selectMsgs.jspa" var="next">
			<c:param name="startLine" value="${endLine*nowPage }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${next }">下一页</a>
		<c:url value="/userWap/selectMsgs.jspa" var="end">
			<c:param name="startLine" value="${(totalPage-1)*endLine}"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${end }">尾页</a>
	</c:if>
	</c:if>
	<c:if test="${empty msgRequests }">
		${messageError }
	</c:if>
<br/>
	</body>

