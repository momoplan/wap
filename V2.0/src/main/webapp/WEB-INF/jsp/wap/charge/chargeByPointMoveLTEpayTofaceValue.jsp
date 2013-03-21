<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
	<title>账户充值--联通卡充值</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
				out.print(CommonUtil.printChar());
			%>联通卡充值<br/><img src="<%=path%>/wap/images/LT.png" alt="" ></img><br/>
		    请选择充值卡的面额<br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByLT.jspx"+"?facevalue=20")%>">联通20元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByLT.jspx"+"?facevalue=30")%>">联通30元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByLT.jspx"+"?facevalue=50")%>">联通50元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByLT.jspx"+"?facevalue=100")%>">联通100元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByLT.jspx"+"?facevalue=300")%>">联通300元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByLT.jspx"+"?facevalue=500")%>">联通500元充值卡</a><br/>
 提醒:请用户充值时,认真查看.<br/>
联通充值卡支付目前支持：联通一卡充,卡号15位，密码19位；面值：20、30、50、100、300、500元<br/>
<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">充值首页</a>
</body>