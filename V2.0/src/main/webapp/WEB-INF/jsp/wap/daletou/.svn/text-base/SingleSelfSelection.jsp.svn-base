<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>大乐透复式自选</title>
<body>

		<%
		ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = (String)request.getAttribute("addNumber");
			if (addNumber==null) addNumber="1";
		}
		String err_msg = (String)request.getAttribute("err_msg");
		String redNumbers = (String)request.getAttribute("redNumbers");
		if(redNumbers==null) redNumbers = "";
		
		String blueNumbers = (String)request.getAttribute("blueNumbers");
		if(blueNumbers==null) blueNumbers = "";
		
		String multNo = (String)request.getAttribute("multNo");
		if(multNo==null) multNo = "1";
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
		复式自选|<a href="/wap/daletou/DantuoSelfSelection.jspx">胆拖自选</a>|<a href="/wap/daletou/ShengXiaoSelection.jspx">12选2自选</a><br/>
			号码间无空格,小于10则补0。如:选02球06球20球则填写020620。<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red">
			<% if(err_msg!=null){
					out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
			   }%></a>
			   
			   <form action="<%=response.encodeURL(path+"/daletou/showSelfBetDetail.jspx")%>" method="post">
			   前区:01-35选5-15个:<br/><input name="redNumbers" type="text" emptyok="false" maxlength="34" size="12" format="*N" value="<%=redNumbers %>" /><br/>
			后区:01-12选2-12个:<br/><input name="blueNumbers" type="text" emptyok="false" maxlength="24" size="12" format="*N" value="<%=blueNumbers %>" /><br/>(如:1012)<br/>
			倍数:<input name="multNo" type="text" emptyok="false" maxlength="2" size="2" format="*N" value="<%=multNo %>" />(最多50倍)<br/>
			是否追加：
			<select name="ZJnum">
				<option value="2">否</option>
				<option value="3">是</option>
			</select>
			(追加后每注3元)<br/>
			<%if (rbint.getString("array3addNumberSwitch").equals("1")) { %>
			追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
			
			<input type="hidden" name="type" value="FSZX"/>
			<input type="submit"  value="提交投注"/>
			   </form>
			
			<br/><%	List<String> list = CommonUtil.getBetCode("T01001","5"); %>
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
