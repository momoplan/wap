<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>大乐透胆拖自选</title>
<body>

		<%
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			
			String addNumber = "";
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
				if (addNumber==null) addNumber="1";
			}
			String message = (String)request.getAttribute("message");
			String reddanma = (String)request.getAttribute("reddanma");
			String redtuoma = (String)request.getAttribute("redtuoma");
			String bluedanma = (String)request.getAttribute("bluedanma");
			String bluetuoma = (String)request.getAttribute("bluetuoma");
			if (reddanma==null) reddanma="";
			if (redtuoma==null) redtuoma="";
			if (bluedanma==null) bluedanma="";
			if (bluetuoma==null) bluetuoma="";
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
		<a href="/wap/daletou/SingleSelfSelection.jspx">复式自选</a>|胆拖自选|<a href="/wap/daletou/ShengXiaoSelection.jspx">12选2自选</a><br/>
		
			(号码间无空格,小于10则补0。如:选02球06球20球则填写020620。前区胆码和前区拖码从01-35中选，前区胆码和拖码总数至少6个，后区胆码从01-12中选。)<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red"><% if(message!=null){
					out.print(CommonUtil.printWarningMessage(message)+"<br/>");
			   }%></a>
			   <form action="<%=response.encodeURL(path+"/daletou/selfSelectionDantuoBetDetail.jspx")%>" method="post">
			   			前区胆码(0-4个):<br/>
			<input name="reddanma" type="text" emptyok="false" maxlength="8" size="10" format="*N" value="<%=reddanma %>" /><br/>
			前区拖码(胆+拖≥6个):<br/>
			<input name="redtuoma" type="text" emptyok="false" maxlength="20" size="10" format="*N" value="<%=redtuoma %>" /><br/>
			后区胆码(0-1个):<br/>
			<input name="bluedanma" type="text" emptyok="false" maxlength="2" size="10" format="*N" value="<%=bluedanma %>" /><br/>
			后区拖码(胆+拖≥2):<br/>
			<input name="bluetuoma" type="text" emptyok="false" maxlength="24" size="10" format="*N" value="<%=bluetuoma %>" /><br/>
			倍数:<input name="beishu" type="text" emptyok="false" maxlength="2" size="2" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
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
			
			<input type="hidden" name="type" value="ZXDT"/>
			<input type="submit" value="提交投注"/>
			   </form>
			<br/>
			  <%	List<String> list = CommonUtil.getBetCode("T01001","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>
		    <a href="<%=response.encodeURL(path + "/wap/daletou/DaletouIndex.jspx"
								)%>">返回上一页</a>
	</body>
