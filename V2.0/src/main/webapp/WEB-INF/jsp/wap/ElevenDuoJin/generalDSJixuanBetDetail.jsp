<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	List<String> list = CommonUtil.getBetCode("T01012","5");
	request.getSession().setAttribute(transctionId+"", "false");
	String beishu = (String)request.getAttribute("beishu");
	String type = (String)request.getAttribute("type");
	String addnumber = (String)request.getAttribute("addnumber");
	String term = (String)request.getAttribute("term");
	String amount = (String)request.getAttribute("amount");
	String autocode = (String)request.getAttribute("autocode");
	String toBetcode = (String)request.getAttribute("toBetcode");
	String viewBetcode = (String)request.getAttribute("viewBetcode");
	String zhushu = (String)request.getAttribute("zhushu");
%>
	<title>十一运夺金单式机选投注确认</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
			<%if("autoR1".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionOne.jspx">任选一</a>
			<%}else if("autoR2".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionTwo.jspx">任选二</a>
			<%}else if("autoR3".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionThree.jspx">任选三</a>
			<%}else if("autoR4".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionFour.jspx">任选四</a>
			<%}else if("autoR5".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionFive.jspx">任选五</a>
			<%}else if("autoR6".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionSix.jspx">任选六</a>
			<%}else if("autoR7".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionSeven.jspx">任选七</a>
			<%}else if("autoR8".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionEight.jspx">任选八</a>
			<%}else if("autoG2".equals(type)){%>
			<a href="/wap/ElevenDuoJin/ForwardTwoGroup.jspx">前二组选</a>
			<%}else if("autoG3".equals(type)){%>
			<a href="/wap/ElevenDuoJin/ForwardThreeGroup.jspx">前三组选</a>
			<%}%>
		<%
			out.print(CommonUtil.printChar());
		%>确定投注<br/>
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
	     <form action="/elevenduojin/autoBetDetail.jspx" method="post">
		    <input type="hidden" name="autocode" value="<%=autocode%>" />
		    <input type="hidden" name="type" value="<%=type%>" />
		    <input type="submit" value="重新选号" />
		    </form>
	       <br/>
			【最新开奖】<br/>	
		<%
			String code = "";
			for (int i = 0; i < list.size(); i++) {
			code = list.get(i);
		%> <%
 			out.print(code);
 		%> <br/>
		<%}%><br />	
</body>