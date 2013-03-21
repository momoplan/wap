<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%><title>投注信息</title>
<body>

		<%
			Random rdm = new Random();
			int transctionId = rdm.nextInt();
			request.getSession().setAttribute(transctionId+"", "false");
			ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
			String addNumber = ""; 
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = (String)request.getAttribute("addNumber");
			}
			//页面跳转参数
			Integer zhushu = Integer.parseInt(request.getAttribute("zhushu").toString());
			String newzhuma = (String)request.getAttribute("newzhuma");
			Integer amount = Integer.parseInt(request.getAttribute("amount").toString());
			String beishu = (String)request.getAttribute("beishu");
			String zhuma = (String)request.getAttribute("zhuma");
			String term = (String)request.getAttribute("term");
			String pageType = (String)request.getAttribute("pageType");
			String certErr = (String)request.getAttribute("certErr");
			List<String> list = CommonUtil.getBetCode("F47103","5");
			request.getSession().setAttribute("term",term);
			request.getSession().setAttribute("newzhuma",newzhuma);
			//request.getSession().setAttribute("zhuma",zhuma);
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
		<%
			if (pageType.trim().equals("directSelectionSingle"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DDirectSingle.jspx")%>">3D直选投注</a>
		<%
			}
		%>
		<%
			if (pageType.trim().equals("ZXFS"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DDirectSingle.jspx")%>">3D直选投注</a>
		<%
			}
		%>
		<%
			if (pageType.trim().equals("autoSelection"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">3D机选单式</a>
		<%
			}
		%>
		<%
			if (pageType.trim().equals("group3Single"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DGroup3Single.jspx")%>">3D组3单式</a>
		<%
			}
		%>
		<%
			if (pageType.trim().equals("group6Single"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DGroup6Single.jspx")%>">3D组6单式</a>
		<%
			}
		%><%
			out.print(CommonUtil.printChar());
		%>确认投注<br/>
		
			您的投注:<br/>
			福彩3D第<% out.print(term); %>期<br/>
			金额:<% out.print(amount); %>元<br/>
			注数:<% out.print(zhushu); %>注<br/>
			倍数:<% out.print(beishu); %>倍<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>
				追号:<% out.print(addNumber); %>期<br/>
				<%} %>
			<%} %>
			投注号码:<%
		if (pageType.trim().equals("autoSelection")) {
	%>
	<%
		if (rbint.getString("addNumberSwitch").equals("1")) {
	%>
	<form action="/wap/3D/3DmodifyAutoSelection.jspx" method="post"
		style="margin: 0px; display: inline;">
		<input type="hidden" name="zhushu" value="<%=zhushu%>" /> <input
			type="hidden" name="zhuma" value="<%=newzhuma%>" /> <input
			type="hidden" name="amount" value="<%=amount%>" /> <input
			type="hidden" name="beishu" value="<%=beishu%>" /> <input
			type="hidden" name="pageType" value="<%=pageType%>" />
		<%
			if (rbint.getString("addNumberSwitch").equals("1")) {
		%>
		<input type="hidden" name="addNumber" value="<%=addNumber%>" />
		<%
			}
		%>
		<input type="submit" value="修改" />
		</form>
		<%
			}
			}
		%><br/>
			<% out.print(newzhuma); %>
			<%
				if (beishu.trim().equals("")) {
					beishu = "1";
				}
				if (addNumber.trim().equals("")) {
					addNumber = "0";
				}
			%><br/>
			<form action="<%=response.encodeURL(path+"/single3D/bet.jspa")%>" method="post" >
				
				<input type="hidden" name="zhushu" value="<%=zhushu %>" />
				<input type="hidden" name="beishu" value="<%=beishu %>" />
				<input type="hidden" name="amount" value="<%=amount %>" />
				<input type="hidden" name="zhuma" value="<%=zhuma %>" />
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<input type="hidden" name="addNumber" value="<%=addNumber %>" />
				<%} %>
				<input type="hidden" name="pageType" value="<%=pageType %>" />
				<input type="hidden" name="token" value="<%=transctionId %>" />
				<%if("1".equals(addNumber)){ %>
		<p style="color: red;">
			<input type="radio" checked="checked" value="daigou" name="buyways">普通投注
		</p>
		<p style="color: red;">
			<input type="radio" value="presented" name="buyways">我要赠送给他人
		</p>
		<p style="color: red;">
			<input type="radio" value="hemai" name="buyways">发起合买
		</p>
		<%}else{ %>
		<p style="color: red;">您已经选择了追期，无法应用赠彩、合买功能</p>
		<input type="hidden" name="buyways" value="daigou" />
		<%} %>
			<input type="submit" value="确认投注"/>
			</form>
			<%
			if (pageType.trim().equals("autoSelection"))  {
			%>
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				 <form action="/single3D/autoSelectionDetail.jspx" method="post">
		    <input type="hidden" name="zhushu" value="<%=zhushu%>" />
		    <input type="hidden" name="beishu" value="<%=beishu%>" />
		    <input type="hidden" name="addNumber" value="<%=addNumber%>" />
		    <input type="hidden" name="transctionId" value="<%=transctionId%>" />
		    <input type="submit" value="重新选号" />
		    </form>
				
				<%} else { %>
			<form action="/single3D/autoSelectionDetail.jspx" method="post">
		    <input type="hidden" name="zhushu" value="<%=zhushu%>" />
		    <input type="hidden" name="beishu" value="<%=beishu%>" />
		    <input type="hidden" name="transctionId" value="<%=transctionId%>" />
		    <input type="submit" value="重新选号" />
		    </form>
				<%} %>
			<%} %>
	
			<br/>
					【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %><br/>
</body>
