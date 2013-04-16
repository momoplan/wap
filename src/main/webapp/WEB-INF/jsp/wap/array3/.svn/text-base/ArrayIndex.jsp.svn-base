<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*"%>
<%@ page import="com.ruyicai.wap.bean.News,net.sf.json.JSONArray,net.sf.json.JSONObject"%>
<title>排列三</title>

<%
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取七乐彩开关
	String TCDefine = rbint.getString("TCDefine");		//取得体彩开关
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	String type = "T01002";	
	JSONObject re = CommonUtil.getAutoZhuma(type);
	String newzhuma = re.getString("newzhuma");
    String betNO = re.getString("betNoStr");
	String deadline = CommonUtil.getDeadline("T01002", 0);
    if (deadline==null) deadline = "";
%>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a>
<%
 	out.print(CommonUtil.printChar());
 %>排列三<br />
<%
			if(TCDefine.equals("0")||TCDefine=="0")
			{}else
			{ %>体彩新玩法，天天开奖，天天中奖！<br/>
今日推荐号码: <%=newzhuma%>
       <br/> 
       <form action="/array3/autoDetail.jspx" method="post">
       		<input type="hidden" name="betNo" value="<%=betNO%>"/>
       		<input type="hidden" name="newzhuma" value="<%=newzhuma%>"/>
       		<input  type="submit" value="确认投注"/>
       </form>
       <form action="/wap/array3/ArrayIndex.jspx">
       		<input  type="submit" value="重新选号"/>
       </form>
			<br/><br/>
<a href="<%=response.encodeURL(path+"/wap/array3/Array3DirectSingle.jspx")%>">直选复式</a>
			<a href="<%=response.encodeURL(path+"/wap/array3/Array3DirectSelectionHeZhi.jspx")%>">直选和值</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/array3/Array3GroupHeZhi.jspx")%>">组选和值</a>
			<a href="<%=response.encodeURL(path+"/wap/array3/Array3Group6.jspx")%>">组6单式</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/array3/Array3Group3BH.jspx")%>">组3包号</a>
			<a href="<%=response.encodeURL(path+"/wap/array3/Array3Group6BH.jspx")%>">组6包号</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/array3/Array3Group3HeZhi.jspx")%>">组3和值</a>
			<a href="<%=response.encodeURL(path+"/wap/array3/Array3Group6HeZhi.jspx")%>">组6和值</a><br/>
			<%} %>
			  <br/>
		   <a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
	</body>
