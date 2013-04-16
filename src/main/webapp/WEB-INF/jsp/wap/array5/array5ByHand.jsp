<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*,net.sf.json.JSONObject"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	String type = "T01011";
	JSONObject re = CommonUtil.getAutoZhuma(type);
	List<String> list = CommonUtil.getBetCode("T01011","5");
	String myria = (String)request.getAttribute("myria")==null?"":(String)request.getAttribute("myria");
    String thousand = (String)request.getAttribute("thousand")==null?"":(String)request.getAttribute("thousand");
    String hundred = (String)request.getAttribute("hundred")==null?"":(String)request.getAttribute("hundred");
    String ten = (String)request.getAttribute("ten")==null?"":(String)request.getAttribute("ten");
    String unit = (String)request.getAttribute("unit")==null?"":(String)request.getAttribute("unit");
	String deadline = "";
    String beishu ="";
    beishu = (String)request.getAttribute("beishu");
    if(beishu ==null) beishu="1";
    String addNumber = "";
    if (rbint.getString("addNumberSwitch").equals("1")) {
		addNumber = (String)request.getAttribute("addNumber");
		if (addNumber==null) addNumber="1";
	}
    String err_msg = (String)request.getAttribute("err_msg");
%>
<title>排列五手选</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %><a
	href="<%=response.encodeURL(path + "/wap/array5/array5Index.jspx"
							)%>">排列五</a><br />
自选|<a href="/w/wap/array5/singleAutoSelection.jspx">机选</a><br/>
自选投注<br />
<%
	deadline = CommonUtil.getDeadline("T01011", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %> <br />
 <a style="color: red"><% if(err_msg!=null){
			out.print(CommonUtil.printWarningMessage(err_msg)+"<br/>");
		   }
		%></a>
<form action="/array5/ByHandBetDetail.jspx" method="post">
数字0-9，每位最多输入十个数字，数字不可重复<br/>
万位:<input name="myria" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=myria %>" /><br/>
千位:<input name="thousand" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=thousand %>" /><br/>
百位:<input name="hundred" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=hundred %>" /><br/>
十位:<input name="ten" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=ten %>" /><br/>
个位:<input name="unit" type="text" emptyok="true"  size="10" maxlength="10" format="*N" tabindex="1" value="<%=unit %>" /><br/>
倍数:<input name="beishu" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=beishu %>"/>倍(最多99倍)<br/>
追号:<input name="addNumber" type="text" emptyok="true" maxlength="2" size="2" format="*N" tabindex="1" value="<%=addNumber %>"/>期(最高99期,默认追1期,即买当前期)<br/>
<input type="submit" value="提交投注" />
</form>		
		<br/>
【最新开奖】<br/>
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%> <%
 	out.print(code);
 %> <br />
<%
	}
%><br />
		   <a href="<%=response.encodeURL(path + "/wap/array5/array5Index.jspx"
								)%>">返回上一页</a>
</body>
