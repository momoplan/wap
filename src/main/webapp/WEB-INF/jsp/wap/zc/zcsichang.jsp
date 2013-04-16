<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx"><%
			out.print(CommonUtil.printHall());
		%></a><%
		out.print(CommonUtil.printChar());
		%><a href="/wap/zc/zcIndex.jspx">足彩</a><%
			out.print(CommonUtil.printChar());
		%>四场进球
<div><a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01003">胜负彩</a>
|<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01004">任选九场</a>
|<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01006">六场半</a>
|四场进球
</div>
<div>
<c:if test="${not empty batchs.bactch0}">
 <a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01005">当前${batchs.bactch0}期</a>
 </c:if>
 <c:if test="${not empty batchs.bactch1}">
 |<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01005&batchcode=${batchs.bactch1}&overtime=${batchs.bactch1endtime}">当前${batchs.bactch1}期</a>
 </c:if>
 <c:if test="${not empty batchs.bactch2}">
 |<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01005&batchcode=${batchs.bactch2}&overtime=${batchs.bactch1endtime}">当前${batchs.bactch2}期</a>
 </c:if>
 <c:if test="${not empty batchs.bactch3}">
 |<a href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01005&batchcode=${batchs.bactch3}&overtime=${batchs.bactch1endtime}">当前${batchs.bactch3}期</a>
 </c:if>
 <br>
<c:if test="${batchcode == null}">
   当前${batchs.bactch0}期距截止${batchs.bactch0endtime}<br>
</c:if>
<c:if test="${batchcode != null}">
当前${batchcode}期距截止${bactchendtime}<br>
</c:if>
 ------------------------
 </div>
<div>
   *0为没有进球、1为进1球、2为进2球、3为进3球或以上
</div>

	<c:if test="${not empty duizhenInfos }">
	<form action="<%=request.getContextPath() %>/zc/zcbetdetail.jspx" method="post">
	<c:forEach items="${duizhenInfos }" var="infos">
	<div class="zucai_tr1">
		<div class="greenbg">
			${infos.tempId}.[${infos.name}] <br>
			${infos.HTeam }:<input type="text" class="team" name="HTeam${infos.tempId}" maxlength="4" size="30"><br>
            ${infos.VTeam} :<input type="text" class="team" name="VTeam${infos.tempId}" maxlength="4" size="30">
		</div>
		<span>均值：${infos.avgOdds}</span>
	</div>
		<input type="hidden" name="duizhens${infos.tempId}" value="${infos.tempId}|[${infos.name}]|${infos.HTeam }|${infos.VTeam}"/>
		<input type="hidden" name="lotno" value="T01005">
		<div class="jc_15"></div>
	</c:forEach>

		 <c:if test="${batchcode == null}">
	         <input type="hidden" name="batchcode" value="${batchs.bactch0 }">
	    </c:if>
	    <c:if test="${batchcode != null}">
	   		 <input type="hidden" name="batchcode" value="${batchcode }">
		</c:if>
		【设置倍数】<br>
	<input name="beishu" value="" type="text" maxlength="5">倍(最大10000倍)<br>
	<input type="submit" value="提交订单">
</form>
		
</c:if>
