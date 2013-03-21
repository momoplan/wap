<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
    ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
    List<String> list = CommonUtil.getBetCode("T01009","5"); 
    String one = (String)request.getAttribute("one")==null?"":(String)request.getAttribute("one");
    String two = (String)request.getAttribute("two")==null?"":(String)request.getAttribute("two");
    String three = (String)request.getAttribute("three")==null?"":(String)request.getAttribute("three");
    String four = (String)request.getAttribute("four")==null?"":(String)request.getAttribute("four");
    String five = (String)request.getAttribute("five")==null?"":(String)request.getAttribute("five");
    String six = (String)request.getAttribute("six")==null?"":(String)request.getAttribute("six");
    String seven = (String)request.getAttribute("seven")==null?"":(String)request.getAttribute("seven");
    String beishu ="";
    beishu = (String)request.getAttribute("beishu");
    if(beishu ==null) beishu="1";
    String type =(String) request.getAttribute("type") == null?"":(String) request.getAttribute("type");
    String addNumber = "";
    if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getAttribute("addNumber");
		if (addNumber==null) addNumber="1";
	}
    String err_msg = (String)request.getAttribute("err_msg");
    //刷新页面截至日期
    String types = request.getParameter("types");
	if(types != null && "Star".equals(types)){
		one = request.getParameter("one")==null?"":request.getParameter("one");
		two = request.getParameter("two")==null?"":request.getParameter("two");
		three = request.getParameter("three")==null?"":request.getParameter("three");
		four = request.getParameter("four")==null?"":request.getParameter("four");
		five = request.getParameter("five")==null?"":request.getParameter("five");
		six = request.getParameter("six")==null?"":request.getParameter("six");
		seven = request.getParameter("seven")==null?"":request.getParameter("seven");
		beishu = request.getParameter("beishu")==null?"": request.getParameter("beishu");
		addNumber = request.getParameter("addNumber")==null?"":request.getParameter("addNumber");
	}
%>
	
	<title>七星彩自选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>七星彩<br/>
		自选|<a 
		   href="/w/wap/sevenStar/SingleAutoSelection.jspx">机选</a>|<a 
		   href="/w/wap/sevenStar/ddSevenStar.jspx">定胆</a><br/>
		  自选投注<br/>
		
	<%
			String deadline = CommonUtil.getDeadline("T01009", 0);
			if (deadline==null) deadline = "";
	%>
		<%if (deadline!=null) { %>
			<%out.print(deadline); %>
		 <%} %>
		 <br/>
		数字0-9选1个或多个号码,多个号码时无需分隔,如246<br/>
		<a style="color: red"><% if(err_msg!=null){
			out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
		   }
		%></a>
		填写号码：<br/>
		<form action="/sevenstar/sevenStarSubmit.jspx" method="post">
		① <input name="one" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=one %>"/><br/>
		② <input name="two" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=two %>"/><br/>
		③ <input name="three" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=three %>"/><br/>
		④ <input name="four" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=four %>"/><br/>
		⑤ <input name="five" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=five %>"/><br/>
		⑥ <input name="six" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=six %>"/><br/>
		⑦ <input name="seven" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=seven %>"/><br/>
		倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=beishu %>"/>(最多99倍)<br/>
		追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=addNumber %>"/>(最高99期,默认追1期,即买当前期)<br/>
		 <input type="hidden" name="type" value="7star" />
		<input type="submit" value="提交投注" />
		</form><br/>
		【最新开奖】<br/>
		 <% String   code ="";
		    for(int i=0;i<list.size();i++){ 
		    	code = list.get(i) ;
		  %> 
		   <%out.print(code) ;%> <br/>  
		   <% } %>
		     <br/>
		   <a href="<%=response.encodeURL(path + "/wap/sevenStar/7StarIndex.jspx"
								)%>">返回上一页</a>
</body>