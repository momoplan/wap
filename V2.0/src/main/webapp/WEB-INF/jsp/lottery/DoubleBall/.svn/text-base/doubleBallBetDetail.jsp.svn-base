<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
<div class="main" >
	<div class="min-head"><img src="/w/m/images/header.gif" width="300" height="50" /></div>
	<div class="OutlookBr">
		<span><img src="/w/m/images/index.gif" width="33" height="42"/></span><span><img src="/w/m/images/nav_arrows.gif" width="16" height="40"  /></span><span>如意彩</span>
	</div>
	<div class="clear10">&nbsp;</div>
	<div class="nav">
		<dl>
			<dt><a href="/w/handpay/home.jspx" title="购彩">购彩</a></dt>
			<dd ><a href="/w/handpay/drawLottery.jspx">开奖</a></dd>
			<dd><a href="/w/handpay/drawCashIndex.jspa">提现</a></dd>
			<dd class="nav_over"><a href="/w/handpay/more.jspx" title="更多">更多</a></dd>
		</dl>
	</div>
	<div class="account">确认投注</div>
    <div class="lottoryaccount">
	<p>
	您的投注:<br />
	双色球第${term }期<br />
	金额:${amount }元<br />
	注数:${zhushu }注<br />
	倍数:${beishu }倍<br />
	追号:${addNumber }期<br />
	投注号码:<br />
	${bet_View}
	</p>
	<form action="/w/handpay/lotteryDoubleBallBet.jspa" method="post">
	<input name="beishu" type="hidden" value="${beishu }"/>
	<input name="term" type="hidden" value="${term }"/>
	<input name="amount" type="hidden" value="${amount }"/>
	<input name="zhushu" type="hidden" value="${zhushu }"/>
	<input name="addNumber" type="hidden" value="${addNumber }"/>
	<input name="bet_View" type="hidden" value="${bet_View }"/>
	<input name="betCode" type="hidden" value="${betCode }"/>
	<input name="type" type="hidden" value="${type }"/>
	<p class="tijiao"><input type="submit" value="确认投注"/></p>
	</form>
	
	
</div>
</div>
</body>