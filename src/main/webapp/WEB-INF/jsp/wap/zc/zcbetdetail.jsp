<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx"><%
			out.print(CommonUtil.printHall());
		%></a><%
		out.print(CommonUtil.printChar());
		%><a href="/wap/zc/zcIndex.jspx">足彩</a>
		<c:if test="${lotno=='T01003'}">
		<%
			out.print(CommonUtil.printChar());
		%>胜负彩
		</c:if>
		<c:if test="${lotno=='T01004'}">
		<%
			out.print(CommonUtil.printChar());
		%>任选九场
		</c:if>
		<c:if test="${lotno=='T01005'}">
		<%
			out.print(CommonUtil.printChar());
		%>四场进球
		</c:if>
		<c:if test="${lotno=='T01006'}">
		<%
			out.print(CommonUtil.printChar());
		%>六场半
		</c:if>
<div><a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01003">胜负彩</a>|
 <a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01004">任选九场</a>
|<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01006">六场半</a>
|<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01005">四场进球</a>
</div>
	<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/zc/zcbet.jspa" method="post">
	【方案内容】<br>
	<c:forEach items="${duizhenInfos }" var="infos">
	   ${infos}<br>
	</c:forEach>
	【确认订单】<br>
	彩种玩法：${lotname}<br>
	倍数：${beishu }<br>
	注数：${zhushu }<br>	
          金额：${amt }<br>
          <p style="color: red;">
			<input type="radio" checked="checked" value="daigou" name="buyways">普通投注
		</p>
		<p style="color: red;">
			<input type="radio" value="hemai" name="buyways">发起合买
		</p><br/>
	<input type="hidden" name="betcode" value="${betcode }">
	<input type="hidden" name="beishu" value="${beishu }">
	<input type="hidden" name="token" value="${token }"/>
	<input type="hidden" name="amt" value="${amt}">
	<input type="hidden" name="lotno" value="${lotno}">
	<input type="hidden" name="wanfa" value="${wanfa}">
	<input type="hidden" name="zhushu" value="${zhushu}">
	<input type="hidden" name="batchcode" value="${batchcode}">
	<input type="submit" value="提交订单">
</form>
		
</c:if>
