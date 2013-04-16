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
	String toBetcode = (String)request.getAttribute("toBetcode");
	String viewBetcode = (String)request.getAttribute("viewBetcode");
	String zhushu = (String)request.getAttribute("zhushu");
%>
	<title>十一运夺金手选投注确认</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
			<%if("R1".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionOne.jspx">任选一</a>
			<%}else if("R2".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionTwo.jspx">任选二</a>
			<%}else if("R3".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionThree.jspx">任选三</a>
			<%}else if("R4".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionFour.jspx">任选四</a>
			<%}else if("R5".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionFive.jspx">任选五</a>
			<%}else if("R6".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionSix.jspx">任选六</a>
			<%}else if("R7".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionSeven.jspx">任选七</a>
			<%}else if("R8".equals(type)){%>
			<a href="/wap/ElevenDuoJin/optionEight.jspx">任选八</a>
			<%}else if("z2".equals(type)){%>
			<a href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">前二直选</a>
			<%}else if("z2fs".equals(type)){%>
			<a href="/wap/ElevenDuoJin/ForwardTwoZXFS.jspx">前二直选复式</a>
			<%}else if("z3fs".equals(type)){%>
			<a href="/wap/ElevenDuoJin/ForwardThreeZXFS.jspx">前三直选复式</a>
			<%}else if("z2dwfs".equals(type)){%>
			<a href="/wap/ElevenDuoJin/DWTwoZXFS.jspx">前二直选定位复式</a>
			<%}else if("z3dwfs".equals(type)){%>
			<a href="/wap/ElevenDuoJin/DWThreeZXFS.jspx">前三直选定位复式</a>
			<%}else if("G2".equals(type)){%>
			<a href="/wap/ElevenDuoJin/ForwardTwoGroup.jspx">前二组选</a>
			<%}else if("z3".equals(type)){%>
			<a href="/wap/ElevenDuoJin/ForwardThreeZX.jspx">前三直选</a>
			<%}else if("G3".equals(type)){%>
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