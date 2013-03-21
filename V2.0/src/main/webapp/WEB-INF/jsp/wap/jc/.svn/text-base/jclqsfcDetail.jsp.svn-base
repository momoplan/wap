<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="<%=request.getContextPath() %>/wap/wapindex.jspx">首页</a>-<a href="<%=request.getContextPath() %>/wap/buyLottery.jspx">购彩大厅</a>-<a href="<%=request.getContextPath() %>/wap/jc/jclqIndex.jspx">竞彩篮球</a>
<br/>
<h4>竞彩篮球-<c:if test="${lotno eq 'BSK001'}">胜负</c:if><c:if test="${lotno eq 'BSK002'}">让分胜负</c:if> 
<c:if test="${lotno eq 'BSK003'}">胜分差</c:if><c:if test="${lotno eq 'BSK004'}">大小分</c:if></h4>
<div class="message">
	<span style="color: blue;"> ${message}</span>
</div>
【方案内容】
<c:if test="${not empty listMap }">
	<form action="<%=request.getContextPath() %>/jc/jingcaiDetail.jspx" method="post">
	<c:forEach items="${listMap }" var="infos">
	<div class="zucai_tr1">
		<div>${infos.newweek }${infos.teamid } <a style="color: red"><b>${infos.league} </b></a></div>
		<div class="greenbg">
			${infos.team } <br/>
			投注：<br/>${infos.result } <br/>
		</div>
	</div><div class="jc_15"></div>
	</c:forEach>
	【请选择过关类型】<br/>
	<c:if test="${type eq 'ziyou' }">
	<c:if test="${changci >1 }">
			<input type="checkbox" name="wanfa"
			value="502" id="" /><span>2串1 </span>
	</c:if>
	<c:if test="${changci >2 }">
			<input type="checkbox" name="wanfa"
			value="503" id="" /><span>3串1 </span>
	</c:if>
	<c:if test="${changci >3 }">
			<input type="checkbox" name="wanfa"
			value="504" id="" /><span>4串1 </span><br/>
	</c:if>
	</c:if>
	<c:if test="${type eq 'duochuan' }">
		<c:if test="${changci >2 }">
			<input type="radio" name="wanfa"
			value="526" id="" /><span> 3串3 </span>
			<input type="radio" name="wanfa"
			value="527" id="" /><span> 3串4 </span>
	</c:if>
	<c:if test="${changci >3 }">
			<input type="radio" name="wanfa"
			value="539" id="" /><span> 4串4 </span><br/>
			<input type="radio" name="wanfa"
			value="540" id="" /><span> 4串5 </span>
			<input type="radio" name="wanfa"
			value="528" id="" /><span> 4串6 </span>
			<input type="radio" name="wanfa"
			value="529" id="" /><span>4串11 </span><br/>
	</c:if>
	
	</c:if><br/>
	【设置倍数】<br/>
		<input name = "beishu" value="" maxlength="5" size="5"/>倍(最大10000倍)<br/>
		<input type="hidden" name = "lotno" value="${lotno }"/>
		<input type="hidden" name = "bet" value="${bet }"/>
		<input type="submit" value="提交订单" class="btnbg"><br>	
		</form>
		
</c:if>


