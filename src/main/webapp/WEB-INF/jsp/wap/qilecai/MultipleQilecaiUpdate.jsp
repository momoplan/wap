<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%><%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	List<String> list = CommonUtil.getBetCode("F47102","5");
%>

	<title>七乐彩复式投注修改</title>
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
			String zhuFirst = (String)request.getAttribute("zhuFirst");
			String nzm = (String)request.getAttribute("nzm");
			String[] n = nzm.split(";");
			String newzhuma = (String)request.getAttribute("newzhuma");
			Integer amount = Integer.parseInt(request.getAttribute("amount").toString());
			String beishu = (String)request.getAttribute("beishu");
			String zhuma = (String)request.getAttribute("zhuma");
			String hangshuStr = (String)request.getAttribute("hangshu");
			int hangshu = Integer.parseInt(hangshuStr);
			//显示的注码的注数
			int zmLen = zhuma.split("\\^").length;
			String term = (String)request.getAttribute("term");
			String type = (String)request.getAttribute("type");
			String zhushuStr =(String) request.getAttribute("zhushuStr");
			String certErr = (String)request.getAttribute("certErr");
			request.removeAttribute("certErr");
			request.getSession().setAttribute("zhushuStr",zhushuStr);
			request.getSession().setAttribute("newzhuma",newzhuma);
			request.getSession().setAttribute("term",term);
		%>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
		<%
				if(type=="JXDS"||type.equals("JXDS")){%>
					<a href="<%=response.encodeURL(path+"/wap/qilecai/SingleAutoSelection.jspx")%>">七乐彩单式机选</a>
					<%} 
				if(type=="JXFS"||type.equals("JXFS")){%>
					<a href="<%=response.encodeURL(path+"/wap/qilecai/MultipleAutoSelection.jspx")%>">七乐彩复式机选</a><%} 
				if(type=="JXDD"||type.equals("JXDD")){%>
					<a href="<%=response.encodeURL(path+"/wap/qilecai/DingDanAutoSelection.jspx")%>">七乐彩定胆机选</a><%} 
				if(type=="ZXDS"||type.equals("ZXDS")){%>
					<a href="<%=response.encodeURL(path+"/wap/qilecai/SingleSelfSelection.jspx")%>">七乐彩单式自选</a><%} 
				if(type=="ZXFS"||type.equals("ZXFS")){%>
					<a href="<%=response.encodeURL(path+"/wap/qilecai/MultipleSelfSelection.jspx")%>">七乐彩复式自选</a><%} 
				if(type=="ZXDT"||type.equals("ZXDT")){%>
					<a href="<%=response.encodeURL(path+"/wap/qilecai/DantuoSelfSelection.jspx")%>">七乐彩胆拖自选</a><%} 
		%>
		<%
			out.print(CommonUtil.printChar());
		%>注码修改<br/>
		
		您的投注:<br/>
			七乐彩第<% out.print(term); %>期<br/>
			<form action="<%=response.encodeURL(path+"/qilecai/multipleAutoSelectionUpdateBet.jspx")%>" method="post">
			倍数:<input type="text" name="beishu" size="2" maxlength="2" value="<%=beishu %>"/>倍(最多50倍)<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>				
				<%} %>
			<%} %>
			追号:<input type="text" name="addNumber" size="2" maxlength="2" value="<%=addNumber %>"/>期<br/>
			<%
				Integer zhushu1 = 0;
				if (beishu.trim().equals("")||!beishu.matches("[1-9][0-9]")) {
					zhushu1 = zhushu/1;
				} else {
					if(beishu.matches("[1-9][0-9]{0,1}")){
						zhushu1 = zhushu/Integer.parseInt(beishu);
					}
					if(!beishu.matches("[1-9][0-9]{0,1}")){
						zhushu1 = zhushu/1;
					}
				} 
			%>
			投注号码:
			<br/>
			<%
				if (beishu.trim().equals("")) {
					beishu = "1";
				}
				if (addNumber.trim().equals("")) {
					addNumber = "0";
				}
				if(certErr==null){
					certErr="";
				}
			%><a style="color: red"><%=certErr %></a>
			<%for(int i=0;i<hangshu;i++){%>
				<input type="text" name="n<%=i%>" value="<%=n[i]%>" maxlength="<%=Integer.parseInt(request.getAttribute("geshuStr").toString())*3-1%>"/><br/>
			<%}%>	
			<%
				String geshuStr = (String)request.getAttribute("geshuStr");
			%>	
			
			<input type="hidden" name="geshuStr" value="<%=geshuStr %>"/>
			<%if(beishu.matches("[1,9][0-9]{0,1}")){%>
			<input type="hidden" name="yuanhangshu" value="<%=zhushu/Integer.parseInt(beishu)/Integer.parseInt(geshuStr) %>"/>
			<%} %>
			<%if(!beishu.matches("[1,9][0-9]{0,1}")){%>
			<input type="hidden" name="yuanhangshu" value="<%=zhushu/1/Integer.parseInt(geshuStr) %>"/>
			<%} %>
			<input type="hidden" name="hangshu" value="<%=hangshu %>"/>
			<input type="hidden" name="zhuma" value="<%=zhuma %>"/>
			<input type="hidden" name="term" value="<%=term %>"/>
			<input type="hidden" name="amount" value="<%=amount %>"/>
			<input type="hidden" name="zhushu" value="<%=zhushu %>"/>
			<input type="hidden" name="zhuFirst" value="<%=zhuFirst %>"/>
			<input type="hidden" name="zongzhushu" value="<%=zhushu %>"/>
			<input type="hidden" name="type" value="<%=type %>"/>
			<input type="hidden" name="nzm" value="<%=nzm %>"/>
			<input type="hidden" name="token" value="<%=transctionId %>"/>
			<input type="submit" value="确认修改"/>
			</form>
			
			<%
				if(type=="JXDS"||type.equals("JXDS")){ //单式机选
			%>
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<form action="<%=response.encodeURL(path+"/qilecai/singleAutoSelection.jspx")%>" method="post">
					<input type="hidden" name="beishu" value="<%=beishu %>"/>
					<input type="hidden" name="type" value="JXDS"/>
					<input type="hidden" name="zhushuStr" value="<%=zhushu1 %>"/>
					<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
					<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
					<input type="submit" value="重新选号"/>
				</form>
				<%} else { %>
				<form action="<%=response.encodeURL(path+"/qilecai/singleAutoSelection.jspx")%>" method="post">
					<input type="hidden" name="beishu" value="<%=beishu %>"/>
					<input type="hidden" name="type" value="JXDS"/>
					<input type="hidden" name="zhushuStr" value="<%=zhushu1 %>"/>
					<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
					<input type="submit" value="重新选号"/>
				</form>
				<%} %>
			<%} %>
			<%
				if(type=="JXFS"||type.equals("JXFS")){ //复式机选
				//	zhushu = Integer.parseInt(zhuFirst);
					String geshu=(String)request.getAttribute("geshuStr");
					int geshuInt = Integer.parseInt(geshu);
					if(zhushu == 0) {zhushu = geshuInt;}
					//request.getSession().setAttribute("zhuFirst",zhushu);
					request.getSession().setAttribute("geshu",geshu);
					if(!beishu.matches("[1-9][0-9]{0,1}")){
						beishu = "1";
					}
					if(!addNumber.matches("[1-9][0-9]{0,1}")){
						addNumber = "1";
					}
			%>
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<form action="<%=response.encodeURL(path+"/qilecai/multipleAutoSelection.jspx")%>" method="post">
				<input type="hidden" name="beishu" value="<%=beishu %>"/>
				<input type="hidden" name="type" value="JXFS"/>
				<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
				<input type="hidden" name="num" value="<%=geshu %>"/>
				<input type="hidden" name="zhushu" value="<%=zhuFirst %>"/>
				<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
				<input type="submit" value="重新选号"/>
				</form>
				<%} else { %>
				<form action="<%=response.encodeURL(path+"/qilecai/multipleAutoSelection.jspx")%>" method="post">
				<input type="hidden" name="beishu" value="<%=beishu %>"/>
				<input type="hidden" name="type" value="JXFS"/>
				<input type="hidden" name="num" value="<%=geshu %>"/>
				<input type="hidden" name="zhushu" value="<%=zhuFirst %>"/>
				<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
				<input type="submit" value="重新选号"/>
				</form>
				<%} %>
			
			<%} %>
			<%
				if(type=="JXDD"||type.equals("JXDD")){ //定胆机选
					String didan =(String)request.getAttribute("didan");
				request.getSession().setAttribute("didan",didan);
			%>
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
					<form action="<%=response.encodeURL(path+"/qilecai/dingDanAutoSelection.jspx")%>" method="post">
				<input type="hidden" name="beishu" value="<%=beishu %>"/>
				<input type="hidden" name="type" value="JXDD"/>
				<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
				<input type="hidden" name="didan" value="<%=didan %>"/>
				<input type="hidden" name="zhushuStr" value="<%=zhushu1 %>"/>
				<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
				<input type="submit" value="重新选号"/>
				</form>
				<%} else { %>
				<form action="<%=response.encodeURL(path+"/qilecai/dingDanAutoSelection.jspx")%>" method="post">
				<input type="hidden" name="beishu" value="<%=beishu %>"/>
				<input type="hidden" name="type" value="JXDD"/>
				<input type="hidden" name="didan" value="<%=didan %>"/>
				<input type="hidden" name="zhushuStr" value="<%=zhushu1 %>"/>
				<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
				<input type="submit" value="重新选号"/>
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