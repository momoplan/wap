<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
<div class="main" >
	<div class="min-head"><img src="/w/m/images/header.gif" width="300" height="50" /></div>
	<div class="OutlookBr">
		<span><img src="/w/m/images/index.gif" width="33" height="42"/></span><span><img src="/w/m/images/nav_arrows.gif" width="16" height="40"  /></span><span>如意彩</span>
	</div>
	<div class="touzhuTop">
		<span class="logo logo3D">&nbsp;</span><span class="logozi">福彩3D</span><span class="return"><a href="/w/handpay/home.jspx" title="返回购彩">返回购彩</a></span>
	</div>
	<div class="touzhuCon">
		<p>天天开奖，中奖率高，免缴税。</p>
		<div class="touzhunav">
			<ul>
				<li><a href="/w/handpay/lottery3DDirect.jspa">直选投注</a></li>
				<li><a href="/w/handpay/lottery3DGroup3Single.jspa">组三单式</a></li>
				<li><a href="/w/handpay/lottery3DGroup6Single.jspa">组六单式</a></li>
				<li><a href="/w/handpay/lottery3DMultiple.jspa">复式投注</a></li>
				<li class="navover"><a href="/w/handpay/lottery3DHeZhi.jspa">和值投注</a></li>
			</ul>
		</div>
	</div>
	<div class="touzhutext">
			<p>
				${deadline}<br />
				和   值:<select name="type" class="selectsmall"><option value="3DDirectHeZhi" selected="selected">直选</option></select><br />（数字0-27中选1个和值,如5）
			</p>
			<form action="/w/handpay/lottery3DHeZhiBetDetail.jspa" method="post">
			<p>
			<span>和值数: <input name="zhuma" type="text" class="inputsmall" maxlength="2" value="${zhuma }"/></span><br />
			<span>倍数:<input name="beishu" type="text" class="inputsmall" maxlength="2" value="${beishu }" />(最多50倍)</span><br />
			<span>追号:<input name="addNumber" type="text" class="inputsmall" maxlength="2" value="${addNumber }"/>期</span><br />
			(最高99期,默认追1期,即买当前期)
			</p>
			<p class="tijiao"><input type="submit" value="提交投注"/> </p>
			</form>
		</div>
	</div>
</body>