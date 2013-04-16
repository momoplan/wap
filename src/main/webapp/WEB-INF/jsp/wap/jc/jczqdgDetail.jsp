<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="<%=request.getContextPath() %>/wap/wapindex.jspx">首页</a>-<a href="<%=request.getContextPath() %>/wap/buyLottery.jspx">购彩大厅</a>-<a href="<%=request.getContextPath() %>/wap/jc/jczqIndex.jspx">竞彩足球</a>
<br/>
<h4>竞彩足球-<c:if test="${lotno eq 'FT001'}">胜平负</c:if><c:if test="${lotno eq 'FT002'}">比分</c:if> 
<c:if test="${lotno eq 'FT003'}">总进球</c:if><c:if test="${lotno eq 'FT004'}">半场胜平负</c:if></h4>
<div class="message">
	<span style="color: blue;"> ${message}</span>
</div>
【方案内容】
<c:if test="${not empty listMap }">
	<form action="<%=request.getContextPath() %>/jc/jczqdgBetDetail.jspx" method="post">
	<c:forEach items="${listMap }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid } <a style="color: red"><b>${infos.league} </b></a></div>
		<div class="greenbg">
			${infos.team } <br/>
			投注：<br/>${infos.result } <br/>
		</div>
	</div><div class="jc_15"></div>
	</c:forEach>
	【过关方式】<br/>
	<input type="checkbox" name="wanfa"
			value="500" onclick="return false;" checked/><span>单关</span>
	<br/>
	【设置倍数】<br/>
		<input name = "beishu" value="" maxlength="5" size="5"/>倍(最大10000倍)<br/>
		<input type="hidden" name = "lotno" value="${lotno }"/>
		<input type="hidden" name = "bet" value="${bet }"/>
		<input type="submit" value="提交订单" class="btnbg"><br>	
		</form>
		
</c:if>


