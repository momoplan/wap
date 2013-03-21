<%@page import="java.util.*"%>
<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.buybal.lot.lottype.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite"); 
String elevenDuoJinSwitch = rbint.getString("elevenDuoJinSwitch");//获取山东十一运夺金开关
String elevenDuojinTBZHSwitch = rbint.getString("elevenDuojinTBZHSwitch");//获取山东十一运夺金特别追号开关

	String deadline = "";
%>
<title>十一运夺金首页</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="/wap/buyLottery.jspx">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>十一运夺金<br />
<%
	deadline = CommonUtil.getDeadline("T01012", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %> 

 <form action="/wap/ElevenDuoJin/index.jspx" method="post">
 <input type="submit" value="刷新" />
 </form>
<br />
59%返奖,中奖率最高  <a href="/wap/ElevenDuoJin/introduction.jspx">玩法介绍</a><br />
<div>
普通玩法<br/>
任选：<a href="/wap/ElevenDuoJin/optionOne.jspx">一</a> <a href="/wap/ElevenDuoJin/optionTwo.jspx">二</a>
 <a href="/wap/ElevenDuoJin/optionThree.jspx" >三</a> <a href="/wap/ElevenDuoJin/optionFour.jspx">四</a>
  <a href="/wap/ElevenDuoJin/optionFive.jspx" >五</a> <a href= "/wap/ElevenDuoJin/optionSix.jspx" >六</a> 
  <a href="/wap/ElevenDuoJin/optionSeven.jspx">七</a> <a href= "/wap/ElevenDuoJin/optionEight.jspx">八</a><br/>
选前二：<a href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选单式</a> 
<a href="/wap/ElevenDuoJin/ForwardTwoZXFS.jspx">直选复式</a> <br/>
<a href="/wap/ElevenDuoJin/DWTwoZXFS.jspx">直选定位复式</a>  
 <a href= "/wap/ElevenDuoJin/ForwardTwoGroup.jspx">组选</a>
<br/>
选前三：
<a href="/wap/ElevenDuoJin/ForwardThreeZX.jspx">直选单式</a> 
<a href="/wap/ElevenDuoJin/ForwardThreeZXFS.jspx">直选复式</a> <br/>
<a href="/wap/ElevenDuoJin/DWThreeZXFS.jspx">直选定位复式</a>  
<a href="/wap/ElevenDuoJin/ForwardThreeGroup.jspx" >组选</a><br/>
胆拖玩法<a href="/wap/ElevenDuoJin/dantuointroduction.jspx">[胆拖必读]</a><br/>
<a href="/wap/ElevenDuoJin/dantuoR2.jspx">二</a> 
<a href="/wap/ElevenDuoJin/dantuoR3.jspx">三</a> 
<a href="/wap/ElevenDuoJin/dantuoR4.jspx">四</a> 
<a href="/wap/ElevenDuoJin/dantuoR5.jspx">五</a> 
<a href="/wap/ElevenDuoJin/dantuoR6.jspx">六</a> 
<a href="/wap/ElevenDuoJin/dantuoR7.jspx">七</a> <br/>
<a href="/wap/ElevenDuoJin/dantuoGroup2.jspx">前二组选</a> <a href="/wap/ElevenDuoJin/dantuoGroup3.jspx">前三组选</a><br/>
</div>
<%if("0".equals(elevenDuojinTBZHSwitch)){}else{ %>
<div>
【追号专区】[<a href="/wap/ElevenDuoJin/addnumberIntroduction.jspx">详细介绍</a>]<br/>
如意彩特别追号,让您中奖收益最大化<br/>
任选：<a href="/w/add/toAddNumberBet.jspx?flag=rx1">一</a> 
<a href="/w/add/toAddNumberBet.jspx?flag=rx2">二</a> 
<a href="/w/add/toAddNumberBet.jspx?flag=rx3">三</a> 
<a href="/w/add/toAddNumberBet.jspx?flag=rx4">四</a> 
<a href="/w/add/toAddNumberBet.jspx?flag=rx5">五</a> 
<a href="/w/add/toAddNumberBet.jspx?flag=rx6">六</a>
<a href="/w/add/toAddNumberBet.jspx?flag=rx7">七</a> 
<a href="/w/add/toAddNumberBet.jspx?flag=rx8">八</a> <br/>
</div>
<%} %>
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
<a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
</body>
