<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,java.text.*"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%><title>余额查询</title>
<body>


<a href="/w/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/userinfo/userCenter.jspx"
							)%>">用户中心</a>
<%
	out.print(CommonUtil.printChar());
%>余额查询<br />
<%
	String balance = (String)request.getAttribute("balance");//总金额
	String FreezeBalanceFloat = (String)request.getAttribute("FreezeBalanceFloat");//冻结金额
	String AbleToBet = (String)request.getAttribute("AbleToBet");//可投注金额
	String ableToTcash = (String)request.getAttribute("ableToTcash");//可提现金额
%>
<%
	if (balance != null) {
%> 总余额:<%
	out.println(balance);
%>元。 <br />
冻结金额:<%
	out.println(FreezeBalanceFloat);
%>元。 <br />
可投注金额:<%
	out.println(AbleToBet);
%>元。 <br />
可提现金额:<%
	out.println(ableToTcash);
%>元。 <%
	} else {
%> 请登录! <%
	}
%> <br />
<a href="<%=response.encodeURL(path + "/wap/userinfo/userCenter.jspx"
								)%>">返回上一页</a>
</body>
