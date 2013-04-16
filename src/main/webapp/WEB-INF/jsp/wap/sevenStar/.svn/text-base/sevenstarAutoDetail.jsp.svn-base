<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
				<%
					
				%>
				<title>七星彩投注确认</title>
				<body>
				
				<%
					Random rdm = new Random();
					int transctionId = rdm.nextInt();
					request.getSession().setAttribute(transctionId + "", "false");
					ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
					String certErr = null;
					if (request.getAttribute("certErr") != null) {
						certErr = (String) request.getAttribute("certErr");
					}
					//重新机选参数
					String dm1 = (String)request.getAttribute("dm1");
					String dm2 = (String)request.getAttribute("dm2");
					String dm3 = (String)request.getAttribute("dm3");
					String dm4 = (String)request.getAttribute("dm4");
					String dm5 = (String)request.getAttribute("dm5");
					String dm6 = (String)request.getAttribute("dm6");
					String dm7 = (String)request.getAttribute("dm7");
					//页面跳转参数
					String addNumber = "";
					if (rbint.getString("addNumberSwitch").equals("1")) {
						addNumber = (String) request.getAttribute("addNumber");
					}
					String zhushu = (String) request.getAttribute("zhushu");
					String newzhuma = (String) request.getAttribute("newzhuma");
					String zhuma = (String) request.getAttribute("zhuma");
					String amount = (String) request.getAttribute("amount");
					String beishu = (String) request.getAttribute("beishu");
					String term = (String) request.getAttribute("term");
					String type = (String) request.getAttribute("type");
					String autoMethod = request.getAttribute("autoMethod").toString();//机选方法
				%>
				<a href="/wap/wapindex.jspx">首页</a>-<a
					href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
											)%>">
				<%
					out.print(CommonUtil.printHall());
				%> </a> <%
				 	out.print(CommonUtil.printChar());
				 %> <%
				 	if (type.trim().equals("MA")) {
				 %> <a
					href="<%=response.encodeURL(path
												+ "/wap/sevenStar/SingleAutoSelection.jspx")%>">七星彩单式机选</a>
				<%
					}
				%> <%
				 	if (type.trim().equals("MS")) {
				 %> <a
					href="<%=response.encodeURL(path
												+ "/wap/sevenStar/MulipleAutoSelection.jspx")%>">七星彩复式机选</a>
				<%
					}
				%> <%
				 	if (type.trim().equals("DDAU")) {
				 %> <a
					href="<%=response.encodeURL(path
												+ "/wap/sevenStar/ddSevenStar.jspx" )%>">七星彩定胆投注</a>
				<%
					}
				%> <%
				 	out.print(CommonUtil.printChar());
				 %>确认投注<br /><%
					if (certErr != null)
						out.println("提示："+certErr.toString());
				%> 您的投注:<br />
				七星彩第<%
					out.print(term);
				%>期<br />
				金额: <%
					out.print(amount);
				%>元<br />
				注数: <%
					out.print(zhushu);
				%>注<br />
				<%
				 	if (beishu != null && !beishu.trim().equals("")) {
				 %>倍数: <%
					out.print(beishu);
				%>倍<br />
				<%
					}
				%><%
					if (rbint.getString("addNumberSwitch").equals("1")) {
				%> <%
				 	if (addNumber != null && !addNumber.trim().equals("")) {
				 %> 追号: <%
				 	out.print(addNumber);
				 %>期<br />
				<%
					}
				%> <%
				 	}
				 %> 投注号码:	<%
				 	String[] s = newzhuma.split("<br/>");
					if(s.length<=10){
				%>
				<form action="/wap/sevenStar/xiugaidd.jspx" method="post" style="margin:0px;display:inline;">
				<input type="hidden" name="zhushu" value="<%=zhushu%>" />
				<input type="hidden" name="term" value="<%=term%>" />
				<input type="hidden" name="zhuma" value="<%=zhuma %>" />
				<input type="hidden" name="amount" value="<%=amount%>" />
				<input type="hidden" name="type" value="<%=type%>" />
				<input type="hidden" name="autoMethod" value="<%=autoMethod%>"/>
				<input type="hidden" name="dm1" value="<%=dm1%>" />
				<input type="hidden" name="dm2" value="<%=dm2%>" />
				<input type="hidden" name="dm3" value="<%=dm3%>" />
				<input type="hidden" name="dm4" value="<%=dm4%>" />
				<input type="hidden" name="dm5" value="<%=dm5%>" />
				<input type="hidden" name="dm6" value="<%=dm6%>" />
				<input type="hidden" name="dm7" value="<%=dm7%>" />
				<input type="submit" value="修改" />
				</form>
				<%
					}
				%><br/>
				 <%
				 	out.print(newzhuma);
				 %><br />
				 
				<form action="/sevenStar/SSAutoBet.jspa" method="post" style="margin:0px;display:inline;">
				<input type="hidden" name="zhushu" value="<%=zhushu%>" />
				<input type="hidden" name="beishu" value="<%=beishu%>" />
				<input type="hidden" name="amount" value="<%=amount%>" />
				<input type="hidden" name="term" value="<%=term%>" />
				<input type="hidden" name="zhuma" value="<%=zhuma %>" />
				<input type="hidden" name="type" value="<%=type%>" />
				<input type="hidden" name="autoMethod" value="<%=autoMethod%>"/>
				<input type="hidden" name="token" value="<%=transctionId%>" />
				<%
					if (rbint.getString("addNumberSwitch").equals("1")) {
				%>
				<input type="hidden" name="addNumber" value="<%=addNumber%>" />
				<%
					}
				%>
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
				<%String act = "/sevenStar/"+autoMethod+".jspx"; %>
				<form action="<%=act %>" method="post" style="margin:0px;display:inline;">
				<input type="hidden" name="zhushu" value="<%=zhushu%>" />
				<input type="hidden" name="dm1" value="<%=dm1%>" />
				<input type="hidden" name="dm2" value="<%=dm2%>" />
				<input type="hidden" name="dm3" value="<%=dm3%>" />
				<input type="hidden" name="dm4" value="<%=dm4%>" />
				<input type="hidden" name="dm5" value="<%=dm5%>" />
				<input type="hidden" name="dm6" value="<%=dm6%>" />
				<input type="hidden" name="dm7" value="<%=dm7%>" />
				<input type="submit" value="重新选号" />
				</form>
				<br/>
				<%	List<String> list = CommonUtil.getBetCode("T01009","5");  %>
			【最新开奖】<br/>
		 	<% String   code ="";
		    	for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		 	%> 
		   	<%out.print(code) ;%> <br/> 
			<% } %>
<br/>
</body>