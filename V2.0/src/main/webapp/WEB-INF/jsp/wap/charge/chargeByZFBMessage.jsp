<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = request.getContextPath();
%>
<title>账户充值--支付宝充值</title>
<!-- 添加统计 -->
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/charge/chargeIndex.jspx"
							)%>">账户充值</a>
<%
	out.print(CommonUtil.printChar());
%>支付宝充值<br />
<img src="<%=path%>/wap/images/ZFB.png" alt=""></img><br />
充值成功！<br />
<a href="/userWap/findUserBalance.jspx">查询余额</a><br />
<a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">马上去投注</a><br />
<a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">双色球</a><br />
<a href="<%=response.encodeURL(path + "/wap/buyLottery.jspx"
							)%>">福彩3D</a>
</body>
