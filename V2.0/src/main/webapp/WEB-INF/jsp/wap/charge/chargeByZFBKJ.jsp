<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>账户充值-支付宝充值</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/charge/chargeIndex.jspx">账户充值</a>-支付宝快捷支付<br/><img src="/w/wap/images/ZFB.png" alt="" ></img><br/>
		（即时入账，无手续费）<br/>
		<c:if test="${not empty messageError }">
			<a style="color: red">${messageError }</a><br/>
		</c:if>
			<form action="/w/chargeWap/chargePayChannelList.jspa" method="POST">
				充值金额(整数):<input name="amount" type="text" maxlength="3" size="3" value="${amount }"/>元<br/>
							  <input type="submit" value="充值" />
			</form><br/>
<br/>提醒:
<br/>1.为保证您的资金安全，银行卡需与支付宝绑定账号
<br/>2.资金将直接从银行卡中充值到如意彩购彩账户
<br/><a href="/w/wap/charge/chargeIndex.jspx">>>更多充值方式选择</a><br/>
</body>