<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String dm1 = (String) request.getAttribute("dm1")==null?"":(String) request.getAttribute("dm1");
	String dm2 = (String) request.getAttribute("dm2")==null?"":(String) request.getAttribute("dm2");
	String dm3 = (String) request.getAttribute("dm3")==null?"":(String) request.getAttribute("dm3");
	String dm4 = (String) request.getAttribute("dm4")==null?"":(String) request.getAttribute("dm4");
	String dm5 = (String) request.getAttribute("dm5")==null?"":(String) request.getAttribute("dm5");
	String dm6 = (String) request.getAttribute("dm6")==null?"":(String) request.getAttribute("dm6");
	String dm7 = (String) request.getAttribute("dm7")==null?"":(String) request.getAttribute("dm7");
	String zhushu = (String) request.getAttribute("zhushu")==null?"1":(String) request.getAttribute("zhushu");
	String err_msg = (String)request.getAttribute("err_msg");
%>
	
	<title>七星彩定胆机选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
	out.print(CommonUtil.printChar());
%>七星彩<br />
 <a href="/w/wap/sevenStar/sevenStarByHand.jspx">自选</a>|<a 
		   href="/w/wap/sevenStar/SingleAutoSelection.jspx">机选</a>|定胆<br/>
		   定胆机选<br />
		<%
			String deadline = CommonUtil.getDeadline("T01009", 0);
			if (deadline==null) deadline = "";
		%>
		<%if (deadline!=null) { %>
			<%out.print(deadline); %>
		<%} %><br/>
		<a style="color: red"><% if(err_msg!=null){
			out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
		   }
		%></a>
		选择胆码:<br/>
		<form action="/sevenStar/DDSevenStar.jspx" method="post">
		①<select name="dm1" id="dm1" value="<%=dm1%>">
			<option value="-1" selected="selected">不选</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select><br/>
		②<select name="dm2" id="dm2" value="<%=dm2%>">
			<option value="-1" selected="selected">不选</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select><br/>
		③<select name="dm3" id="dm3" value="<%=dm3%>">
			<option value="-1" selected="selected">不选</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select><br/>
		④<select name="dm4" id="dm4" value="<%=dm4%>">
			<option value="-1" selected="selected">不选</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select><br/>
		⑤<select name="dm5" id="dm5" value="<%=dm5%>">
			<option value="-1" selected="selected">不选</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select><br/>
		⑥<select name="dm6" id="dm6" value="<%=dm6%>">
			<option value="-1" selected="selected">不选</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select><br/>	
		⑦<select name="dm7" id="dm7" value="<%=dm7%>">
			<option value="-1" selected="selected">不选</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select><br/>	
		注数:<input name="zhushu" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=zhushu%>"/>(最多99注)<br/>		
		<input type="submit" value="提交投注" />
		</form><br />
		<%	List<String> list = CommonUtil.getBetCode("T01009","5");  %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/> 
		<% } %>
		   <br/>
		   <a href="<%=response.encodeURL(path + "/wap/sevenStar/7StarIndex.jspx"
								)%>">返回上一页</a>
</body>