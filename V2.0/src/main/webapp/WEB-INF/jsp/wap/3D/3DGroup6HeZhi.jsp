<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%><title>3D组6和值</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
				if (addNumber==null) addNumber="1";
			}
			String message = (String)request.getAttribute("message");
			String hezhi = (String)request.getAttribute("hezhi");
			String beishu = (String)request.getAttribute("beishu");
			if (hezhi==null) hezhi="";
			if (beishu==null) beishu="1";
			String deadline = CommonUtil.getDeadline("F47103", 0);
			if (deadline==null) deadline = "";
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>福彩3D<br/>
		<a href="/w/wap/3D/3DDirectSingle.jspx">直选</a>|<a 
		   href="/w/wap/3D/3DGroup3Single.jspx">组三</a>|组六|<a 
		   href="/w/wap/3D/3DDantuoMultiple.jspx">胆拖</a><br/>
		<a href="/wap/3D/3DGroup6Single.jspx">组6单式</a>|<a href="/wap/3D/3DGroup6Multiple.jspx">组6复式</a>|组6和值<br/>
			 (3-24中选一个和值，如5)<br/>
			 <%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			 <%} %>
			 <a style="color: red"><%if (message!=null) { %>
				<% out.print("提示：" + message); %><br/>
			 <%} %></a>
			 <form action="<%=response.encodeURL(path+"/hezhi3D/heZhiDetail.jspx")%>" method="post">
			  和值数：<input name="hezhi" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=hezhi %>" tabindex="1"/><br/>
			   倍  数：<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishu %>" tabindex="4"/>(最多50倍)<br/>
			 <%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			  追  号：<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
		    (最高99期,默认追1期,即买当前期)<br/>
			<%} %>
				<input type="hidden"  name="pageType" value="group6" />
			<input type="submit" value="提交投注"/>
			</form>
			<br/>
			  <%	List<String> list = CommonUtil.getBetCode("F47103","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %><br/>
		    <a href="<%=response.encodeURL(path + "/wap/3D/3Dindex.jspx"
								)%>">返回上一页</a>
</body>
