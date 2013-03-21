<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<title>排列3直选</title>

<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<body>

			<%
				ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
				//取得开奖号码 
				String addNumber = "";
				if (rbint.getString("addNumberSwitch").equals("1")) {
					addNumber = (String)request.getAttribute("addNumber");
					if (addNumber==null) addNumber="1";
				}
				String err_msg = (String)request.getAttribute("err_msg");
				String beishu_No = (String)request.getAttribute("beishu_No");
				String hundreds_No = (String)request.getAttribute("hundreds_No");
				String tens_No = (String)request.getAttribute("tens_No");
				String units_No = (String)request.getAttribute("units_No");
				if(beishu_No==null) beishu_No = "1";
				if (hundreds_No==null) hundreds_No = "";
				if (tens_No==null) tens_No = "";
				if (units_No==null) units_No = "";
				String deadline = CommonUtil.getDeadline("T01002", 0);
				if (deadline==null) deadline = "";
			%>
			<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
				out.print(CommonUtil.printHall());
			%></a><%
				out.print(CommonUtil.printChar());
			%>排列三<br/>
			直选|<a 
		   href="/w/wap/array3/Array3GroupHeZhi.jspx">组选</a>|<a 
		   href="/w/wap/array3/Array3Group3BH.jspx">组三</a>|<a 
		   href="/w/wap/array3/Array3Group6BH.jspx">组六</a><br/>
			直选复式|<a href="/wap/array3/Array3DirectSelectionHeZhi.jspx">直选和值</a><br/>
			 <form action="<%=response.encodeURL(path+"/array3/directSelectionSingleDetail.jspx")%>" method="post"> 
			数字0-9,每位数可输入1-10个号码<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red"><% if(err_msg!=null){out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");}%></a>
			    百位:<input name="hundreds_No" type="text" emptyok="true" maxlength="10" size="5" format="*N" value="<%=hundreds_No %>" tabindex="1"/>(如:3)<br/>
			    十位:<input name="tens_No" type="text" emptyok="true" maxlength="10" size="5" format="*N" value="<%=tens_No %>" tabindex="2"/>(如:4)<br/>
			    个位:<input name="units_No" type="text" emptyok="true" maxlength="10" size="5" format="*N" value="<%=units_No %>" tabindex="3"/>(如:5)<br/>
			    倍数:<input name="beishu_No" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishu_No %>" tabindex="4"/>(最多50倍)<br/>
			  <%if (rbint.getString("array3addNumberSwitch").equals("1")) { %>
			    追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			 (最高99期,默认追1期,即买当前期)<br/>
			 <%} %>
			 	<input  type="hidden" name="pageType" value="ZXFS"/>
				<input  type="submit" value="提交投注"/>
			</form>
			<br/>
			  <%	List<String> list = CommonUtil.getBetCode("T01002","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %><br/>
		   <a href="<%=response.encodeURL(path + "/wap/array3/ArrayIndex.jspx"
								)%>">返回上一页</a>
	</body>
