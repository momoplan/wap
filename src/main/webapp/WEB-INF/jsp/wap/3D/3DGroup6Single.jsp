<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%><title>3D组6单式</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
				if (addNumber==null) addNumber="1";
			}
			String err_msg = (String)request.getAttribute("err_msg");
			String beishuStr = (String)request.getAttribute("beishuStr");
			String zhuma = (String)request.getAttribute("zhuma");
			if(beishuStr==null) beishuStr = "1";
			if (zhuma==null) zhuma = "";
			String deadline = CommonUtil.getDeadline("F47103", 0);
			if (deadline==null) deadline = "";
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/buyLottery.jspx"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>福彩3D<br/>
		<a href="/w/wap/3D/3DDirectSingle.jspx">直选</a>|<a 
		   href="/w/wap/3D/3DGroup3Single.jspx">组三</a>|组六|<a 
		   href="/w/wap/3D/3DDantuoMultiple.jspx">胆拖</a><br/>
		组6单式|<a href="/wap/3D/3DGroup6Multiple.jspx">组6复式</a>|<a href="/wap/3D/3DGroup6HeZhi.jspx">组6和值</a><br/>
			数字0-9,任选3个,不能重复<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red"><% if(err_msg!=null){
			%>
			   <% out.print(CommonUtil.printWarningMessage(err_msg));%><br/>
				<% }
			%></a>
			<form action="<%=response.encodeURL(path+"/single3D/group6SingleDetail.jspx")%>" method="post" >
			数字:<input name="zhuma" type="text" emptyok="true" maxlength="3" size="3" format="*N" value="<%=zhuma %>" />(如:245)<br/>
			倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishuStr %>" />(最多50倍)<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
				<input type="hidden" name="pageType" value="group6Single" />
			<input type="submit" value="提交投注" />
			</form>
			<br/>
			  <%	List<String> list = CommonUtil.getBetCode("F47103","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>
		   <br/>
		    <a href="<%=response.encodeURL(path + "/wap/3D/3Dindex.jspx"
								)%>">返回上一页</a>
</body>
