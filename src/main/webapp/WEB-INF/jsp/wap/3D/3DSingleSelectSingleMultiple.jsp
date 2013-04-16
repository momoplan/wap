<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%><title>投注信息</title>
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
			if(zhuma==null) zhuma = "";
			if (beishu==null) beishu = "1";
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
			<a href="/wap/3D/3DDirectSingle.jspx">直选投注</a>|<a href="/wap/3D/3DDirectSelectionHeZhi.jspx">直选和值</a>|直选包号<br/>
			    数字0-9,选3个或以上号码,不能重复.该包号不包括组三和豹子号<br/>
			<%if (deadline!=null) { %>
				<%out.print(deadline); %><br/>
			<%} %>
		    <a style="color: red"><% if(err_msg!=null){out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");}%></a>
		    <form action="<%=response.encodeURL(path+"/multiple3D/singleSelectSingleMultipleDetail.jspx")%>" method="post">
			 数字:<input name="zhuma" type="text" emptyok="true" maxlength="10" size="10" format="*N" value="<%=zhuma %>" />(如:2468)<br/>
		            倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=beishu %>" />(最多50倍)<br/>
		     <%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			 追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=addNumber %>" />期<br/>
			(最高99期,默认追1期,即买当前期)<br/>
			<%} %>
				<input type="hidden" name="pageType" value="singleSelectSingleMultipleDetail" />
			<input type="submit" value="提交投注" />
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
