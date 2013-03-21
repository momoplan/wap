<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	List<String> list = CommonUtil.getBetCode("T01007","5");
    String deadline = "";
%>
	<title>重庆时时彩</title>
<body>
		<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>
		重庆时时彩<br/>
	<%
	        deadline = CommonUtil.getDeadline("T01007", 0);
			if (deadline==null) deadline = "";
	%>
		<%if (deadline!=null) { %>
			<%out.print(deadline); %>
		 <%} %>	 
		 <form action="/wap/cqshishicai/sscIndex.jspx" method="post" style="margin:0px;display:inline;">
		 	<input type="submit" value="刷新" />	 
		 </form>
		<br/>
			  <a href="<%=response.encodeURL(path+"/wap/cqshishicai/sscOneStar.jspx")%>">一星</a>
			  <a href="<%=response.encodeURL(path+"/wap/cqshishicai/sscTowStar.jspx")%>">二星</a>  
			  <a href="<%=response.encodeURL(path+"/wap/cqshishicai/sscThreeStar.jspx")%>">三星</a>
			  <a href="<%=response.encodeURL(path+"/wap/cqshishicai/sscFiveStar.jspx")%>">五星</a><br/>
			二星：  <a href="<%=response.encodeURL(path+"/wap/cqshishicai/sgTowStar.jspx")%>">组选</a>  <a href="<%=response.encodeURL(path+"/wap/cqshishicai/hezhiTowStar.jspx")%>">和值</a> <br/>
			五星：   <a href="<%=response.encodeURL(path+"/wap/cqshishicai/GeneralFiveStar.jspx")%>">通选</a>  <a href="<%=response.encodeURL(path+"/wap/cqshishicai/xddsFiveStar.jspx")%>">大小单双</a><br/>
		    【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>
		   <br/>
		   <a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
</body>