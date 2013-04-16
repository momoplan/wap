<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>

<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
    ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
    String hezhi = (String) request.getAttribute("hezhi");
    	if(hezhi == null){hezhi = "";}
    String beishu = (String) request.getAttribute("beishu");
    	if(beishu == null)  beishu = "1";
	String addNumber = "";
    if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getAttribute("addNumber");
		if (addNumber==null) addNumber="1";
	}
    String err_msg = (String)request.getAttribute("err_msg");
  //刷新页面截至日期
	String types = request.getParameter("types");
	if(types != null && "Star".equals(types)){
		hezhi = request.getParameter("hezhi")==null?"":request.getParameter("hezhi");
		beishu = request.getParameter("beishu")==null?"": request.getParameter("beishu");
		addNumber = request.getParameter("addNumber")==null?"":request.getParameter("addNumber");
		}
%>
<title>时时彩二星和值</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>时时彩<br/>
		<a href="/w/wap/cqshishicai/sscOneStar.jspx">一星</a>|二星|<a 
		   href="/w/wap/cqshishicai/sscThreeStar.jspx">三星</a>|<a 
		   href="/wap/cqshishicai/sscFiveStar.jspx">五星</a>|<a 
		   href="/w/wap/cqshishicai/xddsFiveStar.jspx">大小单双</a><br/>
	<a href="/wap/cqshishicai/sscTowStar.jspx">二星</a>|<a href="/wap/cqshishicai/sgTowStar.jspx">二星组选</a>|二星和值<br/>
		
		
	<%
			String deadline = CommonUtil.getDeadline("T01007", 0);
			if (deadline==null) deadline = "数据正在更新···";
	%>
		<%if (deadline!=null) { %>
			<%out.print(deadline); %>
		 <%} %>
		 <form action="/wap/cqshishicai/hezhiTowStar.jspx" method="post" style="margin:0px;display:inline;">
		 	<input type="submit" value="刷新" />
		 	<input type="hidden" name="hezhi" value="<%=hezhi%>" />
		 	<input type="hidden" name="beishu" value="<%=beishu%>" />
		 	<input type="hidden" name="addNumber" value="<%=addNumber%>" />
		 	<input type="hidden" name="types" value="Star" />
		 </form><br/>
		数字0-18任选一个号码<br/>
		<a style="color: red"><% if(err_msg!=null){
			out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
		   }
		%></a>
		<form action="/shishicai/hzTwoStar.jspx" method="post">
		填写和值:<input name="hezhi" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=hezhi %>"/><br/>
		倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=beishu %>"/>(最多99倍)<br/>
		追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=addNumber %>"/>(最多99期,追1期即买当前期)<br/>
			<input type="hidden" name="type" value="S2" />
			<input type="submit" value="确认投注"/>
		</form><br/>
		二星和值: 即十,个位号码之和<br/>
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