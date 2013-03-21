<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>开奖详细信息</title>
<%
  String type = (String)request.getParameter("type");
%>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-
<a href="/w/select/getWinningInfoBylottery.jspx">开奖公告</a>
<%
  if(type.equals("1")){
	  %>
     -竞彩足彩开奖记录
 <% }else{%>
     -竞彩篮球开奖记录
 <%}%>
<br/>
<c:if test="${not empty times }">
<form action="<%=request.getContextPath() %>/select/getJClqResult.jspx">
<div>
    日期选择：<select name="querytime">
			    <c:forEach items="${times }" var="list">
			    	<c:choose>
			    		<c:when test="${list eq querytime }">
			    			<option selected="selected">${list}</option>
			    		</c:when>
			    		<c:otherwise>
			    			<option>${list}</option>
			    		</c:otherwise>
			    	</c:choose>
			    </c:forEach>
           </select>
           <input type="hidden" name="type" value="<%=type %>">
           <input type="submit" value="查询">
</div>
</form>
</c:if>
<div>
         编号+客队+比分+主队+赛果
</div>
	<c:if test="${not empty jclqList }">
		<c:forEach items="${jclqList }" var="list">
			${list.week }${list.teamid } ${list.team } <a style="color: red"><b>${list.result} </b></a><br/>
		</c:forEach>
	</c:if>
<a href="/w/select/getWinningInfoBylottery.jspx">返回上一页</a>
</body>
