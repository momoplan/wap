<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	List<String> list = CommonUtil.getBetCode("F47102","5");
%>
<title>七乐彩投注确认</title>
<body>

		<%
			String didan = (String)request.getAttribute("didan");
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
			String newzhuma = (String)request.getAttribute("newzhuma");
			//对新注码newzhuma进行格式转换
			String nzm = newzhuma.replace("<br/>",";");
			Integer amount = Integer.parseInt(request.getAttribute("amount").toString());
			String beishu = (String)request.getAttribute("beishu");
			String zhuma = (String)request.getAttribute("zhuma");
			String term = (String)request.getAttribute("term");
			String type = (String)request.getAttribute("type");
			String zhushuStr =(String) request.getAttribute("zhushuStr");
			String certErr = (String)request.getAttribute("certErr");
			request.removeAttribute("certErr");
			request.getSession().setAttribute("zhushuStr",zhushuStr);
			request.getSession().setAttribute("newzhuma",newzhuma);
			request.getSession().setAttribute("term",term);
			String zsStr ="";
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
		%>确认投注<br/>
	
		您的投注:<br/>
			七乐彩第<% out.print(term); %>期<br/>
			金额:<% out.print(amount); %>元<br/>
			注数:<% out.print(zhushu); %>注<br/>
			倍数:<% out.print(beishu); %>倍<br/>
			<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<%if (addNumber!=null&&!addNumber.trim().equals("")) { %>
				追号:<% out.print(addNumber); %>期<br/>
				<%} %>
			<%} %>
			<%
				Integer zhushu1 = 0;
				if (beishu.trim().equals("")) {
					zhushu1 = zhushu/1;
				} else {
					zhushu1 = zhushu/Integer.parseInt(beishu);
				}
			%>			
			投注号码:
			<%if(type=="JXFS"||type.equals("JXFS")){
				
				zsStr = (String)request.getAttribute("zhuFirst");
				String geshuStr = (String)request.getAttribute("geshu");
				//zsInt = Integer.parseInt(zsStr);
				if(Integer.parseInt(zsStr)<11){%>
				<form action="<%=response.encodeURL(path+"/qilecai/multipleAutoSelectionUpdate.jspx")%>" method="post">
					<input type="hidden" name="zhuFirst" value="<%=zsStr %>"/>
					<input type="hidden" name="zongzhushu" value="<%=zhushu %>"/>
					<input type="hidden" name="geshuStr" value="<%=geshuStr %>"/>
					<input type="hidden" name="nzm" value="<%=nzm %>"/>
					<input type="hidden" name="term" value="<%=term %>"/>
					<input type="hidden" name="beishu" value="<%=beishu %>"/>
					<input type="hidden" name="amount" value="<%=amount %>"/>
					<input type="hidden" name="zhuma" value="<%=zhuma %>"/>
					<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
					<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
					<%} %>
					<input type="hidden" name="type" value="<%=type %>"/>
					<input type="hidden" name="token" value="<%=transctionId %>"/>
					<input type="submit" value="修改"/>
				</form>
				<%}			
			}%>						
			<%if(type=="JXDS"||type.equals("JXDS")||type=="JXDD"||type.equals("JXDD")){
				if(Integer.parseInt(zhushuStr)<11){%>
					<form action="<%=response.encodeURL(path+"/qilecai/singleAutoSelectionUpdate.jspx")%>" method="post">
					<input type="hidden" name="zhushuStr" value="<%=zhushuStr %>"/>
					<input type="hidden" name="zhushu" value="<%=zhushu %>"/>
					<input type="hidden" name="didan" value="<%=didan %>"/>
					<input type="hidden" name="nzm" value="<%=nzm %>"/>
					<input type="hidden" name="term" value="<%=term %>"/>
					<input type="hidden" name="beishu" value="<%=beishu %>"/>
					<input type="hidden" name="amount" value="<%=amount %>"/>
					<input type="hidden" name="zhuma" value="<%=zhuma %>"/>
					<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
					<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
					<%} %>
					<input type="hidden" name="type" value="<%=type %>"/>
					<input type="hidden" name="token" value="<%=transctionId %>"/>
					<input type="submit" value="修改"/>
				</form>			
			
				<%}			
			}%>																		
			<br/>
			<%
				if (beishu.trim().equals("")) {
					beishu = "1";
				}
				if (addNumber.trim().equals("")) {
					addNumber = "0";
				}
			%>
			<% out.print(newzhuma); %>
			<br/>
			<form action="<%=response.encodeURL(path+"/qilecai/bet.jspa")%>" method="post">
					
					<input type="hidden" name="zhushu" value="<%=zhushu %>"/>
					<input type="hidden" name="didan" value="<%=didan %>"/>
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
					zhushu = Integer.parseInt(request.getAttribute("zhuFirst").toString());
					String geshu=(String)request.getAttribute("geshu");
					int geshuInt = Integer.parseInt(geshu);
					if(beishu.matches("[1-9][0-9]{0,1}")){
						beishu = beishu;
					}
					if(!beishu.matches("[1-9][0-9]{0,1}")){
						beishu = "1";
					}
					request.getSession().setAttribute("zhuFirst",zhushu);
					request.getSession().setAttribute("geshu",geshu);
			%>
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<form action="<%=response.encodeURL(path+"/qilecai/multipleAutoSelection.jspx")%>" method="post">
				<input type="hidden" name="beishu" value="<%=beishu %>"/>
				<input type="hidden" name="type" value="JXFS"/>
				<input type="hidden" name="num" value="<%=geshu %>"/>
				<input type="hidden" name="zhushu" value="<%=zsStr %>"/>
				<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
				<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
				<input type="submit" value="重新选号"/>
				</form>
				<%} else { %>
				<form action="<%=response.encodeURL(path+"/qilecai/multipleAutoSelection.jspx")%>" method="post">
				<input type="hidden" name="beishu" value="<%=beishu %>"/>
				<input type="hidden" name="type" value="JXFS"/>
				<input type="hidden" name="num" value="<%=geshu %>"/>
				<input type="hidden" name="zhushu" value="<%=zsStr %>"/>
				<input type="hidden" name="transctionId" value="<%=transctionId %>"/>
				<input type="submit" value="重新选号"/>
				</form>
				<%} %>
			
			<%} %>
			<%
				if(type=="JXDD"||type.equals("JXDD")){ //定胆机选
					didan =(String)request.getAttribute("didan");
				request.getSession().setAttribute("didan",didan);
			%>
				<%if (rbint.getString("addNumberSwitch").equals("1")) { %>
				<form action="<%=response.encodeURL(path+"/qilecai/dingDanAutoSelection.jspx")%>" method="post">
				<input type="hidden" name="beishu" value="<%=beishu %>"/>
				<input type="hidden" name="type" value="JXDD"/>
				<input type="hidden" name="didan" value="<%=didan %>"/>
				<input type="hidden" name="zhushuStr" value="<%=zhushu1 %>"/>
				<input type="hidden" name="addNumber" value="<%=addNumber %>"/>
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
