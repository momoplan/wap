<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>3D胆拖复式</title>
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
			String beishu = (String)request.getAttribute("beishu");
			if (danma==null) danma="";
			if (tuoma==null) tuoma="";
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
		   href="/w/wap/3D/3DGroup3Single.jspx">组三</a>|<a 
		   href="/w/wap/3D/3DGroup6Single.jspx">组六</a>|胆拖<br/>
		胆拖复式<br/>
			胆码与拖码不能重复，总数不能小于4<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red"><%if (message!=null) { %>
				<% out.print("提示：" + message); %><br/>
			<%} %></a>
			<form action="<%=response.encodeURL(path+"/dantuoMultiple3D/dantuoMultipleDetail.jspx")%>" method="post">
			    胆码号码<br/>(数字0-9，选1-2个):<br/>
			  <input name="danma" type="text" emptyok="true" maxlength="2" size="9" format="*N" value="<%=danma %>" tabindex="1"/> (如:12)<br/>
			     拖码号码<br/>(数字0-9，选1-9个):<br/>
			  <input name="tuoma" type="text" emptyok="true" maxlength="18" size="9" format="*N" value="<%=tuoma %>" tabindex="1"/>(如:345)<br/>
			    倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishu %>" tabindex="4"/>(最多50倍)<br/>
			  <%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			    追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			  (最高99期,默认追1期,即买当前期)<br/>
			  <%} %>
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
		   <% } %>
		   <br/>
		    <a href="<%=response.encodeURL(path + "/wap/3D/3Dindex.jspx"
								)%>">返回上一页</a>
</body>
