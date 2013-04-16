<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<title>排列三组6单式</title>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<body>

			<%
				ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
				String addNumber = "";
				if (rbint.getString("addNumberSwitch").equals("1")) {
					addNumber = (String)request.getAttribute("addNumber");
					if (addNumber==null) addNumber="1";
				}
				String err_msg = (String)request.getAttribute("err_msg");
				String beishu = (String)request.getAttribute("beishu");
				String zhuma = (String)request.getAttribute("zhuma");
				if(beishu==null) beishu = "1";
				if (zhuma==null) zhuma = "";
				String deadline = CommonUtil.getDeadline("T01002", 0);
				if (deadline==null) deadline = "";
			%>
			<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
				out.print(CommonUtil.printHall());
			%></a><%
				out.print(CommonUtil.printChar());
			%>排列三<br/>
			<a href="/w/wap/array3/Array3DirectSingle.jspx">直选</a>|<a 
		   href="/w/wap/array3/Array3GroupHeZhi.jspx">组选</a>|<a 
		   href="/w/wap/array3/Array3Group3BH.jspx">组三</a>|组六<br/>
			组6单式|<a href="/wap/array3/Array3Group6BH.jspx">组6包号</a>|<a href="/wap/array3/Array3Group6HeZhi.jspx">组6和值</a><br/>
			数字0-9,任选3个,不能重复<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red"><% if(err_msg!=null){out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");}%></a>
			 <form action="<%=response.encodeURL(path+"/array3/array3Group6Detail.jspx")%>" method="post"> 
			 数字:<input name="zhuma" type="text" emptyok="true" maxlength="3" size="3" format="*N" value="<%=zhuma %>" tabindex="3"/>(如:235)<br/>
			    倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishu %>" tabindex="4"/>(最多50倍)<br/>
			  <%if (rbint.getString("array3addNumberSwitch").equals("1")) { %>
			    追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			 (最高99期,默认追1期,即买当前期)<br/>
			 <%} %>
				<input type="hidden" name="pageType" value="group6"/>
				<%if (rbint.getString("array3addNumberSwitch").equals("1")) { %>
				<%} %>
				<input  type="submit" value="提交投注"/>
			</form>
			<br/>  <%	List<String> list = CommonUtil.getBetCode("T01002","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>
		      <br/>
		   <a href="<%=response.encodeURL(path + "/wap/array3/ArrayIndex.jspx"
								)%>">返回上一页</a>
	</body>
