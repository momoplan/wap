<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger,java.math.BigDecimal"%>
<%@ page import="com.ruyicai.wap.bean.News,net.sf.json.JSONObject,net.sf.json.JSONArray"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取七乐彩开关
	String qilecaiDefine = rbint.getString("qilecaiDefine"); //取得七乐彩开关
	String HMQlcSwitch = rbint.getString("HMQlcSwitch"); //取得七乐彩开关
	String type = "F47102";	
	JSONObject re = CommonUtil.getAutoZhuma(type);
	String newzhuma = re.getString("newzhuma");
    String betNO = re.getString("betNoStr");
	String deadline = CommonUtil.getDeadlineForHM("F47102", 0);
    if (deadline==null) deadline = "";
%>
<title>七乐彩</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a>
<%
 	out.print(CommonUtil.printChar());
 %>七乐彩<br />
<%
if(qilecaiDefine.equals("0")||qilecaiDefine=="0")
{}else
{ %>百万大奖，每期都有！号称百万大奖生产线。<br />
今日推荐号码: <%=newzhuma%>
       <br/> 
       <form action="/qilecai/singleAuto.jspx" method="post">
        <input type="hidden" name="betNo" value="<%=betNO%>"/>
         <input type="hidden" name="type" value="JXDS"/>
          <input type="hidden" name="newzhuma" value="<%=newzhuma%>"/>
       <input type="submit" value="确认投注"/>
       </form>
    <form action="/wap/qilecai/QilecaiIndex.jspx" method="post">
    <input type="submit" value="重新选号"/>
    </form>
			<br/><br/>

<a href="<%=response.encodeURL(path+"/wap/qilecai/SingleAutoSelection.jspx")%>">单式机选</a>
			<a href="<%=response.encodeURL(path+"/wap/qilecai/SingleSelfSelection.jspx")%>">单式自选</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/qilecai/MultipleAutoSelection.jspx")%>">复式机选</a>
			<a href="<%=response.encodeURL(path+"/wap/qilecai/MultipleSelfSelection.jspx")%>">复式自选</a><br/>
			<a href="<%=response.encodeURL(path+"/wap/qilecai/DingDanAutoSelection.jspx")%>">定胆机选</a>
			<a href="<%=response.encodeURL(path+"/wap/qilecai/DantuoSelfSelection.jspx")%>">胆拖自选</a><br/>
			<%} %>
          <br/>
		   <a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
</body>	