<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>双色球定胆机选</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = ""; 
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
				if (addNumber==null) addNumber="1";
			}
			String message = (String)request.getAttribute("message");
			String redballDanma = request.getAttribute("redballDanma")==null?"":(String)request.getAttribute("redballDanma");
			String blueball = request.getAttribute("blueball")==null?"":(String)request.getAttribute("blueball");
			String beishu = request.getAttribute("beishu")==null?"1":(String)request.getAttribute("beishu");
			String zhushu=  request.getAttribute("zhushu")==null?"5":(String)request.getAttribute("zhushu");
			String deadline = CommonUtil.getDeadline("F47104", 0);
			if (deadline==null) deadline = "";
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>双色球<br/>
		<a href="/w/wap/DoubleBall/SingleSelfSelection.jspx">自选</a>|机选<br/>
		<a href="/wap/DoubleBall/SingleAutoSelection.jspx">单式机选</a>|<a href="/wap/DoubleBall/MultipleAutoSelection.jspx">复式机选</a>|<a href="/wap/DoubleBall/DantuoAutoSelection.jspx">胆拖机选</a>|定胆机选<br/>	
		
			号码间无分隔，小于10则补0，如：选01球05球20球则填入010520<br/>
			
			<%if (deadline!=null) { %>
			<%out.print(deadline); %><br/>
		 	<%} %>
		 	<a style="color: red">
			<%if (message!=null) { %>
				<% out.print("提示："+message); %><br/>
			<%} %></a>
			红球胆码(1-5个):<br/>
			<form action="<%=response.encodeURL(path+"/doubleballSingleSelfSelection/showDingdanBetDetails.jspx")%>" method="post" >
			<input name="redballDanma" type="text" emptyok="false" maxlength="10" size="15" format="*N" value="<%=redballDanma %>" /><br/>
			蓝球胆码(1个):<br/>
			<input name="blueball" type="text" emptyok="false" maxlength="2" size="5" format="*N" value="<%=blueball %>" /><br/>
			机  选:<input name="zhushu" type="text" emptyok="false" maxlength="3" size="3" format="*N" value="<%=zhushu %>" />注<br/>
			倍  数:<input name="beishu" type="text" emptyok="false" maxlength="2" size="3" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			追  号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
				<input type="hidden" name="type" value="DingdanSelection" />
			<input type="submit" value="提交投注" />
			</form>
			 <br/>
		<%	List<String> list =CommonUtil.getBetCode("F47104","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i);
		  %> 
		   <%out.print(code);%><br/>
		   <%}%><br/>
		      
		   <a href="<%=response.encodeURL(path + "/wap/DoubleBall/DoubleBallIndex.jspx"
								)%>">返回上一页</a>
</body>