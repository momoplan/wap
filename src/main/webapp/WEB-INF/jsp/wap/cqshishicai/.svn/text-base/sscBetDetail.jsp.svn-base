<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.buybal.lot.lottype.ShishicaiUtil"%>
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
<title>确定投注</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
			}
			String zhuma = request.getAttribute("zhuma").toString();
			String newzhuma =(String) request.getAttribute("newzhuma");
		    String beishu =	(String)request.getAttribute("beishu");
		    String zhushu =	request.getAttribute("zhushu").toString();
		    String amount = request.getAttribute("amt").toString();
		    String term = (String)request.getAttribute("term");//期号
		    String type = (String)request.getAttribute("type");//类型
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
			out.print(ShishicaiUtil.playNameSSC(type));
		%><%
			out.print(CommonUtil.printChar());
		%>确认投注<br/>
		您的投注:<br/>
			时时彩第<% out.print(term); %>期<br/>
			金额: <% out.print(amount); %>元<br/>
			注数: <% out.print(zhushu); %>注<br/>
			倍数: <% out.print(beishu); %>倍<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>
				追号: <% out.print(addNumber); %>期<br/>
				<%} %>
			<%} %>
			投注号码:<br/>
			<% out.print(newzhuma); %><br/>
			<form action="/shishicai/SSCBet.jspa" method="post">
				<input type="hidden" name="zhushu" value="<%=zhushu %>" />
				<input type="hidden" name="beishu" value="<%=beishu %>" />
				<input type="hidden" name="amount" value="<%=amount %>" />
				<input type="hidden" name="zhuma" value="<%=zhuma %>" />
				<input type="hidden" name="newzhuma" value="<%=newzhuma %>" />
				<input type="hidden" name="term" value="<%=term %>" />
				<input type="hidden" name="token" value="<%=transctionId %>" />
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<input type="hidden" name="addNumber" value="<%=addNumber %>" />
				<%} %>
				<input type="hidden" name="type" value="<%=type %>" />
				<input type="submit" value="确认投注" />
			</form><br/>
			【最新开奖】<br/>
		 <% 
		 	List<String> list = CommonUtil.getBetCode("T01007","5");
		 	String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>	
</body>