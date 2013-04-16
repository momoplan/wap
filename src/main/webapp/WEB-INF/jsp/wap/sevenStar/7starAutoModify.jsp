<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,java.util.regex.Matcher,java.util.regex.Pattern"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	//获取页面跳转参数
	String term = CommonUtil.getTerm("T01009");
	String zhuma = (String)request.getParameter("zhuma");
	String amount = (String)request.getParameter("amount");
	String zhushu = (String)request.getParameter("zhushu");
	String beishu = (String)request.getParameter("beishu");
	String type = (String)request.getParameter("type");
	String autoMethod = (String)request.getParameter("autoMethod");
	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getParameter("addNumber");
	}
	//获取action跳转参数
	String err_msg = (String)request.getAttribute("err_msg");
	String zhumaStr = (String)request.getAttribute("zhuma");
	String newbeishu = (String)request.getAttribute("beishu");
	String newaddNumber="";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		newaddNumber = (String)request.getAttribute("addNumber");
	}
%>
	
	<title>七星彩机选修改</title>
	<body>
 <a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
	out.print(CommonUtil.printChar());
%><a href="<%=response.encodeURL(path+"/wap/sevenStar/SingleAutoSelection.jspx")%>">七星彩单式机选</a><%
 	out.print(CommonUtil.printChar());
 %>注码修改<br />
 	      	  您的投注：<br/>
			七星彩第<%
				out.print(term);
			%>期<br />
			<a style="color: red"><%
				if(err_msg!=null){
					out.print(err_msg+"<br/>");
				}
			%></a>
		<%
		String b = "";
		if(beishu!=null){
			b=beishu;
		}else{
			b=newbeishu;
		}
		
	%>
		<form action="/sevenStar/singleAutoModify.jspx" method="post">
 		倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=b%>" />倍(最多99倍)<br />
	<%
		String d = "";
		if(newaddNumber!=null){
			d=newaddNumber;
		}else{
			d = addNumber;
		}
		if (rbint.getString("addNumberSwitch").equals("1")) {
	%> 
		追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=d%>" />期<br />
	<%
		}
	%> <%
		String xgzhuma = "";
		String x = "";
		if(zhuma!=null){
			x=zhuma;
		}else{
			x=zhumaStr;
		}
		String[] s = x.split(";");
		for(int i=0;i<s.length;i++){
			xgzhuma = s[i];
	%>
		<input name="i<%=i%>" type="text" emptyok="true"  size="13" maxlength="13" format="*N" tabindex="1" value="<%=xgzhuma%>"/><br/>
	<%	
		}
	%>
				<input type="hidden" name="type" value="<%=type%>" />
				<input type="hidden" name="term" value="<%=term %>" />
				<input type="hidden" name="amount" value="<%=amount%>" />
				<input type="hidden" name="autoMethod" value="<%=autoMethod%>" />
				<input type="hidden" name="zhushu" value="<%=zhushu%>" />
				<input type="submit" value="确认修改" />
	</form>
			<%
			Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
			Matcher matcher = pattern.matcher(b);
			if(matcher.matches()){
				}else{
					b="1";
				}
			Pattern pattern1 = Pattern.compile("[1-9]{1}[0-9]{0,1}");
			Matcher matcher1 = pattern.matcher(d);
			if(matcher1.matches()){
				 }else{
				  d="1";
				}
			%>
			<%
			String act = "/sevenStar/"+autoMethod+".jspx";
				if(rbint.getString("addNumberSwitch").equals("1")){
			%>
			<form action="<%=act %>" method="post">
			<input type="hidden" name="beishu" value="<%=beishu%>" />
			<input type="hidden" name="zhushu" value="<%=zhushu%>" />
			<input type="hidden" name="addNumber" value="<%=d%>" />
			<input type="hidden" name="type" value="<%=type%>" />
			<input type="submit" value="重新选号" />
			</form>
			<%
				}else{
			%>
			<form action="<%=act %>" method="post">
			<input type="hidden" name="beishu" value="<%=beishu%>" />
			<input type="hidden" name="zhushu" value="<%=zhushu%>" />
			<input type="hidden" name="type" value="<%=type%>" />
			<input type="submit" value="重新选号" />
			</form>
			<%
				}
			%><br/>
			<%	List<String> list = CommonUtil.getBetCode("T01009","5");  %>
			【最新开奖】<br/>
		 	<% String   code ="";
		    	for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		 	%> 
		   	<%out.print(code) ;%> <br/> 
			<% } %>
</body>