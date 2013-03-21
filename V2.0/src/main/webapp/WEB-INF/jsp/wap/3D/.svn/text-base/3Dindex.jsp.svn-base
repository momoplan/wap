<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,net.sf.json.JSONObject,net.sf.json.JSONArray"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.math.BigDecimal"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取配置文件开关
	String HM3DSwitch = rbint.getString("HM3DSwitch"); //取得3D合买 开关
	String type = "F47103";	
	JSONObject re = CommonUtil.getAutoZhuma(type);
	String newzhuma = re.getString("newzhuma");
    String betNO = re.getString("betNoStr");
	String deadline = CommonUtil.getDeadlineForHM("F47103", 0);
    if (deadline==null) deadline = "";

%><title>福彩3D购彩首页</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>"><%
			out.print(CommonUtil.printHall());
		%></a>
<%
 	out.print(CommonUtil.printChar());
 %>福彩3D<br />
天天开奖，中奖率高，免缴税。<br />
今日推荐号码: <%=newzhuma%><br/> 
<form action="/single3D/autoDetail.jspx" method="post" >
				<input type="hidden" name="betNo" value="<%=betNO%>" />
				<input type="hidden" name="newzhuma" value="<%=newzhuma%>" />
			<input type="submit" value="确认投注" />
			</form>
			<form action="/wap/3D/3Dindex.jspx" method="post" >
			<input type="submit" value="重新选号"/>
			</form>
			<br/><br/>
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DDirectSingle.jspx"
							)%>">直选投注</a>
<a
	href="<%=response.encodeURL(path
							+ "/wap/3D/3DDirectSelectionHeZhi.jspx" )%>">直选和值</a><br />
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DGroup3Single.jspx"
							)%>">组3单式</a>
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DGroup6Single.jspx"
							)%>">组6单式</a><br />
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DGroup3Multiple.jspx"
							)%>">组3复式</a>
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DGroup6Multiple.jspx"
							)%>">组6复式</a><br />
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DGroup3HeZhi.jspx"
							)%>">组3和值</a>
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DGroup6HeZhi.jspx"
							)%>">组6和值</a><br />
<a
	href="<%=response.encodeURL(path + "/wap/3D/3DDantuoMultiple.jspx"
							)%>">胆拖复式</a>
<a href="<%=response.encodeURL(path+"/wap/3D/3DSingleSelectSingleMultiple.jspx")%>">直选包号</a>						
  <br/>
		   <a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
</body>
