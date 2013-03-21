<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>广东11选5</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-广东11选5<br/>
		<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">任选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/directSelectForwardTwoSingleSelf.jspx">直选</a>|<a 
		   href= "/w/guangDongElevenSelectFiveIndex/groupSelectForwardTwoSelf.jspx">组选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoDanTuoSelf.jspx">胆拖</a><br/>
		<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoDanTuoSelf.jspx">任二</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionThreeDanTuoSelf.jspx">任三</a>|任四|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionFiveDanTuoSelf.jspx">任五</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionSixDanTuoSelf.jspx">任六</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionSevenDanTuoSelf.jspx">任七</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/groupSelectForwardTwoDanTuoSelf.jspx">前二组选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/groupSelectForwardThreeDanTuoSelf.jspx">前三组选</a><br/>
		${headLine }
 		<form action="/guangDongElevenSelectFiveIndex/optionFourDanTuoSelf.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
		  
 	胆码限选1-3个,拖码2~10，不与胆码重复,至少选择2注 <br/>
	<c:if test="${not empty messageError }">
 		提示信息：<span style="color: red;"> ${messageError }</span><br/>
 	</c:if>
	<form action="/guangDongElevenSelectFive/danTuoSelfDetail.jspx" method="post"> 
           胆码:<input name="danMa" type="text" size="20" value="${danMa }" /><br /> 
	拖码:<input name="tuoMa" type="text" size="20" value="${tuoMa}" /><br /> 
	倍数:<input name="beiShu"type="text" maxlength="2" size="2" value="${beiShu }" />(最多99倍)<br /> 
	 追号:<input name="addNumber" type="text" maxlength="2"	size="2" value="${addNumber }" />期<br />(最高99期,默认追1期,即买当前期)<br /> 
	 <input type="hidden" name="type" value="${type }" />
	 <input type="submit" value="提交投注" /> </form> <br/>
		【最新开奖】<br/>
		<c:if test="${not empty winCodeList }">
			<c:forEach items="${winCodeList }" var="winCode">
				${winCode }<br/>
			</c:forEach>
		</c:if>
</body>