<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
<div class="main" >
	<div class="min-head"><img src="/w/m/images/header.gif" width="300" height="50" /></div>
	<div class="OutlookBr">
		<span><img src="/w/m/images/index.gif" width="33" height="42"/></span><span><img src="/w/m/images/nav_arrows.gif" width="16" height="40"  /></span><span>如意彩</span>
	</div>
	<div class="touzhuTop">
		<span class="logo logossq">&nbsp;</span><span class="logozi">双色球</span><span class="return"><a href="/w/handpay/home.jspx" title="返回购彩">返回购彩</a></span>
	</div>
	<div class="touzhuCon">
		<p>最受欢迎，销量最高，2元赢取1000万。</p>
		<div class="touzhunav">
			<ul>
				<li ><a href="/w/handpay/lotteryDoubleBallSelfSingle.jspa">单式自选</a></li>
				<li><a href="/w/handpay/lotteryDoubleBallAutoSingle.jspa">单式机选</a></li>
				<li class="navover"><a href="/w/handpay/lotteryDoubleBallSelfMultiple.jspa">复式自选</a></li>
				<li><a href="/w/handpay/lotteryDoubleBallAutoMultiple.jspa">复式机选</a></li>
			</ul>
		</div>
	</div>
	<div class="touzhutext">
			<p>
				${deadline }<br />
				红复式:可选7-20个红球和1个篮球<br />
				蓝复式:可选6个红球和2-16个篮球<br />
				红蓝复式:可选7-20个红球和2-16篮球</p>
			<form action="/w/handpay/lotteryDoubleBallSelfMultipleBetDetail.jspa" method="post">
			<p>
			<span>红球:<input name="redBall" type="text" class="inputsmall" value="${redBall }"/>(如:010212152233)</span><br />
			<span>篮球:<input name="blueBall" type="text" class="inputsmall" value="${blueBall }"/>(如:0709)</span><br />
			<span>倍数:<input name="beishu" type="text" class="inputsmall" value="${beishu }"/>(最多50倍)</span><br />
			<span>追号:<input name="addNumber" type="text" class="inputsmall" value="${addNumber }"/>期</span><br />
			(最高99期,默认追1期,即买当前期)
			<input name="type" type="hidden" value="DoubleBallSelfMultiple"/>
			</p>
			<p class="tijiao"><input type="submit" value="提交投注"/></p>
			</form>
		</div>
	</div>
</body>