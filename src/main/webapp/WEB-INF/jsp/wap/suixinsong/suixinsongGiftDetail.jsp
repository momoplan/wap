<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	<title>双色球赠送明细</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-<a href="/w/wap/suixinsong/suixinsongIndex.jspx">赠送彩票</a>
-${lotType }赠送明细<br/>
			彩种:${lotType }<br/>
			期号:${term }期<br/>
			注数:${zhushu }注<br/>
			倍数:${beishu }倍<br/>
			金额:${amount }元<br/>
			注码:<br/>
			${bet_View }<br/>
			被赠手机号:<br/>
			${newMobileId }
			赠言:<br/>
			${content }
		<form action="/w/suixinsong/doGiftBet.jspa" method="post">
			<br/>
 			<input type="hidden" name="lotType" value="${lotType }"/>
 			<input type="hidden" name="term" value="${term }"/>
 			<input type="hidden" name="zhushu" value="${zhushu }"/>
 			<input type="hidden" name="amount" value="${amount }"/>
 			<input type="hidden" name="to_mobile" value="${to_mobile }"/>
 			<input type="hidden" name="content" value="${content }"/>
 			<input type="hidden" name="betCode" value="${betCode }"/>
 			<input type="hidden" name="beishu" value="${beishu }"/>
 			<input type="hidden" name="lotNo" value="${lotNo }"/>
 			<input type="submit" value="确定赠送"/>
 		</form>
	</body>			