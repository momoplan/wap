<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
	<title>账户充值--神州行充值</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">账户充值</a><%
			out.print(CommonUtil.printChar());
		%>移动充值卡<br/><img src="<%=path%>/wap/images/YD.png" alt="" ></img><br/>
		    请选择充值卡的面额<br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByYD.jspx"+"?facevalue=30")%>">移动30元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByYD.jspx"+"?facevalue=50")%>">移动50元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByYD.jspx"+"?facevalue=100")%>">移动100元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByYD.jspx"+"?facevalue=300")%>">移动300元充值卡</a><br/>
		 <a href="<%=response.encodeURL(path+"/wap/charge/chargeIndexByFaceValueByYD.jspx"+"?facevalue=500")%>">移动500元充值卡</a><br/>
 提醒:请用户充值时,认真查看.<br/>		
<br/>1.移动充值卡支付目前支持:移动全国通用神州行充值卡，江苏、辽宁、浙江、福建移动本地充值卡.
<br/>2.江苏移动充值卡:序列号为16位，密码为17位；面值是50或100元.
<br/>3.辽宁移动充值卡:序列号为16位，密码为21位；面值是50或100元.
<br/>4.浙江移动缴费券:序列号为10位，密码为8位；面值是50或100元.
<br/>5.福建移动呱呱通充值卡:序列号为16位，密码为17位；面值是50或100元.
<br/>		<a href="<%=response.encodeURL(path+"/wap/charge/chargeIndex.jspx")%>">充值首页</a>
</body>