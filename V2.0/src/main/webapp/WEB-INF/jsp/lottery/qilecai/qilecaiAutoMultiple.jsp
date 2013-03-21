<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
<div class="main" >
	<div class="min-head"><img src="/w/m/images/header.gif" width="300" height="50" /></div>
	<div class="OutlookBr">
		<span><img src="/w/m/images/index.gif" width="33" height="42"/></span><span><img src="/w/m/images/nav_arrows.gif" width="16" height="40"  /></span><span>如意彩</span>
	</div>
	<div class="touzhuTop">
		<span class="logo logoqlc">&nbsp;</span><span class="logozi">七乐彩</span><span class="return"><a href="/w/handpay/home.jspx" title="返回购彩">返回购彩</a></span>
	</div>
	<div class="touzhuCon">
		<p>百万大奖，每期都有！号称百万大奖生产线。</p>
		<div class="touzhunav">
			<ul>
				<li><a href="/w/handpay/lotteryQiLeCaiSelfSingle.jspa">单式自选</a></li>
				<li><a href="/w/handpay/lotteryQiLeCaiAutoSingle.jspa">单式机选</a></li>
				<li><a href="/w/handpay/lotteryQiLeCaiSelfMultiple.jspa">复式自选</a></li>
				<li class="navover"><a href="/w/handpay/lotteryQiLeCaiAutoMultiple.jspa">复式机选</a></li>
			</ul>
		</div>
	</div>
	<div class="touzhutext">
			<p>
				${deadline }<br /></p>
				<form action="/w/handpay/lotteryQiLeCaiAutoMultipleBetDetail.jspa" method="post">
				<p>
			<span>选择号码个数:<select name="geshu" class="selectsmall">
			<option value="8">8个</option>
			<option value="9">9个</option>
			<option value="10">10个</option>
			<option value="11">11个</option>
			<option value="12">12个</option>
			<option value="13">13个</option>
			<option value="14">14个</option>
			<option value="15">15个</option>
			<option value="16">16个</option>
			</select></span><br />
			<span>倍数:<input name="beishu" type="text" class="inputsmall" value="${beishu }"/>(最多50倍)</span><br />
			<span>追号:<input name="addNumber" type="text" class="inputsmall" value="${addNumber }"/>期</span><br />
			(最高99期,默认追1期,即买当前期)
			<input name="type" type="hidden" value="QiLeCaiAutoMultiple"/>
			</p>
			<p class="tijiao"><input type="submit" value="提交投注"/> </p>
			</form>
  </div>
</div>
</body>
</body> 