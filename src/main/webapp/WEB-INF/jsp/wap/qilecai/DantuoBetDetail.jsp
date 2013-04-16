<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%><%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	List<String> list = CommonUtil.getBetCode("F47102","5");
%>
	<title>七乐彩投注确认</title>
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
			Integer zhushu =Integer.parseInt(request.getAttribute("zhushu").toString());
			String newdanma = (String)request.getAttribute("newdanma");
			String newtuoma = (String)request.getAttribute("newtuoma");
			request.getSession().setAttribute("newdanma",newdanma);
			request.getSession().setAttribute("newtuoma",newtuoma);
			Integer amount = Integer.parseInt(request.getAttribute("amount").toString());
			String beishu = (String)request.getAttribute("beishu");
			String zhuma = (String)request.getAttribute("zhuma");
			String term = (String)request.getAttribute("term");
			String type = (String)request.getAttribute("type");
			String certErr = (String)request.getAttribute("certErr");
			request.getSession().setAttribute("term",term);
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
		<a href="<%=response.encodeURL(path+"/wap/qilecai/DantuoSelfSelection.jspx")%>">七乐彩胆拖自选</a>
		<%
			out.print(CommonUtil.printChar());
		%>确认投注<br/>
		您的投注:
		<br/>
			七乐彩第<% out.print(term); %>期<br/>
			金额:<% out.print(amount); %>元<br/>
			注数:<% out.print(zhushu); %>注<br/>
			倍数:<% out.print(beishu); %>倍<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>
				追号:<% out.print(addNumber); %>期<br/>
				<%} %>
			<%} %>
			胆码:<br/>
			<% out.print(newdanma); %><br/>
			拖码:<br/>
			<% out.print(newtuoma); %><br/>
			<%
				if (beishu.trim().equals("")) {
					beishu = "1";
				}
				if (addNumber.trim().equals("")) {
					addNumber = "0";
				}
			%>
			<br/>
			<form action="<%=response.encodeURL(path+"/qilecai/bet.jspa")%>" method="post">
			
			<input type="hidden" name="zhushu" value="<%=zhushu %>"/>
			<input type="hidden" name="beishu" value="<%=beishu %>"/>
			<input type="hidden" name="amount" value="<%=amount %>"/>
			<input type="hidden" name="zhuma" value="<%=zhuma %>"/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
			<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
				<%} %>
			<input type="hidden" name="type" value="<%=type %>"/>
			<input type="hidden" name="token" value="<%=transctionId %>"/>
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
			<br/>
				【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %><br/>
		</body>