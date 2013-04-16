<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%><%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
	<title>七乐彩胆拖自选</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
				if (addNumber==null) addNumber="1";
			}
			String message = (String)request.getAttribute("message");
			String danma = (String)request.getAttribute("danma");
			String tuoma = (String)request.getAttribute("tuoma");
			if (danma==null) danma="";
			if (tuoma==null) tuoma="";
			String beishu = (String)request.getAttribute("beishu");
			if(beishu==null) beishu = "1";
			String deadline = CommonUtil.getDeadline("F47102", 0);
			if (deadline==null) deadline = "";
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>七乐彩<br/>
		自选|<a 
		   href="/w/wap/qilecai/SingleAutoSelection.jspx">机选</a><br/>
		<a href="/wap/qilecai/SingleSelfSelection.jspx">单式自选</a>|<a href="/wap/qilecai/MultipleSelfSelection.jspx">复式自选</a>|胆拖自选<br/>
		
			<%if (deadline!=null) { %>
			<%out.print(deadline); %><br/>
		 	<%} %>
			(号码间无空格，小于10则补0，如02061320)<br/>
			(胆码1-6个，拖码2-20个，胆码与拖码总数不能小于8，不能重复)<br/>
			<a style="color: red"><% if(message!=null){
					out.print(CommonUtil.printWarningMessage(message)+"<br/>");
			   }%></a>
			   <form action="<%=response.encodeURL(path+"/qilecai/selfSelectionDantuoBetDetail.jspx")%>" method="post">
			   胆码号码:
			<input name="danma" type="text" emptyok="false" maxlength="12" size="14" format="*N" value="<%=danma %>" />(1-6个)<br/>
			拖码号码:
			<input name="tuoma" type="text" emptyok="false" maxlength="40" size="14" format="*N" value="<%=tuoma %>" />(2-20个)<br/>
			倍数:<input name="beishu" type="text" emptyok="false" maxlength="2" size="2" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
			<input type="hidden" name="type" value="ZXDT"/>
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
		      <br/>
		   <a href="<%=response.encodeURL(path + "/wap/qilecai/QilecaiIndex.jspx"
								)%>">返回上一页</a>
	</body>