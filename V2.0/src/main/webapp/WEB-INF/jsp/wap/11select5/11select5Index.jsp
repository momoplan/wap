<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.ruyicai.wap.util.*"%>
<%@ page import="com.buybal.lot.lottype.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	String type = "T01010";
	List<String> list = CommonUtil.getBetCode("T01010","5");
	String deadline = "";
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取配置文件开关
	String duolecaiTBZHSwitch = rbint.getString("duolecaiTBZHSwitch"); //取得江西11选5 开关
	Array5Util array5Util = new Array5Util();
%>
<title>江西11选5首页</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>江西11选5<br />
<%
	deadline = CommonUtil.getDeadline("T01010", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %> 

 <form action="/wap/11select5/11select5Index.jspx" method="post">
 <input type="submit" value="刷新" />
 </form>
<br />
59%返奖,中奖率最高  <a href="<%=response.encodeURL(path + "/wap/11select5/11select5Intr.jspx" )%>">玩法介绍</a><br />
普通玩法<br/>
任选：<a href="<%=response.encodeURL(path + "/wap/11select5/optionOne.jspx" )%>">一</a> <a href="<%=response.encodeURL(path + "/wap/11select5/optionTwo.jspx" )%>">二</a> <a href="<%=response.encodeURL(path + "/wap/11select5/optionThree.jspx" )%>">三</a> <a href="<%=response.encodeURL(path + "/wap/11select5/optionFour.jspx" )%>">四</a> <a href="<%=response.encodeURL(path + "/wap/11select5/optionFive.jspx" )%>">五</a> <a href="<%=response.encodeURL(path + "/wap/11select5/optionSix.jspx" )%>">六</a> <a href="<%=response.encodeURL(path + "/wap/11select5/optionSeven.jspx" )%>">七</a> <a href="<%=response.encodeURL(path + "/wap/11select5/optionEight.jspx" )%>">八</a><br/>
选前二：<a href="<%=response.encodeURL(path + "/wap/11select5/ForwardTwoZX.jspx" )%>">直选</a> <a href="<%=response.encodeURL(path + "/wap/11select5/ForwardTwoGroup.jspx" )%>">组选</a><br/>
选前三：<a href="<%=response.encodeURL(path + "/wap/11select5/ForwardThreeZX.jspx" )%>">直选</a> <a href="<%=response.encodeURL(path + "/wap/11select5/ForwardThreeGroup.jspx" )%>">组选</a><br/>
胆拖玩法<a href="<%=response.encodeURL(path + "/wap/11select5/dantuohelp.jspx"
							)%>">[胆拖必读]</a><br/>
<a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=2")%>">二</a> <a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=3")%>">三</a> <a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=4")%>">四</a> <a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=5")%>">五</a> <a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=6")%>">六</a> <a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=7")%>">七</a> <a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=8")%>">八</a><br/>
<a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=9")%>">前二组选</a> <a href="<%=response.encodeURL(path + "/wap/11select5/dantuoselection.jspx"
							 + "?type=10")%>">前三组选</a><br/>

【最新开奖】<br />
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%> <%
 	out.print(code);
 %> <br />
<%
	}
%><br /><a href="<%=response.encodeURL(path + "/wap/buyLottery.jsp"
								)%>">返回上一页</a>
</body>
