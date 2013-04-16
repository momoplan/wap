<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger"%>
<%
	String path = request.getContextPath();
%>
	<title>账户充值--银联语音充值</title>
	<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
			out.print(CommonUtil.printChar());
		%>银联语音充值<br/>
			<%
				String phone = request.getAttribute("phone")==null?"":(String)request.getAttribute("phone");
				String message = (String)request.getAttribute("message");
				if(message!=null && message.trim().length()>0){out.println(message); }
			%><a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">购买彩票</a>
			<br/>1.您的电话<%=phone %>将收到中国银联02096585的来电, 请按照电话提示输入银行卡密码进行操作；
			<br/>2.银行卡及密码经中国银联验证后,将成功支付,您的帐户余额将增加相应金额,请及时查询;农业银行需要短信认证，请在接到短信检验码后及时回复
			<br/>3.如果您的手机没有收到02096585来电
			<br/>请查您的手机号是否正确或拨打银联客服电话<a href="wtai://wp/mc;4006577577">400-657-7577</a>查询
			<br/>4.请确保电话通讯信号畅通！
			<br/>5.首次使用该支付方式后，系统将默认绑定该银行卡作为您再次电话支付和申请提现的唯一专用卡;
			<br/><a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">>>马上去投注</a>
			<br/><a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">>>更多充值方式选择</a>
				<br/>
		</body>