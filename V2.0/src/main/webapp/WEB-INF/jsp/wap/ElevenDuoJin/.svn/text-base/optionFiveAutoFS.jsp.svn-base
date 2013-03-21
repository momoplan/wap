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
	<title>十一运夺金任选五</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>十一运夺金<br/>
		任选|<a 
		   href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选</a>|<a 
		   href= "/wap/ElevenDuoJin/ForwardTwoGroup.jspx">组选</a>|<a 
		   href="/wap/ElevenDuoJin/dantuoR2.jspx">胆拖</a>|<a 
		   href="/w/add/toAddNumberBet.jspx?flag=rx1">特别追号</a><br/>
		<a href="/wap/ElevenDuoJin/optionOne.jspx">任一</a>|<a 
		   href="/wap/ElevenDuoJin/optionTwo.jspx">任二</a>|<a 
		   href="/wap/ElevenDuoJin/optionThree.jspx" >任三</a>|<a 
		   href="/wap/ElevenDuoJin/optionFour.jspx">任四</a>|任五|<a 
		   href="/wap/ElevenDuoJin/optionSix.jspx" >任六</a>|<a 
		   href="/wap/ElevenDuoJin/optionSeven.jspx">任七</a>|<a 
		   href="/wap/ElevenDuoJin/optionEight.jspx">任八</a><br/>
		   
		   <a 
		   href= "/w/wap/ElevenDuoJin/optionFive.jspx">自选</a>|<a 
		   href="/w/wap/ElevenDuoJin/optionFiveAutoDS.jspx">单式机选</a>|复式机选<br/>
		<%
			deadline = CommonUtil.getDeadline("T01012", 0);
			if (deadline == null)
			deadline = "";
		%> <%
 			if (deadline != null) {
 		%> <%
 			out.print(deadline);
 		%> <%}%>
 		<form action="/wap/ElevenDuoJin/optionFiveAutoFS.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br />
		 
 <%if(message != null){ %>
<span style="color: red;">   提示： <%out.println(message); %><br/></span>
 <%} %>
	
		复式机选<br/>
		<form action="/elevenduojin/autoFSBetDetail.jspx" method="post">
		机选:<input name="autoFScode" type="text"  size="10" maxlength="2"  tabindex="1" value="<%=autoFScode %>" />个(5-11)<br/>
		倍数:<input type="text" name="beishu"  maxlength="2" size="2" value="<%=beishu %>">倍(最多99倍) <br/>
		追号:<input type="text" name="addnumber"  maxlength="2" size="2" value="<%=addnumber %>">期(最多99期,追1期即买当前期) <br/>
		<input type="hidden" name="type" value="autoR5" />
		<input type="submit" value="确定机选" />
		</form><br/>	
		任选五：与开奖号码一致即中540元<br/>	
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