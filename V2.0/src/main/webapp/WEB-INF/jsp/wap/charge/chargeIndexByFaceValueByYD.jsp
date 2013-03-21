<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
	<title>账户充值--神州行充值</title>
<!-- 添加统计 -->
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
			out.print(CommonUtil.printChar());
		%>移动卡充值<br/><img src="<%=path%>/wap/images/YD.png" alt="" ></img><br/>
		           请确认您选择的是: <%=request.getParameter("facevalue")%>元充值卡<br/>
		    <form action="/chargeWapEpay/nineteenPayCardChargeWAP.jspa" method="post">
			序列号:<input name="cardNo" type="text" maxlength="50" emptyok="true" value=""/>
			<br/>
			充值密码:<input name="cardPassword" type="text" maxlength="50" emptyok="true" value=""/>
			<br/>
					<input type="hidden" name="money" value="<%=request.getParameter("facevalue")%>"/>
					<input type="hidden" name="allmoney" value="<%=request.getParameter("facevalue")%>"/>
					<input type="hidden" name="cardMode" value="SZX" />
					<!-- <input type="checkbox" name="checkbox" value="1" checked="checked"/>我要参加“单笔充值满百元即返现5%”的活动<a style = "color:#DE0201">（充值金额及赠送彩金只能用于购彩，不能提现，中奖奖金可以提现）</a>。 --><br />
					
					<input type="submit" value="充值" />
			</form>
		<br/>提醒:请确认您使用的是<%=request.getParameter("facevalue")%>元的充值卡,否则将导致金额丢失和支付失败.	
</body>