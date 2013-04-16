<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>双色球胆拖自选</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = ""; 
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
				if (addNumber==null) addNumber="1";
			}
			String message = (String)request.getAttribute("message");
			String redballDanma = (String)request.getAttribute("redballDanma");
			String redballTuoma = (String)request.getAttribute("redballTuoma");
			String blueball = (String)request.getAttribute("blueball");
			String beishu = (String)request.getAttribute("beishu");
			if (redballDanma==null) redballDanma="";
			if (redballTuoma==null) redballTuoma="";
			if (blueball==null) blueball="";
			if (beishu==null) beishu="1";
			String deadline = CommonUtil.getDeadline("F47104", 0);
			if (deadline==null) deadline = "";
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>双色球<br/>
		自选|<a href="/w/wap/DoubleBall/SingleAutoSelection.jspx">机选</a><br/>
		<a href="/w/wap/DoubleBall/SingleSelfSelection.jspx">单式自选</a>|<a href="/wap/DoubleBall/MultipleSelfSelection.jspx">复式自选</a>|胆拖自选<br/>
			号码间无分隔，小于10则补0，如：选01球05球20球则填入010520<br/>
			胆码选1-5个，拖码选2-20个，胆码与拖码总数不能小于7、不能大于25，胆拖码不能重复<br/>
			<%if (deadline!=null) { %>
			<%out.print(deadline); %><br/>
		 	<%} %>
			<a style="color: red"><%if (message!=null) { %>
				<% out.print("提示："+message); %><br/>
			<%} %></a>
			红胆码号码(如020318):<br/>
			<form action="<%=response.encodeURL(path+"/doubleBallDantuo/showSelfBetDetails.jspx")%>" method="post" >
			<input name="redballDanma" type="text" emptyok="false" maxlength="10" size="20" format="*N" value="<%=redballDanma %>" /><br/>
			红拖码号码(如050732):<br/>
			<input name="redballTuoma" type="text" emptyok="false" maxlength="40" size="20" format="*N" value="<%=redballTuoma %>" /><br/>
			蓝球号码(数字01-16):<br/>
			<input name="blueball" type="text" emptyok="false" maxlength="32" size="20" format="*N" value="<%=blueball %>" /><br/>
			倍  数:<input name="beishu" type="text" emptyok="false" maxlength="2" size="3" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			追  号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
				<input type="hidden" name="pageType" value="selfSelection" />
				<input type="submit" value="提交投注" />
			</form>
			 <br/>
			<%	List<String> list = CommonUtil.getBetCode("F47104","5"); %>
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