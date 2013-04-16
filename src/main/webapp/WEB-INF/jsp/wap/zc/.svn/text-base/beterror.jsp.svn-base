<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String URL = request.getRequestURI() ;
%>

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
投注错误提示：<br>
<c:if test="${ not empty code_msg}">
<div class="jc_15"></div>
错误注码：<br>
     <div class="message">
   	<span style="color: blue;"> ${code_msg}</span>
</div>
</c:if>
<c:if test="${ not empty message}">
<div class="jc_15"></div>
     <div class="message">
		<span style="color: red;"> ${message}</span>
</div>
</c:if>
<div class="jc_15"></div>
<div>
    <a href="<%=request.getHeader("Referer") %>">返回对阵，重新填写</a>
</div>


