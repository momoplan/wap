<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东快乐十分</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东快乐十分<br/>
		<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">任选</a>|直选|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkGroupSelf.jspx">组选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br/>
		选二连直|<a 
		   href="/w/guangDongHappyTenMinutesIndex/directSelectForwardThreeSelf.jspx">前三直选</a><br/>
		 <a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkDirectSelf.jspx">自选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkDirectSingleAuto.jspx">单式机选</a>|复式机选<br/>
		 ${headLine }
 		<form action="/guangDongHappyTenMinutesIndex/selectTwoLinkDirectMultipleAuto.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
		  
 	<c:if test="${not empty messageError }">
 		提示信息：<span style="color: red;"> ${messageError }</span><br/>
 	</c:if>

	<form action="/guangDongHappyTenMinutes/selectTwoLinkDirectMultipleAutoDetail.jspx" method="post">
	一位: <input type="text" name="first"  maxlength="2" size="2" value="${first }">个(最多20个)<br/>
	二位: <input type="text" name="second"  maxlength="2" size="2" value="${second }">个(最多20个)<br/>
	倍数: <input type="text" name="beiShu"  maxlength="2" size="2" value="${beiShu }">倍(最多99倍) <br/>
	追号: <input type="text" name="addNumber"  maxlength="2" size="2" value="${addNumber }">期(最多99期,追1期即买当前期) <br/>
	<input type="hidden" name="type" value="${type }">
	<input type="hidden" name="batchCode" value="${batchCode }">
	<input type="submit"   value="提交投注"> <br/>
	</form>
		
	选二连直：投注号码按顺序与开奖号连续2位号码一致，奖金62元<br/>	
		【最新开奖】<br/>	
	<c:if test="${not empty winCodeList }">
		<c:forEach items="${winCodeList }" var="winCode">
			${winCode }<br/>
		</c:forEach>
	</c:if>
</body>