<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>

<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());

%><title>时时彩五星</title>
<body>

	<%
	 	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	    String jixuan = "";
	    jixuan = (String)request.getAttribute("jixuan");
			if (jixuan==null) jixuan="5";
    	String myria = (String)request.getAttribute("myria");
    	if(myria==null)myria="";
    	String thousand = (String)request.getAttribute("thousand");
    	if(thousand==null)thousand="";
    	String hundred = (String)request.getAttribute("hundred");
    	if(hundred==null)hundred="";
 		String tens = (String)request.getAttribute("tens");
 		if(tens==null)tens="";
    	String unit = (String)request.getAttribute("unit");
    	if(unit==null)unit="";
   	 	String beishu = (String)request.getAttribute("beishu");
    	if(beishu==null)beishu="1";
		String addNumber = "";
    	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getAttribute("addNumber");
			if (addNumber==null) addNumber="1";
		}
    	String err_msg = (String)request.getAttribute("err_msg");
    	//刷新页面截至日期
    	String types = request.getParameter("types");
    	if(types != null && "Star".equals(types)){
    		myria = request.getParameter("myria")==null?"":request.getParameter("myria");
    		thousand = request.getParameter("thousand")==null?"":request.getParameter("thousand");
    		hundred = request.getParameter("hundred")==null?"":request.getParameter("hundred");
    	    tens = request.getParameter("tens")==null?"":request.getParameter("tens");
    		unit = request.getParameter("unit")==null?"":request.getParameter("unit");
    		beishu = request.getParameter("beishu")==null?"": request.getParameter("beishu");
    		addNumber = request.getParameter("addNumber")==null?"":request.getParameter("addNumber");
    		}
	%>
		 <a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>时时彩<br/>
		<a href="/w/wap/cqshishicai/sscOneStar.jspx">一星</a>|<a 
		   href="/w/wap/cqshishicai/sscTowStar.jspx">二星</a>|<a 
		   href="/w/wap/cqshishicai/sscThreeStar.jspx">三星</a>|五星|<a 
		   href="/w/wap/cqshishicai/xddsFiveStar.jspx">大小单双</a><br/>
		五星|<a href="/wap/cqshishicai/GeneralFiveStar.jspx">五星通选</a><br/>
		<a href="/w/wap/cqshishicai/sscFiveStar.jspx">自选</a>|机选<br/>
	<%
			String deadline = CommonUtil.getDeadline("T01007", 0);
			if (deadline==null) deadline = "数据正在更新···";
	%>
		<%if (deadline!=null) { %>
			<%out.print(deadline); %>
		 <%} %>
		 
		 <form action="/wap/cqshishicai/sscFiveStarAuto.jspx" method="post" style="margin:0px;display:inline;">
		 	<input type="submit" value="刷新" />
		 	<input type="hidden" name="myria" value="<%=myria%>" />
		 	<input type="hidden" name="thousand" value="<%=thousand%>" />
		 	<input type="hidden" name="hundred" value="<%=hundred%>" />
		 	<input type="hidden" name="tens" value="<%=tens%>" />
		 	<input type="hidden" name="unit" value="<%=unit%>" />
		 	<input type="hidden" name="beishu" value="<%=beishu%>" />
		 	<input type="hidden" name="addNumber" value="<%=addNumber%>" />
		 	<input type="hidden" name="types" value="Star" />
		 </form><br/>
		<a style="color: red"><% if(err_msg!=null){
			out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
		   }
		%></a>
		<form action="/shishicaiauto/autoFive.jspx" method="post">
		机选:<input name="jixuan" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=jixuan %>"/>注（1-99注）<br/>
		倍数:<input name="beishu" type="text" maxlength="2" size="2" value="<%= beishu%>" />倍(最多99倍)<br/>
		追号:<input name="addNumber" type="text" maxlength="2" size="2" value="<%= addNumber%>" />期(最多99期,追1期即买当前期) <br/>	
		<input type="hidden" name="type" value="5D" />
			<input type="submit" value="确认机选" />
		</form><br/>
		五星：5位投注号码与开奖号码全部相同（即万、千、百、十、个位）单注奖金100,000元<br/>
		【最新开奖】<br/>
		 <% 
		 	List<String> list = CommonUtil.getBetCode("T01007","5");
		 	String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>	
		       <br/>
		   <a href="<%=response.encodeURL(path + "/wap/cqshishicai/sscIndex.jspx"
								)%>">返回上一页</a>
</body>