<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4><a href="/w/wap/wapindex.jspx">首页</a>-<a
	href="/w/orderhm/caseLotLottery.jspx">彩票合买</a>-参与合买</h4>
【方案详情】<br/>
<c:if test="${messageError !=null }">
<a style="color: red">${messageError }</a><br/>
</c:if>
<c:if test="${caseLot !=null }">
发起人:${caseLot.starter }<c:if test="${caseLot.starterFlag eq 'true'&&caseLot.cancelFlag eq 'true'}">
	<form action="/w/orderhm/removeCase.jspa"  method="post">
		<input type="hidden" name="caseLotId" value="${caseLot.caseLotId }"/>
		<input type="submit" style="background-color: red;color: white" value="撤单"/>
	</form>
</c:if><br/>
战绩:${caseLot.achievement}<br/>
彩种:${caseLot.lotName }<br/>
<c:if test="${caseLot.batchCode != ''&&caseLot.batchCode != 'null' }">
期号:${caseLot.batchCode }<br/>
</c:if>
方案金额:￥<a style="color: red">${caseLot.totalAmt}</a><br/>
最低认购:￥<a style="color: red">${caseLot.minAmt }</a><br/>
-----------------------<br/>
方案进度:${caseLot.progress }<br/>
发起人提成:${caseLot.commisionRatio }<br/>
<c:if test="${caseLot.description !=null&&caseLot.description != ''}">
方案描述:${caseLot.description}<br/>
</c:if>
<c:if test="${caseLot.batchCode != ''&&caseLot.batchCode != 'null' }">
玩法类型:${caseLot.betType }<br/>
</c:if>
方案内容:<br/>
<c:choose>
		<c:when test="${caseLot.displayTlots eq 'false' }">
			${caseLot.visibility }
		</c:when>
		<c:otherwise>
			${caseLot.betCode }<br/>
		</c:otherwise>
	
	</c:choose>
</c:if>

<c:if test="${caseLot.winbasecode != '' }">
-----------------------<br/>
方案中奖总金额:￥<a style="color: red">${caseLot.winBigAmt }</a><br/>
我的中奖金额:￥<a style="color: red">${caseLot.userPrizeAmt }</a><br/>
方案开奖号码:${caseLot.winbasecode }<br/>
</c:if>
-----------------------<br/>
我的认购:￥<a style="color: red">${caseLot.userTotalAmt }</a><br/>
我的保底:￥<a style="color: red">${caseLot.userSafeAmt }</a><br/>
-----------------------<br/>
参与人<br/>
<c:if test="${caseLots !=null }">
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
