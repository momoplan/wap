<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>22选5投注确认</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx">购彩大厅</a>-
<c:if test="${type eq 'DS' }">22选5单式</c:if>
<c:if test="${type eq 'FS' }">22选5复式</c:if>
<c:if test="${type eq 'DT' }">22选5胆拖</c:if>-
确认投注<br/>
您的投注:<br/>
	22选5第${term }期<br/>
	金额:${amount }元<br/>
	注数:${zhushu }注<br/>
	倍数:${beishu }倍<br/>
	追号:${addNumber }期<br/>			
	投注号码:<br/>
		${betCodeView }<br/>
	<form action="/select5from22/betDetail.jspa" method="post">
		<input type="hidden" name="zhushu" value="${zhushu }"/>
		<input type="hidden" name="lotno" value="${lotno }"/>
		<input type="hidden" name="beishu" value="${beishu }"/>
		<input type="hidden" name="amount" value="${amount }"/>
		<input type="hidden" name="betCode" value="${betCode }"/>
		<input type="hidden" name="addNumber" value="${addNumber }"/>
		<input type="hidden" name="token" value="${token }"/>
		<c:if test="${addNumber eq '1'}">
			<p style="color: red;">
			<input type="radio" checked="checked" value="daigou" name="buyways">普通投注
			</p>
			<p style="color: red;">
				<input type="radio" value="presented" name="buyways">我要赠送给他人
			</p>
			<p style="color: red;">
				<input type="radio" value="hemai" name="buyways">发起合买
			</p>
		</c:if>
		<c:if test="${addNumber != '1'}">
			<p style="color: red;">您已经选择了追期，无法应用赠彩、合买功能</p>
			<input type="hidden" name="buyways" value="daigou" />
		</c:if>
		<input type="submit" value="确认投注"/>
	</form><br/>
	<c:if test="${not empty list }">
	【最新开奖】<br/>
		<c:forEach items="${list }" var="lis">
			${lis }<br/>
		</c:forEach>
	</c:if>
</body>
