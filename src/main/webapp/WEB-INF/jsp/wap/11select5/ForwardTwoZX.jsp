<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	List<String> list = CommonUtil.getBetCode("T01010","5");
    String deadline = "";
    String myria = (String)request.getAttribute("myria")==null?"":(String)request.getAttribute("myria");
    String thousand = (String)request.getAttribute("thousand")==null?"":(String)request.getAttribute("thousand");
    String zhuma = (String)request.getAttribute("zhuma")==null?"":(String)request.getAttribute("zhuma");
    String beishu ="";
    beishu = (String)request.getAttribute("beishu");
    if(beishu ==null) beishu="1";
    String addNumber = "";
    if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getAttribute("addNumber");
		if (addNumber==null) addNumber="1";
	}
    String twoDS ="";
    twoDS = (String)request.getAttribute("twoDS");
    if(twoDS ==null) twoDS="5";
    String err_msg = (String)request.getAttribute("err_msg");
%>
	<title>江西11选5前二直选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a><%
			out.print(CommonUtil.printChar());
		%>江西11选5<br/>
		<a href="/w/wap/11select5/optionOne.jspx">任选</a>|直选|<a 
		   href="/w/wap/11select5/ForwardTwoGroup.jspx">组选</a>|<a 
		   href="/w/wap/11select5/dantuoselection.jspx?type=2">胆拖</a><br/>
		前二直选|<a href="/wap/11select5/ForwardThreeZX.jspx">前三直选</a><br/>
		<%
			deadline = CommonUtil.getDeadline("T01010", 0);
			if (deadline == null)
			deadline = "";
		%> <%
 			if (deadline != null) {
 		%> <%
 			out.print(deadline);
 		%> <%}%>
 		<form action="/wap/11select5/ForwardTwoZX.jspx" method="post">
 		<input type="submit" value="刷新" />
 		</form><br /><a style="color: red">
		 <% if(err_msg!=null){
			out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
		   }
		%></a>
 		01-11选2-11个数字,两位个数之和不超过11,号码间无分隔,数字不可重复,小于10则补0,如选择2则填写02<br/>
 		自选<br/>
 		<form action="/11select5/qTwoByHand.jspx" method="post">
 		第一位：<input name="myria" type="text" emptyok="true"  size="20" maxlength="20" format="*N" tabindex="1" value="<%=myria %>" /><br/>
 		第二位：<input name="thousand" type="text" emptyok="true"  size="20" maxlength="20" format="*N" tabindex="1" value="<%=thousand %>" /><br/>
 		倍数：<input name="beishu" type="text" emptyok="true"  size="2" maxlength="2" format="*N" tabindex="1" value="<%=beishu %>" />倍(最多99倍)<br/>
 		追号：<input name="addNumber" type="text" emptyok="true"  size="2" maxlength="2" format="*N" tabindex="1" value="<%=addNumber %>" />期(最多99期,追1期即买当前期)<br/>
 		<input type="submit" value="提交投注" />
 		</form><br/>
		前2:投注号码与开奖号码前两位相同中130元<br/>	
		【最新开奖】<br/>	
		<%
			String code = "";
			for (int i = 0; i < list.size(); i++) {
			code = list.get(i);
		%> <%
 			out.print(code);
 		%><br />	 
		<%}%><br />	
</body>