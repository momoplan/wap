<%@page import="java.util.List"%>
<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ruyicai.wap.bean.WinSelectInfoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>开奖详细信息</title>
<%
	request.setAttribute("pvType", 5);
%>
<% 
   String mrg = (String) request.getAttribute("mrg");
   List<WinSelectInfoBean> list = ( List<WinSelectInfoBean>)request.getAttribute("winlist");
   String lotname = (String ) request.getAttribute("lotname");
%>
<body>
<div><a href="/w/wap/wapindex.jspx">首页</a>-
<a href="/w/select/getWinningInfoBylottery.jspx">开奖公告</a>
<% out.print(CommonUtil.printChar());%>历史记录<br/>
</div>
<c:choose>
	<c:when test="${'10' eq qiShu }">
	10期 | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=20">20期</a> | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=30">30期</a> | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=50">50期</a><br/>
	</c:when>
	<c:when test="${'20' eq qiShu }">
	<a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=10">10期</a> | 20期 | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=30">30期</a> | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=50">50期</a><br/>
	</c:when>
	<c:when test="${'30' eq qiShu }">
	<a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=10">10期</a> | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=20">20期</a> | 30期 | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=50">50期</a><br/>
	</c:when>
	<c:when test="${'50' eq qiShu }">
	<a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=10">10期</a> | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=20">20期</a> | <a
	href="/w/select/lotterySelectByType.jspx?lotno=${lotno }&qiShu=30">30期</a> | 50期<br/>
	</c:when>
</c:choose>
<div>
<%  if(mrg!=null){%>
<%out.print(CommonUtil.printWarningMessage(mrg)); %>
<%} %>
</div>
<div>
  <%
    for(int i=0 ; i<list.size();i++){
    	
  %>
 <a href="/w/select/lotterySelect.jspx?lotno=<%= list.get(i).getLotno()%>&amp;issue=<%=list.get(i).getBatchcode()%>"> <%="第"+list.get(i).getBatchcode()+"期："+list.get(i).getBetCode() %><br/></a> 
  <%
    }
  %>
</div>
	<a href="/w/select/getWinningInfoBylottery.jspx">返回上一页</a>
</body>