<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东快乐十分</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东快乐十分<br/>
		<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">任选</a>|直选|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkGroupSelf.jspx">组选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br/>
		<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkDirectSelf.jspx">选二连直</a>|前三直选<br/>
		 自选|<a 
		   href="/w/guangDongHappyTenMinutesIndex/directSelectForwardThreeSingleAuto.jspx">单式机选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/directSelectForwardThreeMultipleAuto.jspx">复式机选</a><br/>
		 ${headLine }
 		<form action="/guangDongHappyTenMinutesIndex/directSelectForwardThreeSelf.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
		  
 	从1~20选择，每一位注码个数≥1，号码间无分隔符,10之前号码前面补0,如1要输入01<br/>
 	<c:if test="${not empty messageError }">
 		提示信息：<span style="color: red;"> ${messageError }</span><br/>
 	</c:if>

	<form action="/guangDongHappyTenMinutes/directSelectForwardThreeSelfDetail.jspx" method="post">
	一位: <input type="text" name="first"  maxlength="40" size="20" value="${first }"><br/>
	二位: <input type="text" name="second"  maxlength="40" size="20" value="${second }"><br/>
	三位: <input type="text" name="third"  maxlength="40" size="20" value="${third }"><br/>
	倍数: <input type="text" name="beiShu"  maxlength="2" size="2" value="${beiShu }">倍(最多99倍) <br/>
	追号: <input type="text" name="addNumber"  maxlength="2" size="2" value="${addNumber }">期(最多99期,追1期即买当前期) <br/>
	<input type="hidden" name="type" value="${type }">
	<input type="hidden" name="batchCode" value="${batchCode }">
	<input type="submit"   value="提交投注"> <br/>
	</form>
		
	前三直选：投注号码按顺序与开奖号前3位号码一致，奖金8000元<br/>	
		【最新开奖】<br/>	
	<c:if test="${not empty winCodeList }">
		<c:forEach items="${winCodeList }" var="winCode">
			${winCode }<br/>
		</c:forEach>
	</c:if>
</body>