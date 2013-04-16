<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String tens = (String) request.getAttribute("tens");
		if(tens == null){tens = "";}
	String unit = (String) request.getAttribute("unit");
		if(unit == null){unit = "";}
	String beishu = (String) request.getAttribute("beishu");
		if(beishu == null){beishu = "1";}
	String addNumber = "";
	if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getAttribute("addNumber");
		if (addNumber==null) addNumber="1";
	}
	    String err_msg = (String)request.getAttribute("err_msg");
		//刷新页面截至日期
    	String types = request.getParameter("types");
    	if(types != null && "Star".equals(types)){
    	    tens = request.getParameter("tens")==null?"":request.getParameter("tens");
    		unit = request.getParameter("unit")==null?"":request.getParameter("unit");
    		beishu = request.getParameter("beishu")==null?"": request.getParameter("beishu");
    		addNumber = request.getParameter("addNumber")==null?"":request.getParameter("addNumber");
    		}
%>
<title>时时彩小大单双</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>时时彩<br/>
		<a href="/w/wap/cqshishicai/sscOneStar.jspx">一星</a>|<a 
		   href="/w/wap/cqshishicai/sscTowStar.jspx">二星</a>|<a 
		   href="/w/wap/cqshishicai/sscThreeStar.jspx">三星</a>|<a 
		   href="/wap/cqshishicai/sscFiveStar.jspx">五星</a>|大小单双<br/>
		小：0-4，大：5-9，单：13579，双：24680。<br/>
	<%
			String deadline = CommonUtil.getDeadline("T01007", 0);
			if (deadline==null) deadline = "数据正在更新···";
	%>
		<%if (deadline!=null) { %>
			<%out.print(deadline); %>
		 <%} %>

		 <form action="/wap/cqshishicai/xddsFiveStar.jspx" method="post" style="margin:0px;display:inline;">
		 	<input type="submit" value="刷新" />
		 	<input type="hidden" name="tens" value="<%=tens%>" />
		 	<input type="hidden" name="unit" value="<%=unit%>" />
		 	<input type="hidden" name="beishu" value="<%=beishu%>" />
		 	<input type="hidden" name="addNumber" value="<%=addNumber%>" />
		 	<input type="hidden" name="types" value="Star" />
		 </form><br/>
		 <a style="color: red"><%
			if (err_msg != null) {
				out.print(CommonUtil.printWarningMessage(err_msg) + "<br/>");
			}
		%></a>
		<form action="/shishicai/xddsFive.jspx" method="post">
		十位：
		<select name="tens" id="tens" >
			<option value="2" selected="selected">大</option>
			<option value="1">小</option>
			<option value="5">单</option>
			<option value="4">双</option>
		</select>	
		<br/>
		个位：
		<select name="unit" id="unit" >
			<option value="2">大</option>
			<option value="1" selected="selected">小</option>
			<option value="5">单</option>
			<option value="4">双</option>
		</select>	
		<br/>
		倍数：<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=beishu %>"/>（最多99倍）<br/>
		追号：<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=addNumber %>"/>（最多99期,追1期即买当前期）<br/>
			<input type="hidden" name="type" value="DD" />
			<input type="submit" value="确定投注" />
		</form><br/>
		大小单双：对“个位、十位”进行“大小、单双”投注。投注内容与开奖结果一致即中奖。奖金4元。<br/>
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