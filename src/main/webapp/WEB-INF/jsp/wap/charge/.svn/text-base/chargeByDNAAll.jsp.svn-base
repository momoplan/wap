<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*,java.util.*,org.apache.log4j.Logger,net.sf.json.JSONObject"%>
<%
	Random rdm = new Random();
	int transctionId = rdm.nextInt();
	request.getSession().setAttribute(transctionId+"", "false");

	String path = request.getContextPath();
	String mobile="",userName="",certId="";
	//手机号码	
	mobile = (String)request.getAttribute("mobile");
	//用户姓名
	userName = (String)request.getAttribute("userName");
	//身份证号
	certId = (String)request.getAttribute("certId");
	//银行卡号
	String card_no = request.getAttribute("card_no")==null?"":(String)request.getAttribute("card_no");
	//银行卡开户省份
	String accountAddress =request.getAttribute("accountAddress")==null?"":(String)request.getAttribute("accountAddress");
	//银行卡开户城市
	String accountAddress1 =request.getAttribute("accountAddress1")==null?"":(String)request.getAttribute("accountAddress1");
	//身份证户籍所在地
	String documentAddress = request.getAttribute("documentAddress")==null?"":(String)request.getAttribute("documentAddress");
	//充值金额
	String transaction_money = request.getAttribute("transaction_money")==null?"100":(String)request.getAttribute("transaction_money");
	JSONObject userinfo =(JSONObject)request.getAttribute("userinfo")==null?null:(JSONObject)request.getAttribute("userinfo");
	if ((mobile==null||mobile.trim().equals(""))&&userinfo!=null) {
		mobile=userinfo.getString("mobileid");
	}
	if ((userName==null||userName.trim().equals(""))&&userinfo!=null) {
		userName=userinfo.getString("name");
	}
	if ((certId==null||certId.trim().equals(""))&&userinfo!=null) {
		certId=userinfo.getString("certid");
	}
	if (certId==null||certId.trim().equals("111111111111111111")||certId.trim().equals("$"+"(userID)")) certId="";
	if (userName==null||"null".equals(userName)) userName="";
	if (certId==null||"null".equals(certId)) certId="";
	if (mobile==null||"null".equals(mobile)) mobile="";
%>
	<title>账户充值--银联语音充值</title>
	<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
			out.print(CommonUtil.printChar());
		%>银联语音充值<br/>
		<%
			String message = (String)request.getAttribute("message");
			if(message!=null && message.trim().length()>0)
			{%>
				<a style="color: red"><% out.print(CommonUtil.printWarningMessage(message)); %></a><br/>
			<%}
		%>
		小提示：您第一次使用银联语音充值，为确保安全，请完善以下信息：<br/>
		<br/>充值金额:
				<form action="<%=response.encodeURL(path+"/chargeWap/DNAChargeWAPGreylisting.jspa")%>" method="post">
<input name="transaction_money" type="text" maxlength="10" size="8" format="*N" emptyok="true" value="<%=transaction_money %>"/>元<br/>
		选择银行：<br/><select name="bankType" value="" >
				<option id="00" value="00">请选择：
				</option>
				<option id="ddd" value="gongshang">中国工商银行
				</option>
				<option id="ddd" value="nongye">中国农业银行
				</option>
				<option id="ddd" value="jianshe">中国建设银行
				</option>
				<option id="dd" value="zhaoshang">招商银行
				</option>
				<option id="ddd" value="youzheng">中国邮政储蓄银行
				</option>
				<option id="d" value="huaxia">华夏银行
				</option>
				<option id="d" value="xingye">兴业银行
				</option>
				<option id="d" value="zhongxin">中信银行
 			    </option>
				<%--<option id="ddd" value="pufa">上海浦东发展银行 
				</option>--%>	
				<option id="dd" value="shenfa">深圳发展银行
				</option>
			</select><br/>
		(仅支持以上银行的借记卡)  <br/>  
		银行卡号:
		<input name="card_no" type="text" size="20" format="*N" emptyok="true" value="<%=card_no %>"/><br/>
		开卡人姓名:
		<input type="text" size="10" name="userName" value="<%=userName %>" /><br/>
	 	开卡人身份证号:
		<input type="text" name="documentNumber" maxlength="20" size="20" value="<%=certId %>"/><br/>
	 	身份证户籍所在地:
	 	<input type="text" name="documentAddress" size="20" value="<%=documentAddress %>" /><br/>
	 	(山东省青岛市XX街XX路XX号)<br/>
	 	银行卡开户省份:
	 	<input type="text" name="accountAddress" size="10" value="<%=accountAddress %>" />省<br/>
	 	银行卡开户城市:
	 	<input type="text" name="accountAddress1" size="10" value="<%=accountAddress1 %>" />市<br/>
	 	电话号码:
		<input name="phone" type="text" size="20" format="*N" emptyok="true" value="<%=mobile %>"/><br/>
		(为确保接听银联电话,请填写真实号码)<br/>			
			<input type="hidden" name="cardType" value="01"/>
			<input type="hidden" name="accesstype" value="W"/>
			<input type="hidden" name="bankId" value="dna001"/>
			<input type="hidden" name="token" value="<%=transctionId %>"/>
			<input type="submit" value="提交信息"/>
			</form>
		
	 <br/>
		小提醒：<br/>
		1.第一次使用电话银行，账户认证需要一段时间，请耐心等待银联020-96585的来电，并按照语音提示操作<br/>
        2.农业银行需要短信认证，请在接到短信检验码后及时回复<br/>
		3.银联客服热线：<a href="wtai://wp/mc;020-28863558">020-28863558</a> <br/>
		4.首次使用该支付方式后，系统将默认绑定该银行卡作为您再次电话支付的唯一专用卡；如要修改，请拨打如意彩客服电话400-665-1000<br/>
		<br/><a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">>>更多充值方式选择</a>
		<br/><a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">充值首页</a>
		</body>