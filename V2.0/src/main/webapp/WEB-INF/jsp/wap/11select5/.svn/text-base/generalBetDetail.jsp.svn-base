<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	request.getSession().setAttribute(transctionId+"", "false");
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	String certErr = null;
	if(request.getAttribute("certErr")!=null) {
		certErr = (String)request.getAttribute("certErr");
	}
%>
<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String beishu = (String)request.getAttribute("beishu");
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
			}
			String zhushu = (String)request.getAttribute("zhushu");
			String amount = (String)request.getAttribute("amount");
			String zhuma = (String)request.getAttribute("zhuma");
			String newzhuma = (String)request.getAttribute("newzhuma");
		    String term = CommonUtil.getTerm("T01010");//期号
		    String type = (String)request.getAttribute("type");//类型
		%>
	<title>江西11选5普通玩法自选投注确认</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
			<%if(type.equals("R1")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionOne.jspx")%>">任选一</a>
			<%}else if(type.equals("R2")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionTwo.jspx")%>">任选二</a>
			<%}else if(type.equals("R3")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionThree.jspx")%>">任选三</a>
			<%}else if(type.equals("R4")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionFour.jspx")%>">任选四</a>
			<%}else if(type.equals("R5")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionFive.jspx")%>">任选五</a>
			<%}else if(type.equals("R6")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionSix.jspx")%>">任选六</a>
			<%}else if(type.equals("R7")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionSeven.jspx")%>">任选七</a>
			<%}else if(type.equals("R8")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/optionEight.jspx")%>">任选八</a>
			<%}else if(type.equals("Q2")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/ForwardTwoZX.jspx")%>">前二直选</a>
			<%}else if(type.equals("Z2")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/ForwardTwoGroup.jspx")%>">前二组选</a>
			<%}else if(type.equals("Q3")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/ForwardThreeZX.jspx")%>">前三直选</a>
			<%}else if(type.equals("Z3")){%>
			<a href="<%=response.encodeURL(path+"/wap/11select5/ForwardThreeGroup.jspx")%>">前三组选</a>
			<%}%>
		<%
			out.print(CommonUtil.printChar());
		%>确定投注<br/>
		您的投注:<br/>
			江西11选5第<% out.print(term); %>期<br/>
			金额: <% out.print(amount); %>元<br/>
			注数: <% out.print(zhushu); %>注<br/>
			倍数: <% out.print(beishu); %>倍<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>
				追号: <% out.print(addNumber); %>期<br/>
				<%} %>
			<%} %>
			投注号码:<br/>
			<%=newzhuma%>
			<form action="/11select5/BetConfirm.jspa" method="post">
			<br/>
		    <input type="hidden" name="zhuma" value="<%=zhuma %>" />
			<input type="hidden" name="zhushu" value="<%=zhushu %>" />
			<input type="hidden" name="amount" value="<%=amount %>" />
			<input type="hidden" name="term" value="<%=term %>" />
			<input type="hidden" name="beishu" value="<%=beishu %>" />				
			<input type="hidden" name="type" value="<%=type %>" />				
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			<input type="hidden" name="addNumber" value="<%=addNumber %>" />
			<%} %>
			<input type="hidden" name="token" value="<%=transctionId %>" />
		    <input type="submit" value="确认投注" />
		    </form><br/>
			<%	List<String> list = CommonUtil.getBetCode("T01010","5"); %>
		
			【最新开奖】<br/>
		 	<% String   code ="";
		    	for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		 	%> 
		   	<%out.print(code) ;%> <br/> 
			<% } %>
</body>