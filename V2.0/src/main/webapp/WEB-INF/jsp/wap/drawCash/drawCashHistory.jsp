<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>账户提现</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a
   href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-账户提现<br/>
   
  
 <a href="/w/drawCash/findDNAtoCash.jspa">账户提现</a>|查看提现记录<br/>
   <c:choose>
   		 <c:when test="${not empty drawCashList}">
   		 	<c:forEach items="${drawCashList }" var="cash">
   		 	${cash.plattime } 提现￥${cash.amt }<br/>
   		 	<c:if test="${cash.state eq '1' }">
   		 	待审核 <a href="/w/drawCash/cancelDrawCash.jspa?id=${cash.id }">撤销提现</a><br/>
   		 	</c:if>
   		 	<c:if test="${cash.state eq '103' }">
   		 	已审核<br/>
   		 	</c:if>
   		 	<c:if test="${cash.state eq '105' }">
   		 	成功<br/>
   		 	</c:if>
   		 	<c:if test="${cash.state eq '106' }">
   		 	取消<br/>
   		 	</c:if>
   		 	<c:if test="${cash.state eq '104' }">
   		 	驳回 
   		 	<c:choose>
   		 		<c:when test="${cash.rejectreason eq 'null' }"></c:when>
   		 		<c:otherwise>
   		 			${cash.rejectreason }
   		 		</c:otherwise>
   		 	</c:choose><br/>
   		 	</c:if>
   		 	</c:forEach>
   		 	<c:if test="${upPage }">
		<c:url value="/drawCash/drawCashHistory.jspa" var="start">
			<c:param name="pageIndex" value="0"></c:param>
		</c:url>
		<a href="${start }">首页</a>
		<c:url value="/drawCash/drawCashHistory.jspa" var="up">
			<c:param name="pageIndex" value="${pageIndex-1}"></c:param>
		</c:url>
		<a href="${up }">上一页</a>
	</c:if>
	<c:if test="${nextPage }">
		<c:url value="/drawCash/drawCashHistory.jspa" var="next">
			<c:param name="pageIndex" value="${pageIndex+1 }"></c:param>
		</c:url>
		<a href="${next }">下一页</a>
		<c:url value="/drawCash/drawCashHistory.jspa" var="end">
			<c:param name="pageIndex" value="${totalPage-1}"></c:param>
		</c:url>
		<a href="${end }">尾页</a>
	</c:if>
	共${totalPage }页<br/>
   		 </c:when>
   		 <c:otherwise>
   			没有提现记录<br/>
     	 </c:otherwise>
   </c:choose>
  
提款须知： <br/>   
	（1）提现记录中的字段分别为：提现时间、提现金额、提现状态、操作（web上有订单号）；提现状态有五种，分别为：待审核、已审核、驳回、成功、取消；待审核状态的提现可以做撤销操作，被驳回的提现会以短信的形式通知用户。 <br/> 
	（2）提现金额精确到分，如可以提现14.59元或1.58元。 <br/> 
	（3）可提现金额小于10元时，申请提现时，需要一次性提清。 <br/> 
	     提现只能提到银行卡上，暂不支持信用卡提现。 <br/> 
	“为了防止少数用户利用信用卡套现和洗钱行为，保证正常用户的资金安全，本软件针对提款做出以下规定：累计充值资金消费未满30%，可提现金额为累计充值资金的70%；累计充值资金消费达到30%，不受此限制。” <br/> 
	（4）提现没有次数限制。 <br/> 
	（5）提现受理银行有：中国工商银行、中国农业银行、中国建设银行、中国民生银行、招商银行、中国邮政储蓄银行、交通银行、兴业银行、中信银行、中国光大银行、广东发展银行、上海浦东发展银行、深圳发展银行、杭州银行，共14家。<br />

<br />
</body>
