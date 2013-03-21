<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%@ page import="java.util.*,net.sf.json.JSONObject"%>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取七乐彩开关
	String TCDefine = rbint.getString("TCDefine"); //取得体彩开关
	String type = "T01001";	
	JSONObject re = CommonUtil.getAutoZhuma(type);
	String newzhuma = re.getString("newzhuma");
    String betNO = re.getString("betNoStr");
	String deadline = CommonUtil.getDeadline("T01001", 0);
    if (deadline==null) deadline = "";

%>
<title>大乐透</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a>
<%
 	out.print(CommonUtil.printChar());
 %>大乐透<br />
<%
	if (TCDefine.equals("0") || TCDefine == "0") {
	} else {
%> 2元赢1000万，3元赢1600万！<br />
今日推荐号码: <%=newzhuma%>
       <br/> 
       <form action="/daletou/showBetDetails.jspx" method="post">
       	<input type="hidden" name="betNo" value="<%=betNO%>"/>
       	<input type="hidden" name="type" value="DSJX"/>
       	<input type="hidden" name="newzhuma" value="<%=newzhuma%>"/>
       	<input type="submit" value="确认投注"/>			
       </form>

		<form action="/wap/daletou/DaletouIndex.jspx">
			<input type="submit"  value="重新选号"/>
		</form>
			<br/><br/> 

<a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/SingleAutoSelection.jspx"
								)%>">单式机选</a>
<a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/SingleSelfSelection.jspx"
								)%>">复式自选</a><br />
<a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/MultipleAutoSelection.jspx"
								)%>">复式机选</a>
<a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/DantuoSelfSelection.jspx"
								)%>">胆拖自选</a><br />
<a
	href="<%=response.encodeURL(path
								+ "/wap/daletou/ShengXiaoSelection.jspx"
								)%>">12选2手选</a>
<br />
<%
	}
%>   <br/>
		   <a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
	</body>
