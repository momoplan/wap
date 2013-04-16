<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	//获取重新机选的参数
	String dm1 = request.getParameter("dm1");
	String dm2 = request.getParameter("dm2");
	String dm3 = request.getParameter("dm3");
	String dm4 = request.getParameter("dm4");
	String dm5 = request.getParameter("dm5");
	String dm6 = request.getParameter("dm6");
	String dm7 = request.getParameter("dm7");
	
	//获取详细页面的跳转参数
	String zhushu = (String) request.getParameter("zhushu");
	String zhuma = (String)request.getParameter("zhuma");
	String type = (String)request.getParameter("type");
	String term = (String)request.getParameter("term");
	String autoMethod = (String)request.getParameter("autoMethod");
	String amount = (String)request.getParameter("amount");
	//获取action的跳转参数
	String zhumaStr = (String)request.getAttribute("zhuma");
	String newamount = (String)request.getAttribute("amount");
	String newzhushu = (String)request.getAttribute("zhushu");
	String err_msg = (String)request.getAttribute("err_msg");
%>
	
	<title>七星彩定胆机选修改</title>
	<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
	out.print(CommonUtil.printChar());
%><a href="<%=response.encodeURL(path+"/wap/sevenStar/ddSevenStar.jspx")%>">七星彩定胆机选<%
	out.print(CommonUtil.printChar());
%></a>注码修改<br />
		您的投注：<br/>
 		七星彩第<%
					out.print(term);
				%>期<br />
				<a style="color: red"><%
					if(err_msg!=null){
						out.print(err_msg+"<br/>");
					}
				%></a>
		投注号码:<br/>	
		<form action="/sevenStar/xiugaiDd.jspx" method="post" style="margin:0px;display:inline;">	
		<%	
			String xgzhuma = "";
			String a = "";
			if(zhuma!=null){
				a = zhuma;
			}else{
				a = zhumaStr;
			}
			String[] s = a.split(";");
			for(int i=0;i<s.length;i++){
				xgzhuma=s[i];
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
				<input type="hidden" name="dm1" value="<%=dm1%>" />
				<input type="hidden" name="dm2" value="<%=dm2%>" />
				<input type="hidden" name="dm3" value="<%=dm3%>" />
				<input type="hidden" name="dm4" value="<%=dm4%>" />
				<input type="hidden" name="dm5" value="<%=dm5%>" />
				<input type="hidden" name="dm6" value="<%=dm6%>" />
				<input type="hidden" name="dm7" value="<%=dm7%>" />
		<input type="submit" value="确认修改"/>
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
		   <%out.print(code);%> <br/> 
		<% } %>
<br/>
</body>