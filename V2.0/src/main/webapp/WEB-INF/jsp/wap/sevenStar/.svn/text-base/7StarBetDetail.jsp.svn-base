<%@page import="com.buybal.lot.lottype.QixingcaiUtil"%>
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

		
	<title>七星彩投注确认</title>
	<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
			}
			String zhumaStr = request.getAttribute("zhumaStr").toString();
			String zhumaView =(String) request.getAttribute("zhumaView");
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
		%><a href="<%=response.encodeURL(path+"/wap/sevenStar/sevenStarByHand.jspx")%>">七星彩自选</a><%
			out.print(CommonUtil.printChar());
		%>确定投注<br/>
		您的投注:<br/>
			七星彩第<% out.print(term); %>期<br/>
			金额: <% out.print(amount); %>元<br/>
			注数: <% out.print(zhushu); %>注<br/>
			倍数: <% out.print(beishu); %>倍<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>
				追号: <% out.print(addNumber); %>期<br/>
				<%} %>
			<%} %>
			投注号码:<br/>
			<%= zhumaStr %>
			<br/>
			<form action="/sevenstar/betConfirm.jspa" method="post">
				
		   		<input type="hidden" name="zhushu" value="<%=zhushu %>" />
				<input type="hidden" name="beishu" value="<%=beishu %>" />
				<input type="hidden" name="amount" value="<%=amount %>" />
				<input type="hidden" name="zhumaStr" value="<%=zhumaStr %>" />
				<input type="hidden" name="term" value="<%=term %>" />
				<input type="hidden" name="token" value="<%=transctionId %>" />
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<input type="hidden" name="addNumber" value="<%=addNumber %>" />
				<%} %>
				<input type="hidden" name="type" value="<%=type %>" />
				<%if("1".equals(addNumber)){ %>
		<p style="color: red;">
			<input type="radio" checked="checked" value="daigou" name="buyways">普通投注
		</p>
		<p style="color: red;">
			<input type="radio" value="presented" name="buyways">我要赠送给他人
		</p>
		<p style="color: red;">
			<input type="radio" value="hemai" name="buyways">发起合买
		</p>
		<%}else{ %>
		<p style="color: red;">您已经选择了追期，无法应用赠彩、合买功能</p>
		<input type="hidden" name="buyways" value="daigou" />
		<%} %>
				<input type="submit" value="确认投注" />
		   </form><br/>
			<%	List<String> list = CommonUtil.getBetCode("T01009","5");  %>
			【最新开奖】<br/>
		 	<% String   code ="";
		    	for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		 	%> 
		   	<%out.print(code) ;%> <br/> 
			<% } %>
</body>	