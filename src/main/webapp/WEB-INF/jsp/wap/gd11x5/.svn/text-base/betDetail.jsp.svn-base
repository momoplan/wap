<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东11选5确认投注</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东11选5<br/>
${typeName }确认投注<br/>
您的投注:<br/>
	广东11选5第${batchCode }期<br/>
	金额:${amountView }元<br/>
	注数:${zhuShu }注<br/>
	倍数:${beiShu }倍<br/>
	追号:${addNumber }期<br/>			
	投注号码:<br/>
		${betCodeView }<br/>
	<form action="/lottery/betDetail.jspa" method="post">
		<input type="hidden" name="zhuShu" value="${zhuShu }"/>
		<input type="hidden" name="lotNo" value="${lotNo }"/>
		<input type="hidden" name="beiShu" value="${beiShu }"/>
		<input type="hidden" name="amount" value="${amount }"/>
		<input type="hidden" name="oneAmount" value="${oneAmount }"/>
		<input type="hidden" name="betCode" value="${betCode }"/>
		<input type="hidden" name="addNumber" value="${addNumber }"/>
		<input type="hidden" name="batchCode" value="${batchCode }"/>
		<input type="hidden" name="token" value="${token }"/>
		<input type="hidden" name="buyWays" value="daiGou"/>
		<input type="submit" value="确认投注"/>
	</form><br/>
	【最新开奖】<br/>
	<c:if test="${not empty winCodeList }">
		<c:forEach items="${winCodeList }" var="winCode">
			${winCode }<br/>
		</c:forEach>
	</c:if>
</body>
