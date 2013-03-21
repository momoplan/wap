<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	List<String> list = CommonUtil.getBetCode("T01010","5");
    String deadline = "";
    String zhuma = (String)request.getAttribute("zhuma")==null?"":(String)request.getAttribute("zhuma");
    String beishu ="";
    beishu = (String)request.getAttribute("beishu");
    if(beishu ==null) beishu="1";
    String addNumber = "";
    if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getAttribute("addNumber");
		if (addNumber==null) addNumber="1";
	}
    String optionDS ="";
    optionDS = (String)request.getAttribute("optionDS");
    if(optionDS ==null) optionDS="5";
    String err_msg = (String)request.getAttribute("err_msg");
%>
	<title>江西11选5任选四</title>
<!-- 添加统计 -->
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>江西11选5<br/>
		任选|<a 
		   href="/w/wap/11select5/ForwardTwoZX.jspx">直选</a>|<a 
		   href="/w/wap/11select5/ForwardTwoGroup.jspx">组选</a>|<a 
		   href="/w/wap/11select5/dantuoselection.jspx?type=2">胆拖</a><br/>
		<a href="/wap/11select5/optionOne.jspx">一</a>|<a href="/wap/11select5/optionTwo.jspx">二</a>|<a href="/wap/11select5/optionThree.jspx">三</a>|四|<a href="/wap/11select5/optionFive.jspx">五</a>|<a href="/wap/11select5/optionSix.jspx">六</a>|<a href="/wap/11select5/optionSeven.jspx">七</a>|<a href="/wap/11select5/optionEight.jspx">八</a><br/>
		<a 
		   href="/w/wap/11select5/optionFour.jspx">自选</a>|<a 
		   href="/w/wap/11select5/optionFourAutoDS.jspx">单式机选</a>|复式机选<br/>
		<%
			deadline = CommonUtil.getDeadline("T01010", 0);
			if (deadline == null)
			deadline = "";
		%> <%
 			if (deadline != null) {
 		%> <%
 			out.print(deadline);
 		%> <%}%>
 		<form action="/wap/11select5/optionFourAutoFS.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br /><a style="color: red">
		  <% if(err_msg!=null){
			out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
		   }
		%></a>

		复式机选<br/>
		<form action="/11select5/optionFSAuto.jspx" method="post">
		机选:<select name="optionFS">
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
			</select>个<br/>
		倍数:<input name="beishu" type="text" maxlength="2" size="2" value="<%= beishu%>" />倍(最多99倍)<br/>
		追号:<input name="addNumber" type="text" maxlength="2" size="2" value="<%= addNumber%>" />期(最多99期,追1期即买当前期) <br/>
		<input type="hidden" name="R" value="4" />
		<input type="submit" value="确定机选" />
		</form><br/>	
		任选四：与开奖号码中的任四个一致即中78元<br/>	
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