<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h4><a href="/w/wap/wapindex.jspx">首页</a>-<a
	href="/w/orderhm/caseLotLottery.jspx">合买大厅</a>-参与合买</h4>
【方案详情】<br/>
<c:if test="${messageError !=null }">
<a style="color: red">${messageError }</a><br/>
</c:if>

<c:choose>
	<c:when test="${not empty caseLot}">
		彩种:${caseLot.lotName }<br/>
		<c:if test="${caseLot.batchCode != 'null' }">
				期号:${caseLot.batchCode }<br/>
		</c:if>
		发起人:${caseLot.starter }<c:if test="${caseLot.starterFlag eq 'true'&&caseLot.cancelFlag eq 'true'}">
	<form action="/w/orderhm/removeCase.jspa"  method="post">
		<input type="hidden" name="caseLotId" value="${caseLot.caseLotId }"/>
		<input type="submit" style="background-color: red;color: white" value="撤单"/>
	</form>
	
</c:if>
<br/>
		方案编号:${caseLot.caseLotId }<br/>
		方案金额:${caseLot.totalAmt }元<br/>
		最低金额:${caseLot.minAmt }元<br/>
		保底金额:${caseLot.safeAmt }元<br/>
		方案进度:${caseLot.progress }<br/>
		发起人认购:${caseLot.buyAmtByStarter }元<br/>
		发起人提成:${caseLot.commisionRatio }<br/>
		<c:if test="${caseLot.description !=null&&caseLot.description != ''}">
		方案描述:${caseLot.description}<br/>
		</c:if>
		<c:if test="${caseLot.batchCode == 'null' }">
				玩法类型:${caseLot.betType }<br/>
		</c:if>
【方案内容】<br/>
	<c:choose>
		<c:when test="${caseLot.displayTlots eq 'false' }">
			${caseLot.visibility }
		</c:when>
		<c:otherwise>
			${caseLot.betCode }<br/>
		</c:otherwise>
	
	</c:choose>
<c:if test="${caseLots !=null }">
	【参与人员】<br/>
	<table>
	<c:forEach items="${caseLots}" var="case">
		
			<tr>
				<td>[${case.buyName}]</td>
				<td>
				<c:if test="${case.joinFlag eq 'true'&&case.divestmentFlag eq 'true'}">
					<form action="/w/orderhm/cancelCaselotbuy.jspa"  method="post">
						<input type="hidden" name="caseLotId" value="${caseLot.caseLotId }"/>
						<input type="submit" style="background-color: red;color: white" value="撤资"/>
					</form>
				</c:if>
				</td>
				<td>${case.buyTime}</td>
				<td>
				￥${case.buyAmt }+${case.safeAmt }(保)
				</td>
			</tr>
	</c:forEach>	
	</table>
</c:if>
	【认购方案】<br/>
	<c:if test="${login eq 'NO' }">
		您尚未登录，请先<a href="/w/wap/userinfo/login.jspx">登录</a><br/>
	</c:if>
	<form action="/w/orderhm/joinCaseLotDetail.jspx" method="post">
	我要认购
	<c:choose>
		<c:when test="${joinAmt ==null}">
			<input type="text" name="joinAmt" value="0"/><br/>
		</c:when>
		<c:otherwise>
			<input type="text" name="joinAmt" value="${joinAmt }"/><br/>
		</c:otherwise>
	</c:choose>
	(剩余${caseLot.betAmt}元可认购)<br/>
	我要保底
	<c:choose>
		<c:when test="${joinBaodiAmt ==null}">
			<input type="text" name="joinBaodiAmt" value="0"/><br/>
		</c:when>
		<c:otherwise>
			<input type="text" name="joinBaodiAmt" value="${joinBaodiAmt }"/><br/>
		</c:otherwise>
	</c:choose>

	<input type="hidden" name="caseLotId" value="${caseLot.caseLotId }"/>
	<input type="hidden" name="betAmt" value="${caseLot.betAmt }"/>
	<input type="hidden" name="baodiAmt" value="${caseLot.baodiAmt }"/>
	<input type="hidden" name="minAmt" value="${caseLot.minAmt }"/>
	<input type="submit" value="立即认购"/>
	</form>
	
	</c:when>
	<c:otherwise>
	没有合买信息!<br/>
	</c:otherwise>

</c:choose>