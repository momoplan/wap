<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东11选5</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东11选5<br/>
		任选|<a 
		   href="/w/guangDongElevenSelectFiveIndex/directSelectForwardTwoSingleSelf.jspx">直选</a>|<a 
		   href= "/w/guangDongElevenSelectFiveIndex/groupSelectForwardTwoSelf.jspx">组选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br/>
		一|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoSelf.jspx">二</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionThreeSelf.jspx">三</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionFourSelf.jspx">四</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionFiveSelf.jspx">五</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionSixSelf.jspx">六</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionSevenSelf.jspx">七</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionEightSelf.jspx">八</a><br/>
		 <a 
		   href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">自选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionOneSingleAuto.jspx">单式机选</a>|复式机选<br/>
		${headLine }
 		<form action="/guangDongElevenSelectFiveIndex/optionOneMultipleAuto.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
	<c:if test="${not empty messageError }">
 		提示信息：<span style="color: red;"> ${messageError }</span><br/>
 	</c:if>
		<form action="/guangDongElevenSelectFive/optionMultipleAutoDetail.jspx" method="post">
		机选: <input name="geShu" type="text"  size="10" maxlength="2"  tabindex="1" value="${geShu }" />个(1-11)<br/>
		倍数: <input type="text" name="beiShu"  maxlength="2" size="2" value="${beiShu }">倍(最多99倍) <br/>
		追号: <input type="text" name="addNumber"  maxlength="2" size="2" value="${addNumber }">期(最多99期,追1期即买当前期) <br/>		
		<input type="hidden" name="type" value="${type }" />
		<input type="submit" value="确定机选" />
		</form><br/>
		任选一：投注号码与开奖号第一位数字相同中13元<br/>	
		【最新开奖】<br/>	
		<c:if test="${not empty winCodeList }">
		<c:forEach items="${winCodeList }" var="winCode">
			${winCode }<br/>
		</c:forEach>
		</c:if>		

</body>