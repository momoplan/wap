<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东快乐十分</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东快乐十分<br/>
		<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">任选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkDirectSelf.jspx">直选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkGroupSelf.jspx">组选</a>|胆拖<br/>
		<a 
		   href="/w/guangDongHappyTenMinutesIndex/optionTwoDanTuoSelf.jspx">任二</a>|任三|<a 
		   href="/w/guangDongHappyTenMinutesIndex/optionFourDanTuoSelf.jspx">任四</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/optionFiveDanTuoSelf.jspx">任五</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkGroupDanTuoSelf.jspx">选二连组</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/groupSelectForwardThreeDanTuoSelf.jspx">前三组选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkDirectDanTuoSelf.jspx">选二连直</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/directSelectForwardThreeDanTuoSelf.jspx">前三直选</a><br/>
		 ${headLine }
 		<form action="/guangDongHappyTenMinutesIndex/optionThreeDanTuoSelf.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
		  
 	从1~20选择，胆码1-2个，胆码+拖码注码个数≥4，号码间无分隔符,10之前号码前面补0,如1要输入01<br/>
 	<c:if test="${not empty messageError }">
 		提示信息：<span style="color: red;"> ${messageError }</span><br/>
 	</c:if>

	<form action="/guangDongHappyTenMinutes/danTuoSelfDetail.jspx" method="post">
	胆码: <input type="text" name="danMa"  maxlength="4" size="20" value="${danMa }"><br/>
	拖码: <input type="text" name="tuoMa"  maxlength="38" size="20" value="${tuoMa }"><br/>
	倍数: <input type="text" name="beiShu"  maxlength="2" size="2" value="${beiShu }">倍(最多99倍) <br/>
	追号: <input type="text" name="addNumber"  maxlength="2" size="2" value="${addNumber }">期(最多99期,追1期即买当前期) <br/>
	<input type="hidden" name="type" value="${type }">
	<input type="hidden" name="batchCode" value="${batchCode }">
	<input type="submit"   value="提交投注"> <br/>
	</form>
		
	任三胆拖：投注号码与开奖号任意3个号码一致，奖金24元<br/>	
		【最新开奖】<br/>	
	<c:if test="${not empty winCodeList }">
		<c:forEach items="${winCodeList }" var="winCode">
			${winCode }<br/>
		</c:forEach>
	</c:if>
</body>