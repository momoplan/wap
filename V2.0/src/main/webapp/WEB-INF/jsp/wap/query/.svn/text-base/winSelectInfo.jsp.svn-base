<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@page import="com.ruyicai.wap.bean.WinSelectInfoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>开奖详细信息</title>
<%	request.setAttribute("pvType", 5);%>
<% 
   
   String  batchcode = (String) request.getAttribute("batchcode");
   String  betCode = (String) request.getAttribute("betCode");
   String  lotname = (String) request.getAttribute("lotname");
   String  lotNo = (String) request.getAttribute("lotNo");
   String  info = (String) request.getAttribute("info");
   String  opentime = (String) request.getAttribute("opentime");
   String mrg = (String) request.getAttribute("mrg");
%>
<body>
<div><a href="/w/wap/wapindex.jspx">首页</a>-
<a href="/w/select/getWinningInfoBylottery.jspx">开奖公告</a>
<% out.print(CommonUtil.printChar());%>记录详细<br/>
</div>
<div>
<%  if(mrg!=null){%>
<%out.print(CommonUtil.printWarningMessage(mrg)); %>
<%} %>
</div>
<div>
<%if(lotname != null){ %>
    彩种：<%=lotname %> <br/>
    <%} %>
<%if(batchcode != null){ %>
    期号：<%=batchcode %><br/>
    <%} %>
 <%if(opentime != null){ %>
      开奖日期：<%=opentime %><br/>
   <%} %>
  <%if(betCode != null){ %>  
   开奖号码：<%=betCode %><br/>
   <%} %>
  <%=info %><br/>
</div>
 <a href="/wap/buyLottery.jspx">马上去试试手气</a> <br/>
		仅供参考，以福体彩官方公告为准<br/>
		<%if(lotNo != null){ %>
    <div class="gd">
	 <a href="/select/lotterySelectByType.jspx?lotno=<%=lotNo%>" >更多记录</a>
	</div>
	<%} %>
	<style type="text/css">
     .gd{margin-left:200px}
    </style>
	<a href="/w/select/getWinningInfoBylottery.jspx">返回上一页</a>
</body>
