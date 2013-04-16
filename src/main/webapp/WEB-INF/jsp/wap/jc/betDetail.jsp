<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="<%=request.getContextPath() %>/wap/wapindex.jspx">首页</a>-<a href="<%=request.getContextPath() %>/wap/buyLottery.jspx">购彩大厅</a>
<br/>
<h4>竞彩-<c:if test="${lotno eq 'BSK001'}">篮球胜负</c:if><c:if test="${lotno eq 'BSK002'}">篮球让分胜负</c:if> 
<c:if test="${lotno eq 'BSK003'}">篮球胜分差</c:if><c:if test="${lotno eq 'BSK004'}">篮球大小分</c:if>
<c:if test="${lotno eq 'FT001'}">足球胜平负</c:if><c:if test="${lotno eq 'FT002'}">足球比分</c:if> 
<c:if test="${lotno eq 'FT003'}">足球总进球</c:if><c:if test="${lotno eq 'FT004'}">足球半场胜平负</c:if></h4>
	<form action="<%=request.getContextPath() %>/jc/betDetail.jspa" method="post">
		彩种玩法：${lotname }<br/>
		注数：${zhushu }<br/>
		倍数：${beishu }<br/>
		金额：${amount }<br/>
		<p style="color: red;">
			<input type="radio" checked="checked" value="daigou" name="buyways">普通投注
		</p>
		<p style="color: red;">
			<input type="radio" value="hemai" name="buyways">发起合买
		</p><br/>
		<input type="hidden" name = "zhushu" value="${zhushu }" />
		<input type="hidden" name = "lotno" value="${lotno }" />
		<input type="hidden" name = "beishu" value="${beishu }" />
		<input type="hidden" name = "betcode" value="${betcode }" />
		<input type="hidden" name = "amount" value="${amount }" />
		<input type="hidden" name = "token" value="${token }" />
		<input type="submit" value="确认投注" class="btnbg"><br>	
	</form>
		


