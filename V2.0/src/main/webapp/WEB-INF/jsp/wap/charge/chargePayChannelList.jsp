<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<title>账户充值--支付宝充值</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/charge/chargeIndex.jspx">账户充值</a>-支付宝快捷支付<br/><img src="/w/wap/images/ZFB.png" alt="" ></img><br/>
		（即时入账，无手续费）<br/>
		充值金额:${amount }元<br/>
		<c:if test="${not empty lastestPayChannel }">
		近期使用:<form action="<%=request.getContextPath() %>/chargeWap/chargeZFBKJWap.jspa" method="POST">
						<input type="hidden" name="amount" value="${amount }">
						<input type="hidden" name="checkbox" value="${checkbox }">
						<input type="hidden" name="cashierCode" value="${lastestPayChannel.cashierCode }">
						<input type="hidden" name="name" value="${lastestPayChannel.name }">
						<input type="submit" value="${lastestPayChannel.name }" />
					</form><br/>
		</c:if>
		<c:if test="${not empty supportTopPayChannelList }">
			<c:forEach items="${supportTopPayChannelList }" var="supportTopPayChannel" varStatus="supportTopPayChannelIndex">
				<c:if test="${supportTopPayChannelIndex.index ==0}">①</c:if>
				<c:if test="${supportTopPayChannelIndex.index ==1}">②</c:if>			
				${supportTopPayChannel.name }<br/>
				<c:forEach items="${supportTopPayChannel.supportSecPayChannelList }" var="supportSecPayChannel" varStatus="supportSecPayChannelIndex">
					<form action="<%=request.getContextPath() %>/chargeWap/chargeZFBKJWap.jspa" method="POST">
						<input type="hidden" name="amount" value="${amount }">
						<input type="hidden" name="checkbox" value="${checkbox }">
						<input type="hidden" name="cashierCode" value="${supportSecPayChannel.cashierCode }">
						<input type="hidden" name="name" value="${supportSecPayChannel.name }">
						<c:choose>
							<c:when test="${supportSecPayChannelIndex.index+1 == fn:length(supportTopPayChannel.supportSecPayChannelList) }">
								<input type="submit" value="${supportSecPayChannel.name }银行  " class="btnbg"/>
							</c:when>
							<c:when test="${supportTopPayChannelIndex.index ==0}">
								<input type="submit" value="${supportSecPayChannel.name }信用卡" class="btnbg"/>
							</c:when>
							<c:when test="${supportTopPayChannelIndex.index ==1}">
								<input type="submit" value="${supportSecPayChannel.name }储蓄卡" class="btnbg"/>
							</c:when>
						</c:choose>
					</form><br/>
				</c:forEach>
			
			</c:forEach>
		</c:if>
<a href="/w/wap/charge/chargeIndex.jspx">充值首页</a>
</body>