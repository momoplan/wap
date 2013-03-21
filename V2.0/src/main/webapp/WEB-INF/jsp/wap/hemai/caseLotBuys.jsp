<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4><a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-合买查询</h4>
<c:if test="">

</c:if>

<c:choose>
	<c:when test="${not empty caseLotList}">
	<table>
		<tr>
			<td>合买类型  </td>
			<td>时间 </td>
			<td>彩种 </td>
			<td>认购金额 </td>
			<td>保底金额 </td>
			<td>方案总额 </td>
			<td>进度 </td>
			<td>状态</td>
		</tr>
		<c:forEach items="${caseLotList }" var="caseLot">
		<tr>
			<td>${caseLot.buyType }</td>
			<td>${caseLot.buyTime }</td>
			<td>${caseLot.lotName }</td>
			<td>${caseLot.oneBuyAmt }元</td>
			<td>${caseLot.oneSafeAmt }元</td>
			<td>${caseLot.totalAmt }元 </td>
			<td>${caseLot.progress }</td>
			<td>${caseLot.displayStateMemo }</td>
			<td><a href="/w/orderhm/caseLotUserBuyDetail.jspa?caseLotId=${caseLot.caseLotId }">查看</a></td>
		</tr>
			
		</c:forEach>
			</table>
		<c:if test="${upPage }">
		<c:url value="/orderhm/caseLotBuys.jspa" var="start">
			<c:param name="startLine" value="0"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
			<c:param name="maxLine" value="1"></c:param>
		</c:url>
		<a href="${start }">首页</a>
		<c:url value="/orderhm/caseLotBuys.jspa" var="up">
			<c:param name="startLine" value="${startLine-endLine }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
			<c:param name="maxLine" value="${maxLine-1 }"></c:param>
		</c:url>
		<a href="${up }">上一页</a>
	</c:if>
	<c:if test="${nextPage }">
		<c:url value="/orderhm/caseLotBuys.jspa" var="next">
			<c:param name="startLine" value="${startLine+endLine }"></c:param>
			<c:param name="maxLine" value="${maxLine+1 }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${next }">下一页</a>
		<c:url value="/orderhm/caseLotBuys.jspa" var="end">
			<c:param name="startLine" value="${(totalPage-1)*endLine}"></c:param>
			<c:param name="maxLine" value="${totalPage }"></c:param>
			<c:param name="endLine" value="${endLine }"></c:param>
		</c:url>
		<a href="${end }">尾页</a>
	</c:if>
	共${totalPage }页<br/>
	</c:when>
	<c:when test="${messageError !=null}">
		${messageError }
	</c:when>
	<c:otherwise>
		没有合买信息!<br/>
	</c:otherwise>

</c:choose>