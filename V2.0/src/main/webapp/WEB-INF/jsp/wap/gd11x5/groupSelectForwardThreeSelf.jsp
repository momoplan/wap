<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东11选5</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东11选5<br/>
		<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">任选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/directSelectForwardTwoSingleSelf.jspx">直选</a>|组选|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br/>
		<a 
		   href="/w/guangDongElevenSelectFiveIndex/groupSelectForwardTwoSelf.jspx">前二组选</a>|前三组选<br/>
		自选|<a 
		   href="/w/guangDongElevenSelectFiveIndex/groupSelectForwardThreeSingleAuto.jspx">单式机选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/groupSelectForwardThreeMultipleAuto.jspx">复式机选</a><br/>
		 ${headLine }
 		<form action="/guangDongElevenSelectFiveIndex/groupSelectForwardThreeSelf.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
		  
 	从1~11选择,注码个数≥3，号码间无分隔符,10之前号码前面补0,如1要输入01 <br/>
	<c:if test="${not empty messageError }">
 		提示信息：<span style="color: red;"> ${messageError }</span><br/>
 	</c:if>
	<form action="/guangDongElevenSelectFive/optionSelfDetail.jspx" method="post">
	注码: <input type="text" name="zhuMa"  maxlength="22" size="22" value="${zhuMa }"><br/>
	倍数: <input type="text" name="beiShu"  maxlength="2" size="2" value="${beiShu }">倍(最多99倍) <br/>
	追号: <input type="text" name="addNumber"  maxlength="2" size="2" value="${addNumber }">期(最多99期,追1期即买当前期) <br/>
	<input type="hidden" name="type" value="${type }">
	<input type="hidden" name="batchCode" value="${batchCode }">
	<input type="submit"   value="提交投注"> <br/>
	</form>
		
	前三组选:与开奖号码的前三位一致（不限顺序）即中195元<br/>	
		【最新开奖】<br/>	
	<c:if test="${not empty winCodeList }">
		<c:forEach items="${winCodeList }" var="winCode">
			${winCode }<br/>
		</c:forEach>
	</c:if>
</body>