<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>3D直选复式</title>
<body>

			<%
				ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
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
				String deadline = CommonUtil.getDeadline("F47103", 0);
				if (deadline==null) deadline = "";
			%>
			<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
				out.print(CommonUtil.printHall());
			%></a><%
				out.print(CommonUtil.printChar());
			%>福彩3D<br/>
			直选|<a 
		   href="/w/wap/3D/3DGroup3Single.jspx">组三</a>|<a 
		   href="/w/wap/3D/3DGroup6Single.jspx">组六</a>|<a 
		   href="/w/wap/3D/3DDantuoMultiple.jspx">胆拖</a><br/>
			直选投注|<a href="/wap/3D/3DDirectSelectionHeZhi.jspx">直选和值</a>|<a href="/wap/3D/3DSingleSelectSingleMultiple.jspx">直选包号</a><br/>
			数字0-9,每位数可输入1-10个号码<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %><a style="color: red">
			<% if(err_msg!=null){out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");}%></a>
			<form action="<%=response.encodeURL(path+"/single3D/directSelectionSingleDetail.jspx")%>" method="post" >
			    百位:<input name="hundreds_No" type="text" emptyok="true" maxlength="10" size="5" format="*N" value="<%=hundreds_No %>" tabindex="1"/>(如:3)<br/>
			    十位:<input name="tens_No" type="text" emptyok="true" maxlength="10" size="5" format="*N" value="<%=tens_No %>" tabindex="2"/>(如:4)<br/>
			    个位:<input name="units_No" type="text" emptyok="true" maxlength="10" size="5" format="*N" value="<%=units_No %>" tabindex="3"/>(如:5)<br/>
			    倍数:<input name="beishu_No" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishu_No %>" tabindex="4"/>(最多50倍)<br/>
			  <%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			    追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			 (最高99期,默认追1期,即买当前期)<br/>
			 <%} %>
				<input type="hidden" name="pageType" value="ZXFS" />
			<input type="submit" value="提交投注" />
			</form>
			<br/>
			  <%	List<String> list = CommonUtil.getBetCode("F47103","5");; %>
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
