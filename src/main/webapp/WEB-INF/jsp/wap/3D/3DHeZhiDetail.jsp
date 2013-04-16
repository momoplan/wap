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
			String term = (String)request.getAttribute("term");
			Integer amount = Integer.parseInt(request.getAttribute("amount").toString());
			Integer zhushu = Integer.parseInt(request.getAttribute("zhushu").toString());
			String beishu = (String)request.getAttribute("beishu").toString();
			String hezhi = (String)request.getAttribute("hezhi").toString();
			String pageType = (String)request.getAttribute("pageType").toString();
			String certErr = (String)request.getAttribute("certErr");
			
			request.getSession().setAttribute("zhushu",zhushu);
			request.getSession().setAttribute("hezhi",hezhi);
			request.getSession().setAttribute("pageType",pageType);
			request.getSession().setAttribute("term",term);
			
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
		<%
			if (pageType.trim().equals("directSelection"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DDirectSelectionHeZhi.jspx")%>">3D直选和值</a>
		<%
			}
		%>
		<%
			if (pageType.trim().equals("group3"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DGroup3HeZhi.jspx")%>">3D组3和值</a>
		<%
			}
		%>
		<%
			if (pageType.trim().equals("group6"))  {
		%>
			<a href="<%=response.encodeURL(path+"/wap/3D/3DGroup6HeZhi.jspx")%>">3D组6和值</a>
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
			和值:<br/>
			<%out.print(hezhi); %><br/>
			<%
				if (beishu.trim().equals("")) {
					beishu = "1";
				}
				if (addNumber.trim().equals("")) {
					addNumber = "0";
				}
			%><br/>
			<form action="<%=response.encodeURL(path+"/hezhi3D/bet.jspa")%>" method="post" >
				
				<input type="hidden"  name="beishu" value="<%=beishu %>" />
				<input type="hidden"  name="hezhi" value="<%=hezhi %>" />
				<input type="hidden"  name="zhushu" value="<%=zhushu %>" />
				<input type="hidden"  name="amount" value="<%=amount %>" />
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<input type="hidden"  name="addNumber" value="<%=addNumber %>" />
				<%} %>
				<input type="hidden"  name="pageType" value="<%=pageType %>" />
				
				<input type="hidden"  name="token" value="<%=transctionId %>" />
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
	   <%	List<String> list = CommonUtil.getBetCode("F47103","5"); %>
			【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %><br/>
</body>
