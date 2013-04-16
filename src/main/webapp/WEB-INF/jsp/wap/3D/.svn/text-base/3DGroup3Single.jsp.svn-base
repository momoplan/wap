<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%><title>3D组3单式</title>
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
			String zhuma1 = (String)request.getAttribute("zhuma1");
			String zhuma2 = (String)request.getAttribute("zhuma2");
			if(beishuStr==null) beishuStr = "1";
			if (zhuma1==null) zhuma1 = "";
			if (zhuma2==null) zhuma2 = "";
			String deadline = CommonUtil.getDeadline("F47103", 0);
			if (deadline==null) deadline = "";
		%>
	<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>福彩3D<br/>
		<a href="/w/wap/3D/3DDirectSingle.jspx">直选</a>|组三|<a 
		   href="/w/wap/3D/3DGroup6Single.jspx">组六</a>|<a 
		   href="/w/wap/3D/3DDantuoMultiple.jspx">胆拖</a><br/>
		组3单式|<a href="/wap/3D/3DGroup3Multiple.jspx">组3复式</a>|<a href="/wap/3D/3DGroup3HeZhi.jspx">组3和值</a><br/>
			(数字0-9,每位数只能输入一个号码)<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
			<a style="color: red"><% if(err_msg!=null){out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");}%></a>
			<form action="<%=response.encodeURL(path+"/single3D/group3SingleDetail.jspx")%>" method="post" >
			出现2次的号码:<input name="zhuma2" type="text" emptyok="true" maxlength="1" size="2" format="*N" value="<%=zhuma2 %>" />(如:2)<br/>
			出现1次的号码:<input name="zhuma1" type="text" emptyok="true" maxlength="1" size="2" format="*N" value="<%=zhuma1 %>" />(如:3)<br/>
			倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishuStr %>" />(最多50倍)<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
				<input type="hidden" name="pageType" value="group3Single" />
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
