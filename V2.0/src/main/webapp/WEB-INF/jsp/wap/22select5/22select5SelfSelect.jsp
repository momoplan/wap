<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>22选5自选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx">购彩大厅</a>-22选5<br/>
自选|<a 
		   href="/select5from22/autoSelect.jspx">机选</a>|<a 
		   href="/select5from22/dantuoSelect.jspx">胆拖</a><br/>
	${deadline }<br/>
	从01-22共22个号码中至少选择5个号码进行投注，22个号码中最多可选18个(所选号码不能超过10000注)<br/>
	(号码间无空格，小于10则补0。如0206132022)<br/>
	<c:if test="${not empty messageError }">
		<a style="color: red">${messageError }</a><br/>
	</c:if>
	<form action="/select5from22/selfSelectDetail.jspx" method="post">
		填写号码:<br/>
		<input name="zhuma" type="text" maxlength="36" size="14" value="${zhuma }" /><br/>(如:0102030405)<br/>
		倍数:<input name="beishu" type="text" maxlength="2" size="2" value="${beishu }" />(最多99倍)<br/>
		追号:<input name="addNumber" type="text" maxlength="2" size="2" value="${addNumber }" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
		<input name="lotno" type="hidden" value="T01013"/>
		<input type="submit" value="提交投注"/>
	</form>
	<br/>
	<c:if test="${not empty list }">
	【最新开奖】<br/>
		<c:forEach items="${list }" var="lis">
			${lis }<br/>
		</c:forEach>
	</c:if>
<a href="/wap/22select5/22select5Index.jspx">返回上一页</a>
</body>	