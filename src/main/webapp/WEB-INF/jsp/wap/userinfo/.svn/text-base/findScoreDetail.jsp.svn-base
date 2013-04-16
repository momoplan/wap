<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>用户积分</title>
<body>

<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-我的积分<br/><br/>
我现有的积分为<a style="color: red"><b>${score }</b></a>  &nbsp;  <a href="/w/wap/help/howScore.jspx">如何获得更多积分</a> <br/>
<form action="/w/userWap/transScore.jspa">
我要用<input name="tradScore" type="text" value="" size="5" maxlength="5"/>个积分兑换
<input name="handsel" type="text" value="" size="5"/>元购彩金
<input name="score" type="hidden" value="${score }"/>
<input type="submit" value="兑换">
</form><br/>
注：500个积分可以兑换1元购彩金（请输入500的倍数），存入您的如意彩账户。<br/>
<b>积分明细</b><br/>
<table>
	<tr align="center">
		<td>序号</td>
		<td>时间</td>
		<td>积分变动</td>
		<td>说明</td>
	</tr>


<c:if test="${not empty scores }">
		<c:forEach items="${scores }" var="score">
		<tr align="center">
		<td>${score.id }</td>
		<td>${score.createTime }</td>
		<td>${score.score }</td>
		<td> ${score.scoreType}</td>
	</tr>
 		</c:forEach>
 		<tr align="center">
 		<td></td>
			<c:if test="${upPage }">
		<c:url value="/userWap/findScoreDetailByUserno.jspa" var="start">
			<c:param name="startLine" value="0"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<td> <a href="${start }">首页</a></td>
		<c:url value="/userWap/findScoreDetailByUserno.jspa" var="up">
			<c:param name="startLine" value="${endLine*(nowPage-2) }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<td><a href="${up }">上一页</a></td>
	</c:if>
	</tr>
	<tr align="center">
	<td></td>
	<c:if test="${nextPage }">
		<c:url value="/userWap/findScoreDetailByUserno.jspa" var="next">
			<c:param name="startLine" value="${endLine*nowPage }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<td><a href="${next }">下一页</a></td>
		<c:url value="/userWap/findScoreDetailByUserno.jspa" var="end">
			<c:param name="startLine" value="${(totalPage-1)*endLine}"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<td><a href="${end }">尾页</a></td>
	</c:if>
	</tr>
	</c:if>
	</table>
	<c:if test="${empty scores }">
		${messageError }
	</c:if>
<br/>
	</body>

