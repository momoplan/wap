<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*,net.sf.json.JSONObject"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	String type = "T01009";
	JSONObject re = CommonUtil.getAutoZhuma(type);
	String newzhuma = re.getString("newzhuma");
	String betNO = re.getString("betNoStr");
	List<String> list = CommonUtil.getBetCode("T01009","5"); 
	String deadline = "";
%>
<%
	
%>
<title>七星彩</title>
<body>
 <a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">
<%
	out.print(CommonUtil.printHall());
%> </a> <%
 	out.print(CommonUtil.printChar());
 %>七星彩<br />
<%
	deadline = CommonUtil.getDeadline("T01009", 0);
	if (deadline == null)
		deadline = "";
%> <%
 	if (deadline != null) {
 %> <%
 	out.print(deadline);
 %> <%
 	}
 %> <br />
大奖高，小奖多，任选七个号码即可<br />
今日推荐号码: <%=newzhuma%> <br />
<form action="/sevenstarSingleAutoSelection/autoBetDetails.jspx" method="post" style="margin:0px;display:inline;">
<input type="hidden" name="type" value="DS" />
<input type="hidden" name="newzhuma" value="<%=newzhuma%>" />
<input type="submit" value="确认投注" />
</form>
<form action="/wap/sevenStar/7StarIndex.jspx" method="post" style="margin:0px;display:inline;">
<input type="submit" value="重新选号" />
</form><br/>
七星彩 <a
	href="<%=response.encodeURL(path
							+ "/wap/sevenStar/playMethIntr.jspx" )%>">玩法介绍</a><br />
<a
	href="<%=response.encodeURL(path
							+ "/wap/sevenStar/sevenStarByHand.jspx" )%>">自选投注</a>
<a
	href="<%=response.encodeURL(path
							+ "/wap/sevenStar/SingleAutoSelection.jspx"
							)%>">单式机选</a><br />
<a
	href="<%=response.encodeURL(path
							+ "/wap/sevenStar/MulipleAutoSelection.jspx"
							)%>">复式机选</a>
<a
	href="<%=response.encodeURL(path
							+ "/wap/sevenStar/ddSevenStar.jspx" )%>">定胆投注</a><br />
<br />

【最新开奖】<br />
<%
	String code = "";
	for (int i = 0; i < list.size(); i++) {
		code = list.get(i);
%> <%
 	out.print(code);
 %> <br />
<%
	}
%>
  <br/>
		   <a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
								)%>">返回上一页</a>
</body>
