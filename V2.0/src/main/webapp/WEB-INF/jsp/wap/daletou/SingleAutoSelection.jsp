<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = request.getContextPath();
%>
<title>大乐透机选投注</title>
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
			String zhushuStr = (String)request.getAttribute("zhushuStr");
			if(zhushuStr==null) zhushuStr = "5";//默认5注
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
		<a href="/w/wap/daletou/SingleSelfSelection.jspx">自选</a>|机选<br/>
		单式机选|<a href="/wap/daletou/MultipleAutoSelection.jspx">复式机选</a><br/>
		<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red"><% if(message!=null){
					out.print(CommonUtil.printWarningMessage(message)+"<br/>");
			   }%></a>
			   
			   <form action="<%=response.encodeURL(path+"/daletou/showAutoBetDetails.jspx")%>" method="post" >
			     机选注数:
			   <select name="zhushuStr">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>注<br/>
			  倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
			  是否追加:
			<select name="ZJnum">
				<option value="2">否</option>
				<option value="3">是</option>
			</select>
			(追加后每注3元)<br/>
			 <%if (rbint.getString("array3addNumberSwitch").equals("1")) { %>
			追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
			    <input type="hidden" name="type" value="DSJX"/>
			    <input type="submit" value="提交投注"/>
			   
			   </form>
			<br/>  <%	List<String> list = CommonUtil.getBetCode("T01001","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %><br/>
		   <a href="<%=response.encodeURL(path + "/wap/daletou/DaletouIndex.jspx"
								)%>">返回上一页</a>
	</body>
