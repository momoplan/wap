<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	List<String> list = CommonUtil.getBetCode("T01012","5");
    String deadline = "";
    String message = (String)request.getAttribute("message");
    String beishu = (String)request.getAttribute("beishu")==null ?"1":(String)request.getAttribute("beishu");
    String betcode = (String)request.getAttribute("betcode") == null ?"":(String)request.getAttribute("betcode");
    String addnumber = (String)request.getAttribute("addnumber")== null?"1":(String)request.getAttribute("addnumber");
%>
	<title>十一运夺金前二直选复式</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%><a href="<%=response.encodeURL(path+"/wap/ElevenDuoJin/index.jspx")%>">
		十一运夺金</a><%
			out.print(CommonUtil.printChar());
		%>前二直选复式<br/>
		<a href="/wap/ElevenDuoJin/optionOne.jspx">任选</a>|直选|<a 
		   href= "/wap/ElevenDuoJin/ForwardTwoGroup.jspx">组选</a>|<a 
		   href="/wap/ElevenDuoJin/dantuoR2.jspx">胆拖</a>|<a 
		   href="/w/add/toAddNumberBet.jspx?flag=rx1">特别追号</a><br/>
		选前二：<a href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选单式</a>|直选复式|<a 
			href="/wap/ElevenDuoJin/DWTwoZXFS.jspx">直选定位复式</a><br/>
		选前三：<a href="/wap/ElevenDuoJin/ForwardThreeZX.jspx">直选单式</a>|<a 
		href="/wap/ElevenDuoJin/ForwardThreeZXFS.jspx">直选复式</a>|<a 
		href="/wap/ElevenDuoJin/DWThreeZXFS.jspx">直选定位复式</a> <br/>
		<%
			deadline = CommonUtil.getDeadline("T01012", 0);
			if (deadline == null)
			deadline = "";
		%> <%
 			if (deadline != null) {
 		%> <%
 			out.print(deadline);
 		%> <%}%>
 		<form action="/wap/ElevenDuoJin/ForwardTwoZXFS.jspx" method="post">
 		  <input type="submit" value="刷新" />
 		</form><br />
 		从1~11选择,注码个数≥3，号码间无分隔符,10之前号码前面补0,如1要输入01<br/>
 		<%if(message != null){ %>
		<span style="color: red;">   提示： <%out.println(message); %><br/></span>
		<%} %>
 		选前二直选复式<br/>
 		<form action="/elevenduojin/groupFSDetail.jspx" method="post">
	 		注码：<input name="betcode" type="text"  size="20" maxlength="22" tabindex="1" value="<%=betcode %>" /><br/>
	 		倍数：<input name="beishu" type="text"  size="2" maxlength="2"  tabindex="1" value="<%=beishu %>" />倍(最多99倍)<br/>
	 		追号：<input name="addnumber" type="text"  size="2" maxlength="2"  tabindex="1" value="<%=addnumber %>" />期(最多99期,追1期即买当前期)<br/>
	 		<input type="hidden" name="type" value="z2fs" />
	 		<input type="submit" value="提交投注" />
 		</form><br/>
		前二直选:投注号码与开奖号码前两位相同中130元<br/>	
		【最新开奖】<br/>	
		<%
			String code = "";
			for (int i = 0; i < list.size(); i++) {
			code = list.get(i);
		%> <%
 			out.print(code);
 		%><br />	 
		<%}%><br />	
</body>