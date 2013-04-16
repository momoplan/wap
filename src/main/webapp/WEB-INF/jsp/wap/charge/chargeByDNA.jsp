<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger,net.sf.json.JSONObject,net.sf.json.JSONArray"%>
<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	request.getSession().setAttribute(transctionId+"", "false");
	String path = request.getContextPath();
	String mobile = request.getAttribute("mobile")==null?"":(String)request.getAttribute("mobile");
	String transaction_money = request.getAttribute("transaction_money")==null?"100":String.valueOf(request.getAttribute("transaction_money"));
	String bindState = request.getAttribute("BindState")==null?"":(String)request.getAttribute("BindState");
	String card_no = request.getAttribute("card_no")==null?"":(String)request.getAttribute("card_no");
	JSONObject userinfo =request.getAttribute("userinfo")==null?null:(JSONObject)request.getAttribute("userinfo");
	if ((mobile==null||mobile.trim().equals(""))&&userinfo!=null)mobile=userinfo.getString("mobileid");
		
%>
	
<title>账户充值--银联语音充值</title>
	<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
				out.print(CommonUtil.printChar());
			%>银联语音充值<br/>
			<img src="<%=path%>/wap/images/DNA.jpg" alt="" ></img><br/>
			
				<form action="<%=response.encodeURL(path+"/chargeWap/DNAChargeWAP.jspa")%>" method="post">
（即时入账，无手续费）<br/>
			<a style="color: red"><%
				String message = (String)request.getAttribute("message");
				if(message!=null && message.trim().length()>0)
				{%>
					<% out.print("<br/>"+CommonUtil.printWarningMessage(message)+"<br/>"); %>
				<%}
			%></a>
			<br/>充值金额:
			<input name="transaction_money" type="text" maxlength="10" size="8" format="*N" emptyok="true" value="<%=transaction_money %>"/>
			<br/>银行卡号:
			<%
				if("1".equals(bindState)){
					out.print(card_no);%>
					<input name="card_no" type="hidden" size="20" format="*N" emptyok="true" value="<%=card_no %>"/><br/>
					<%
				}else{   %>
			<input name="card_no" type="text" size="20" format="*N" emptyok="true" value="<%=card_no %>"/><br/>
			(招行/农行/浦发/深发/华夏/兴业银行的借记卡)
			<% }%><br/>
			电话号码:<input name="phone" type="text" size="20" format="*N" emptyok="true" value="<%=mobile %>"/><br/>
			<input type="hidden" name="cardType" value="01"/>
			<input type="hidden" name="accesstype" value="W"/>
			<input type="hidden" name="bindState" value="<%=bindState %>"/>
			<input type="hidden" name="bankId" value="dna001"/>
			<input type="hidden" name="token" value="<%=transctionId %>"/>
			<!--  <input type="checkbox" name="checkbox" value="1" checked="checked"/>我要参加“单笔充值满百元即返现5%”的活动<a style = "color:#DE0201">（充值金额及赠送彩金只能用于购彩，不能提现，中奖奖金可以提现）</a>。--><br />
			<input type="submit" value="提交信息"/>
			</form>
		<br/>
			小提醒: <br/> 
			提交信息后，您将接收到银联02096585的来电，请按照语音提示操作；	<br/>	
			中国银联采用卡密分离核心专利技术，彻底保障用户交易安全！<br/>
			银联客服热线：<a href="wtai://wp/mc;4006577577">400-657-7577</a><br/>
			1.目前支持招行/农行/浦发/深发/华夏/兴业银行的借记卡;<br/>
			      农业银行需要短信认证，请在接到短信检验码后及时回复<br/>
			2.中国银联于每日23时做结算,本充值方式每日23时至次日0时暂停使用!<br/>
			3.单笔支付限额2万，当日限额2万；<br/>
			4.首次使用该支付方式后，系统将默认绑定该银行卡作为您再次电话支付的唯一专用卡；如要修改，请拨打如意彩客服电话400-665-1000<br/>
			
			<br/>
			<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">>>更多充值方式选择</a>
			<br/><a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">充值首页</a>
		</body>