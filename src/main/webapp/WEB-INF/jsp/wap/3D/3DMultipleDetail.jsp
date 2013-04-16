<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	List<String> list = CommonUtil.getBetCode("F47103","5");
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
			request.getSession().setAttribute("term",term);
			request.getSession().setAttribute("newzhuma",newzhuma);
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
		<%
			if (pageType.trim().equals("group3Multiple")) {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DGroup3Multiple.jspx")%>">3D组3复式</a>
		<%
			}
		%>
		<%if (pageType.trim().equals("group6Multiple")) {%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DGroup6Multiple.jspx")%>">3D组6复式</a>
		<%}%>
		<%if (pageType.trim().equals("singleSelectSingleMultipleDetail")) {%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DGroup6Multiple.jspx")%>">3D直选包号</a>
		<%}%>
		<%
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
			<%
				if (beishu.trim().equals("")) {
					beishu = "1";
				}
				if (addNumber.trim().equals("")) {
					addNumber = "0";
				}
			%>
			投注号码:<br/>
			<% out.print(newzhuma); %><br/>
				<form action="<%=response.encodeURL(path+"/multiple3D/bet.jspa")%>" method="post" >
				
				<input type="hidden" name="zhushu" value="<%=zhushu %>" />
				<input type="hidden" name="beishu" value="<%=beishu %>" />
				<input type="hidden" name="amount" value="<%=amount %>" />
				<input type="hidden" name="zhuma" value="<%=zhuma %>" />
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<input type="hidden" name="addNumber" value="<%=addNumber %>" />
				<%} %>
				<input type="hidden" name="pageType" value="<%=pageType %>" />
				<input type="hidden" name="certID" value="<%=request.getParameter("certID:noesc")%>" />
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
				
			<input type="submit" value="确认投注" />
			</form>
			<br/>
【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %><br/>
</body>
