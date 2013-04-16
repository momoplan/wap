<%@page import="java.util.Random"%>
<%@page import="com.ruyicai.wap.bean.AddNumberAmtBean"%>
<%@page import="java.util.List"%>
<%@page import="com.buybal.lot.lottype.AddNumberUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>十一运夺金追号计划</title>
<%
Random rdm = new Random();
int transctionId = rdm.nextInt();
request.getSession().setAttribute(transctionId+"", "false");
String deadline = CommonUtil.getDeadline("T01012", 0);
String betcode =(String) request.getAttribute("betcode")==null?"":(String) request.getAttribute("betcode");
String addnumber =(String) request.getAttribute("addnumber")==null?"5":(String) request.getAttribute("addnumber");
String flag = (String)request.getAttribute("flag");
String zhushu = (String)request.getAttribute("zhushu");
String term = (String)request.getAttribute("term");
String toBetCode = (String)request.getAttribute("toBetCode");
String viewBetcode = (String)request.getAttribute("viewBetcode");
String amount = (String)request.getAttribute("amount");
List<String> beishuarr = new ArrayList<String>();
for(int i = 0 ; i< Integer.valueOf(addnumber) ; i++){
	String beishus =(String) request.getAttribute("beishu"+i);
	beishuarr.add(beishus);
}
List<AddNumberAmtBean> listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr, zhushu, addnumber,flag);
%>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="/wap/buyLottery.jspx">
<%
	out.print(CommonUtil.printHall());
%> </a><%
 	out.print(CommonUtil.printChar());
%> 
 <a
	href="/wap/ElevenDuoJin/addnumberIntroduction.jspx">追号计划</a> 
<%
 	out.print(CommonUtil.printChar());
%>追号计划投注详细<br />
 <% 	if (deadline != null) { %> 
 <% 	out.print(deadline); %> <br/><%}%>
您的投注：<br/>
从十一运夺金第<%=term %>期开始追<%=addnumber %>期<br/>
注码：<%=betcode %><br/>
注数：<%=zhushu %>注<br/>
期数 |倍数 |投入 |累计  |盈利<br/>
<%for(int i = 0 ; i<listAdd.size() ; i++){ %>
 <%= i+1%>期
    - <%=String.valueOf(listAdd.get(i).getBeishu()) %>倍
    - 投<%= String.valueOf( listAdd.get(i).getBetAmt()) %>
    <%
    	if(String.valueOf(listAdd.get(i).getAddAmt()).length()==1){%>
    - 累 <%= String.valueOf(listAdd.get(i).getAddAmt())  %>	
    	<%}else{%>
    - 累<%= String.valueOf(listAdd.get(i).getAddAmt())  %> 
    		<%}
    %>   
   
    - 赚<%= String.valueOf( listAdd.get(i).getProfAmt())%>
<br/>
<%} %>
中奖即停止追号<br/>
<form action="/add/bet.jspa" method="post">
<input type="hidden" name="zhuma" value="<%=toBetCode %>">	
<input type="hidden" name="addnumber" value="<%=addnumber %>">	
<input type="hidden" name="amount" value="<%=amount %>">	
<input type="hidden" name="zhushu" value="<%=zhushu %>">
<input type="hidden" name="token" value="<%=transctionId %>" />	
<input type="hidden" name="flag" value="<%=flag %>">	
<%for(int i = 0 ; i<listAdd.size() ; i++){ %>
<input type="hidden" name="<%="beishu"+i %>" value="<%=String.valueOf(listAdd.get(i).getBeishu()) %>">
<%} %>	
<input type="submit" value="确认投注">	
</form>
<br />
<%
    List<String> list = CommonUtil.getBetCode("T01012","5");
 %> 【最新开奖】<br /> <%
 	String code = "";
 	for (int i = 0; i < list.size(); i++) {
 		code = list.get(i);
 %>
 <%
 	out.print(code);
 %> <br /> <%
 	}
 %>
</body>
