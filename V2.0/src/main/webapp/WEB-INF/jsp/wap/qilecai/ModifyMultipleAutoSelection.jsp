<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%><%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
	<title>七乐彩复式自选</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
				if (addNumber==null) addNumber="1";
			}
			String message = (String)request.getAttribute("message");
			String zhuma = (String)request.getAttribute("zhuma");
			if(zhuma==null) zhuma = "";
			String beishu = (String)request.getAttribute("beishu");
			if(beishu==null) beishu = "1";
			String deadline = CommonUtil.getDeadline("F47102", 0);
			if (deadline==null) deadline = "";
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>七乐彩复式自选<br/>
			<%if (deadline!=null) { %>
			<%out.print(deadline); %><br/>
		 	<%} %>
			<a style="color: red"><% if(message!=null){
					out.print(CommonUtil.printWarningMessage(message)+"<br/>");
			   }%></a>
			   <form action="<%=response.encodeURL(path+"/qilecai/selfSelectionMultipleBetDetail.jspx")%>" method="post">
			   (数字1-30任选7-16个，号码间无空格，小于10则补0。如02061320)<br/>
			填写号码:<br/>
			<input name="zhuma" type="text" emptyok="false" maxlength="32" size="14" format="*N" value="<%=zhuma %>" /><br/>(如:010203040509)<br/>
			倍数:<input name="beishu" type="text" emptyok="false" maxlength="2" size="2" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
			<input type="hidden" name="type" value="ZXFS"/>
			<input type="submit" value="提交投注"/>
			   </form>
			<br/>
			<%	List<String> list = CommonUtil.getBetCode("F47102","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>
		</body>	