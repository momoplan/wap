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
			投注：${infos.result } <br/>
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
	<c:if test="${changci >4 }">
			<input type="checkbox" name="wanfa"
			value="505" id="" /><span>5串1 </span>
	</c:if>
	<c:if test="${changci >5 }">
			<input type="checkbox" name="wanfa"
			value="506" id="" /><span>6串1 </span>
	</c:if>
	<c:if test="${changci >6 }">
			<input type="checkbox" name="wanfa"
			value="507" id="" /><span>7串1 </span><br/>
	</c:if>
	<c:if test="${changci >7 }">
			<input type="checkbox" name="wanfa"
			value="508" id="" /><span>8串1 </span>
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
	<c:if test="${changci >4 }">
			<input type="radio" name="wanfa"
			value="544" id="" /><span> 5串5 </span>
			<input type="radio" name="wanfa"
			value="545" id="" /><span> 5串6 </span>
			<input type="radio" name="wanfa"
			value="530" id="" /><span>5串10 </span><br/>
			<input type="radio" name="wanfa"
			value="541" id="" /><span>5串16 </span>
			<input type="radio" name="wanfa"
			value="531" id="" /><span>5串20 </span>
			<input type="radio" name="wanfa"
			value="532" id="" /><span>5串26 </span><br/>
	</c:if>
	<c:if test="${changci >5 }">
				<input type="radio" name="wanfa"
			value="549" id="" /><span> 6串6 </span>
			<input type="radio" name="wanfa"
			value="550" id="" /><span> 6串7 </span>
			<input type="radio" name="wanfa"
			value="533" id="" /><span>6串15 </span><br/>
			<input type="radio" name="wanfa"
			value="542" id="" /><span>6串20 </span>
			<input type="radio" name="wanfa"
			value="546" id="" /><span>6串22 </span>
			<input type="radio" name="wanfa"
			value="534" id="" /><span>6串35 </span><br/>
			<input type="radio" name="wanfa"
			value="543" id="" /><span>6串42 </span>
			<input type="radio" name="wanfa"
			value="535" id="" /><span>6串50 </span>
			<input type="radio" name="wanfa"
			value="536" id="" /><span>6串57 </span><br/>
	</c:if>
	<c:if test="${changci >6 }">
			<input type="radio" name="wanfa"
			value="553" id="" /><span> 7串7 </span>
			<input type="radio" name="wanfa"
			value="554" id="" /><span> 7串8 </span>
			<input type="radio" name="wanfa"
			value="551" id="" /><span>7串21 </span><br/>
			<input type="radio" name="wanfa"
			value="547" id="" /><span>7串35 </span>
			<input type="radio" name="wanfa"
			value="537" id="" /><span>7串120</span>
	</c:if>
	<c:if test="${changci >7 }">
			<input type="radio" name="wanfa"
			value="556" id="" /><span> 8串8 </span><br/>
			<input type="radio" name="wanfa"
			value="557" id="" /><span> 8串9 </span>
			<input type="radio" name="wanfa"
			value="555" id="" /><span>8串28 </span>
			<input type="radio" name="wanfa"
			value="552" id="" /><span>8串56 </span><br/>
			<input type="radio" name="wanfa"
			value="548" id="" /><span>8串70 </span>
			<input type="radio" name="wanfa"
			value="538" id="" /><span>8串247</span>
	</c:if>
	</c:if><br/>
	【设置倍数】<br/>
		<input name = "beishu" value="" maxlength="5" size="5"/>倍(最大10000倍)<br/>
		<input type="hidden" name = "lotno" value="${lotno }"/>
		<input type="hidden" name = "bet" value="${bet }"/>
		<input type="submit" value="提交订单" class="btnbg"><br>	
		</form>
		
</c:if>


