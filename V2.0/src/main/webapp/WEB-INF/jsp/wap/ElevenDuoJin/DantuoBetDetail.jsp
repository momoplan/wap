<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
Random rdm = new Random();
int transctionId = rdm.nextInt();
request.getSession().setAttribute(transctionId+"", "false");
String beishu = (String)request.getAttribute("beishu");
String type = (String)request.getAttribute("type");
String addnumber = (String)request.getAttribute("addnumber");
String term = (String)request.getAttribute("term");
String amount = (String)request.getAttribute("amount");
String toBetcode = (String)request.getAttribute("toBetcode");
String viewBetcode = (String)request.getAttribute("viewBetcode");
String zhushu = (String)request.getAttribute("zhushu");
%> 
<title>十一运夺金胆拖</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx">
<%
	out.print(CommonUtil.printHall());
%> </a>
<%
	out.print(CommonUtil.printChar());
%>
	<%if("DR2".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoR2.jspx">胆拖任选二</a>
			<%}else if("DR3".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoR3.jspx">胆拖任选三</a>
			<%}else if("DR4".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoR4.jspx">胆拖任选四</a>
			<%}else if("DR5".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoR5.jspx">胆拖任选五</a>
			<%}else if("DR6".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoR6.jspx">胆拖任选六</a>
			<%}else if("DR7".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoR7.jspx">胆拖任选七</a>
			<%}else if("DZX2".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoGroup2.jspx">胆拖选前二组选</a>
			<%}else if("DZX3".equals(type)){%>
			<a href="/wap/ElevenDuoJin/dantuoGroup3.jspx">胆拖选前三组选</a>
			<%}%>
<%
	out.print(CommonUtil.printChar());
%>确认投注
<br />
		您的投注:<br/>
			十一运夺金第<%= term%>期<br/>
			金额: <%= amount%>元<br/>
			注数: <%=zhushu %>注<br/>
			倍数: <%= beishu%>倍<br/>
		           追号: <%= addnumber%>期<br/>
			投注号码:<br/>
			<%= viewBetcode %><br/>
			<form action="/elevenduojin/bet.jspa" method="post">
			<input type="hidden" name="term" value="<%=term %>" />
		    <input type="hidden" name="amount" value="<%=amount %>" />
			<input type="hidden" name="zhushu" value="<%=zhushu %>" />
			<input type="hidden" name="beishu" value="<%=beishu %>" />
			<input type="hidden" name="addnumber" value="<%=addnumber %>" />
			<input type="hidden" name="zhuma" value="<%=toBetcode %>" />				
			<input type="hidden" name="token" value="<%=transctionId %>" />
			<input type="hidden" name="type" value="<%=type %>" />
		    <input type="submit" value="确认投注" />
		    </form>
	
		<%if("DR2".equals(type)){%>
			<form action="/wap/ElevenDuoJin/dantuoR2.jspx"	method="post">
			<input type="submit" value="重新选号" />
			</form>
			<%}else if("DR3".equals(type)){%>
			<form action="/wap/ElevenDuoJin/dantuoR3.jspx"	method="post">
			<input type="submit" value="重新选号" />
						</form>
			<%}else if("DR4".equals(type)){%>
			<form action="/wap/ElevenDuoJin/dantuoR4.jspx"	method="post">
			<input type="submit" value="重新选号" />
						</form>
			<%}else if("DR5".equals(type)){%>
			<form action="/wap/ElevenDuoJin/dantuoR5.jspx"	method="post">
			<input type="submit" value="重新选号" />
						</form>
			<%}else if("DR6".equals(type)){%>
			<form action="/wap/ElevenDuoJin/dantuoR6.jspx"	method="post">
			<input type="submit" value="重新选号" />
						</form>
			<%}else if("DR7".equals(type)){%>
			<form action="/wap/ElevenDuoJin/dantuoR7.jspx"	method="post">
			<input type="submit" value="重新选号" />
						</form>
			<%}else if("DZX2".equals(type)){%>
			<form action="/wap/ElevenDuoJin/dantuoGroup2.jspx"	method="post">
			<input type="submit" value="重新选号" />
						</form>
			<%}else if("DZX3".equals(type)){%>
		    <form action="/wap/ElevenDuoJin/dantuoGroup3.jspx"	method="post">
		    <input type="submit" value="重新选号" />
		    			</form>
			<%} %>
		    
<br />
<%
	List<String> list = CommonUtil.getBetCode("T01012","5");
%> 【最新开奖】<br /> <%
 	String code = "";
 	for (int i = 0; i < list.size(); i++) {
 		code = list.get(i);
 %> <%
 	out.print(code);
 %> <br /> <%
 	}
 %> 
</body>
