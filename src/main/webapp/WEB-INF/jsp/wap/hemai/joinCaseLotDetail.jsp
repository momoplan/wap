<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4><a href="/wap/wapindex.jspx">首页</a>-<a
	href="/w/orderhm/caseLotLottery.jspx">合买大厅</a>-参与合买</h4>
【确定购买】<br/>
我要认购:${joinAmt }元<br/>
我要保底:${joinBaodiAmt }元<br/>
<form action="/w/orderhm/joinCasLotBetDetail.jspa" method="post">
	<input type="hidden" name="joinAmt" value="${joinAmt }"/>
	<input type="hidden" name="joinBaodiAmt" value="${joinBaodiAmt }"/>
	<input type="hidden" name="caseLotId" value="${caseLotId }"/>
	<input type="submit" value="确定"/>
</form>
