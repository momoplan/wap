<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,java.util.regex.Matcher,java.util.regex.Pattern"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	//获取页面跳转参数
	String term = CommonUtil.getTerm("T01002");
	String zhuma = (String) request.getParameter("zhuma");
	String amount = (String) request.getParameter("amount");
	String zhushu = (String) request.getParameter("zhushu");
	String beishu = (String) request.getParameter("beishu");
	String type = (String) request.getParameter("type");
	String addNumber = "";
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
		addNumber = (String) request.getParameter("addNumber");
	}
	//获取action跳转参数
	String err_msg = (String) request.getAttribute("err_msg");
	String zhumaStr = (String) request.getAttribute("zhuma");
	String newbeishu = (String) request.getAttribute("beishu");
	String newaddNumber = "";
	if (rbint.getString("array3addNumberSwitch").equals("1")) {
		newaddNumber = (String) request.getAttribute("addNumber");
	}
%>
<title>排列三注码修改</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
	out.print(CommonUtil.printChar());
%>排列三<%
 	out.print(CommonUtil.printChar());
 %>注码修改<br />
 	      	  您的投注：<br/>
			排列三第<%
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
	<form action="/array3/modifyBetDetail.jspx" method="post" style="margin:0px;display:inline;">
 		倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=b%>" />倍(最多99倍)<br />
	<%
		String d = "";
		if(newaddNumber!=null){
			d=newaddNumber;
		}else{
			d = addNumber;
		}
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
	%> 
		追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" value="<%=d%>" />期<br />
	<%
		}
	%> <%
		String xgzhuma = "";
		String x = "";
		String[] s;
		if(zhuma!=null){
			x=zhuma;
			 s = x.split("<br/>");
		}else{
			x=zhumaStr;
			 s = x.split("<br/>");
		}
		
		for(int i=0;i<s.length;i++){
			xgzhuma = s[i];
	%>
		<input name="i<%=i%>" type="text" emptyok="true"  size="5" maxlength="5" format="*N" tabindex="1" value="<%=xgzhuma%>"/><br/>
	<%	
		}
	%>
	
				<input type="hidden" name="type" value="<%=type%>" />
				<input type="hidden" name="term" value="<%=term %>" />
				<input type="hidden" name="amount" value="<%=amount%>" />
				<input type="hidden" name="autoMethod" value="autoSelection" />
				<input type="hidden" name="method" value="modifyBetDetail" />
				<input type="hidden" name="zhushu" value="<%=zhushu%>" />
				<input type="submit" value="确认修改" />
	</form>
			<br/>
			<%	List<String> list = CommonUtil.getBetCode("T01002","5"); %>
			【最新开奖】<br/>
		 	<% String   code ="";
		    	for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		 	%> 
		   	<%out.print(code) ;%> <br/> 
			<% } %><br/>
</body>