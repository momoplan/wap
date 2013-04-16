<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>十一运夺金</title>
<body>
<%
 	String deadline = CommonUtil.getDeadline("T01012", 0);
 	if (deadline == null)
 		deadline = "";
	String addnumber = (String) request.getAttribute("addnumber")== null?"1":(String) request.getAttribute("addnumber");
	String danma = (String) request.getAttribute("danma") == null ?"":(String) request.getAttribute("danma");
	String tuoma = (String) request.getAttribute("tuoma") == null ?"":(String) request.getAttribute("tuoma");
	String beishu = (String) request.getAttribute("beishu") == null?"1":(String) request.getAttribute("beishu");
	String message = (String) request.getAttribute("message");
 %> <a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>十一运夺金<br /> 
 <a href="/wap/ElevenDuoJin/optionOne.jspx">任选</a>|<a 
		   href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选</a>|<a 
		   href= "/wap/ElevenDuoJin/ForwardTwoGroup.jspx">组选</a>|胆拖|<a 
		   href="/w/add/toAddNumberBet.jspx?flag=rx1">特别追号</a><br/>
 <a href="/wap/ElevenDuoJin/dantuoR2.jspx">任二</a>|<a 
 	href="/wap/ElevenDuoJin/dantuoR3.jspx">任三</a>|任四|<a 
 	href="/wap/ElevenDuoJin/dantuoR5.jspx">任五</a>|<a 
 	href="/wap/ElevenDuoJin/dantuoR6.jspx">任六</a>|<a 
 	href="/wap/ElevenDuoJin/dantuoR7.jspx">任七</a>|<a 
 	href="/wap/ElevenDuoJin/dantuoGroup2.jspx">前二组选</a>|<a 
 	href="/wap/ElevenDuoJin/dantuoGroup3.jspx">前三组选</a><br/>
 <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %> <form action="/wap/ElevenDuoJin/dantuoR4.jspx"
	method="post">
<input type="submit" value="刷新" /> </form><br />
胆码限选1-3个,拖码2~10，不与胆码重复,至少选择2注<br />
<%if(message != null){ %>
		<span style="color: red;">   提示： <%out.println(message); %><br/></span>
	 <%} %>
<form	action="/elevenduojin/todantuoDetail.jspx"	method="post"> 
           胆码:<input name="danma" type="text" size="20"  value="<%=danma%>" /><br /> 
	拖码:<input name="tuoma" type="text"  size="20"  value="<%=tuoma %>" /><br /> 
	倍数:<input name="beishu" type="text" maxlength="2" size="2"  value="<%=beishu %>" />(最多99倍)<br /> 
           追号:<input name="addnumber" type="text"  maxlength="2"	size="2"  value="<%=addnumber%>" />期<br />(最高99期,默认追1期,即买当前期)<br />
    <input	type="hidden" name="type" value="DR4" />
    <input type="submit" value="提交投注" /> </form> <br /> 
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
 %> </body>
