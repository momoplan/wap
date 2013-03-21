<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	List<String> list = CommonUtil.getBetCode("T01012","5");
    String deadline = "";
	String addnumber  = (String)request.getAttribute("addnumber");
	String betcode  = (String)request.getAttribute("betcode")== null ?"":(String)request.getAttribute("betcode");
	String message  = (String)request.getAttribute("message");
	if(addnumber == null) addnumber = "1";
	String beishu = (String) request.getAttribute("beishu");if(beishu == null) beishu = "1";
    String autocode = (String) request.getAttribute("autocode");if(autocode == null) autocode = "5";
    String autoFScode = (String) request.getAttribute("autoFScode");if(autoFScode == null) autoFScode = "5";

%>
	<title>十一运夺金前二组选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>十一运夺金<br/>
		<a href="/wap/ElevenDuoJin/optionOne.jspx">任选</a>|<a 
		   href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选</a>|组选|<a 
		   href="/wap/ElevenDuoJin/dantuoR2.jspx">胆拖</a>|<a 
		   href="/w/add/toAddNumberBet.jspx?flag=rx1">特别追号</a><br/>
		前二组选|<a href="/wap/ElevenDuoJin/ForwardThreeGroup.jspx" >前三组选</a><br/>
		
		自选|<a 
		   href="/w/wap/ElevenDuoJin/ForwardTwoGroupAutoDS.jspx">单式机选</a>|<a 
		   href="/w/wap/ElevenDuoJin/ForwardTwoGroupAutoFS.jspx">复式机选</a><br/>
		<%
			deadline = CommonUtil.getDeadline("T01012", 0);
			if (deadline == null)
			deadline = "";
		%> <%
 			if (deadline != null) {
 		%> <%
 			out.print(deadline);
 		%> <%}%>
 		<form action="/wap/ElevenDuoJin/ForwardTwoGroup.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
 		从1~11选择,注码个数≥2，号码间无分隔符,10之前号码前面补0,如1要输入01<br/>
 		 <%if(message != null){ %>
		<span style="color: red;">   提示： <%out.println(message); %><br/></span>
		 <%} %>
 		选前二组选自选<br/>
 		<form action="/elevenduojin/DetailBetG2.jspx" method="post">
 		注码：<input name="betcode" type="text" emptyok="true"  size="22" maxlength="22" format="*N" tabindex="1" value="<%=betcode %>" /><br/>
 		倍数：<input name="beishu" type="text" emptyok="true"  size="2" maxlength="2" format="*N" tabindex="1" value="<%=beishu %>" />倍(最多99倍)<br/>
 		追号：<input name="addnumber" type="text" emptyok="true"  size="2" maxlength="2" format="*N" tabindex="1" value="<%=addnumber %>" />期(最多99期,追1期即买当前期)<br/>
 		<input type="hidden" name="type" value="G2" />
 		<input type="submit" value="提交投注" />
 		</form><br/>
 		
		前二组选:与开奖号码的前两位一致（不限顺序）即中65元!<br/>	
		【最新开奖】<br/>	
		<%
			String code = "";
			for (int i = 0; i < list.size(); i++) {
			code = list.get(i);
		%> <%
 			out.print(code);
 		%> <br />	
		<%}%><br />	
</body>