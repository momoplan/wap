<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = request.getContextPath();
%>
	<title>账户充值--支付宝充值</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
			out.print(CommonUtil.printChar());
		%>支付宝充值<br/><img src="<%=path%>/wap/images/ZFB.png" alt="" ></img><br/>
		（即时入账，无手续费）<br/>
			<%
				String message = (String)request.getAttribute("message");
				if(message!=null && message.trim().length()>0)
				{%>
					<a style="color: red"><% out.println(message); %></a>
				<%}
			%><br/>
			<form action="<%=request.getContextPath() %>/chargeWap/ZFBChargeWAP.jspa" method="POST">
				充值金额(整数):<input name="transaction_money" type="text" maxlength="3" size="3" format="*N" emptyok="true" value="100"/>元
				<br/>
					<input type="hidden" name="cardType" value="03"/>
					<input type="hidden" name="accesstype" value="W"/>
					<input type="hidden" name="bankId" value="zfb001"/>
					<input type="submit" value="充值" />
			</form><br/>
			<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">选择其它方式充值</a><br/>
			<br/>
			提醒:
<br/>1.支付前,请您确认您已是支付宝会员,如您还没有支付宝账户,请登录 www.alipay.com支付宝网站注册
<br/>2.手机支付宝将通过WAP或短信方式从您的支付宝账户扣款完成支付
<br/>3.手机支付宝支付金额每日最高500元
<br/>4.支付宝客服热线：<a href="wtai://wp/mc;057188156688">0571-88156688</a>
<br/>5.使用ucweb手机浏览器的用户，请升级到ucweb7.0以上。<a href="http://down2.uc.cn/?f=zhousx@riyicai2&amp;url=http%3A%2F%2Fwap.ruyicai.com&amp;title=%E5%A6%82%E6%84%8F%E5%BD%A9%E5%BD%A9%E7%A5%A8">ucweb7.2下载</a>
<br/><a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">>>更多充值方式选择</a><br/>
<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">充值首页</a>
</body>