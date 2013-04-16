<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东11选5</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东11选5<br/>
		<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">任选</a>|直选|<a 
		   href= "/w/guangDongElevenSelectFiveIndex/groupSelectForwardTwoSelf.jspx">组选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br/>
		选前二：<a 
		   href="/w/guangDongElevenSelectFiveIndex/directSelectForwardTwoSingleSelf.jspx">直选单式</a>|<a 
			href="/w/guangDongElevenSelectFiveIndex/directSelectForwardTwoPositionMultipleSelf.jspx">直选定位复式</a><br/>
		选前三：<a href="/w/guangDongElevenSelectFiveIndex/directSelectForwardThreeSingleSelf.jspx">直选单式</a>|直选定位复式 <br/>
		${headLine }
 		<form action="/guangDongElevenSelectFiveIndex/directSelectForwardThreePositionMultipleSelf.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
 		从1~11选择,注码个数>3，号码间无分隔符,10之前号码前面补0,如1要输入01 <br/>
	<c:if test="${not empty messageError }">
 		提示信息：<span style="color: red;"> ${messageError }</span><br/>
 	</c:if>
 		<form action="/guangDongElevenSelectFive/directSelectForwardThreePositionMultipleSelfDetail.jspx" method="post">
 		第一位：<input name="first" type="text"   size="20" maxlength="20"  tabindex="1" value="${first }" /><br/>
 		第二位：<input name="second" type="text"   size="20" maxlength="20"  tabindex="1" value="${second }" /><br/>
 		第三位：<input name="third" type="text"   size="20" maxlength="20"  tabindex="1" value="${third }" /><br/>
 		倍数：<input name="beiShu" type="text"  size="2" maxlength="2"  tabindex="1" value="${beiShu }" />倍(最多99倍)<br/>
 		追号：<input name="addNumber" type="text"   size="2" maxlength="2"  tabindex="1" value="${addNumber }" />期(最多99期,追1期即买当前期)<br/>
 		<input type="hidden" name="type" value="${type }" />
 		<input type="hidden" name="batchCode" value="${batchCode }">
 		<input type="submit" value="提交投注" />
 		</form><br/>
		前三直选:采用开奖号码前三位,奖金1170元<br/>	
		【最新开奖】<br/>	
	<c:if test="${not empty winCodeList }">
		<c:forEach items="${winCodeList }" var="winCode">
			${winCode }<br/>
		</c:forEach>
	</c:if>
</body>