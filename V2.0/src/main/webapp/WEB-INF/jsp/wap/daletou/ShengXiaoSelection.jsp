<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>大乐透12选2手选</title>
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
			String deadline = CommonUtil.getDeadline("T01001", 0);
			if (deadline==null) deadline = "";
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>大乐透<br/>
		自选|<a 
		   href="/w/wap/daletou/SingleAutoSelection.jspx">机选</a><br/>
		<a href="/wap/daletou/SingleSelfSelection.jspx">复式自选</a>|<a href="/wap/daletou/DantuoSelfSelection.jspx">胆拖自选</a>|12选2自选<br/>
		
		<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %><a style="color: red">
			<% if(message!=null){
					out.print(CommonUtil.printWarningMessage(message)+"<br/>");
			   }%></a>
			   <form action="<%=response.encodeURL(path+"/daletou/shengxiaoBetDetail.jspx")%>" method="post">
			   投注号码（01-12选2个或以上，号码间无空格，小于10则补0。如020612）：<br/>
			<input name="zhuma" type="text" emptyok="false" maxlength="24" size="10" format="*N" value="<%=zhuma %>" /><br/>(如:010203040509)<br/>
			倍数:<input name="beishu" type="text" emptyok="false" maxlength="2" size="2" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
			<%if (rbint.getString("array3addNumberSwitch").equals("1")) { %>
			追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
			<input type="hidden" name="type" value="SXZX"/>
			<input type="submit" value="提交投注"/>
			   </form>
			<br/>
			 <a href="<%=response.encodeURL(path + "/wap/daletou/DaletouIndex.jspx"
								)%>">返回上一页</a>
	</body>
