<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,com.ruyicai.wap.util.MessageUtil"%>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%
	String CHANNEL=com.ruyicai.wap.util.WapUtil.getChannelId(request);
	String path = CommonUtil.removeTrailingSlash(request
	.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取七乐彩开关
	String HMSwitch = rbint.getString("HMSwitch"); //取得合买 开关
%>
<title>用户中心</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-用户中心<br />
【账户】<br />
<a
	href="<%=response.encodeURL(path + "/userWap/getLotteryAccount.jspa")%>">账户余额</a><br />
<a
	href="<%=response.encodeURL(path + "/wap/charge/chargeIndex.jspx"
							)%>">账户充值</a><br />

<a
	href="<%=response
							.encodeURL(path
									+ "/wap/accountDetail/accountDetail.jspx"
									)%>">账户明细</a><br />
	<a
	href="<%=response.encodeURL(path + "/drawCash/findDNAtoCash.jspa")%>">账户提现</a><br />
<a href="/userWap/findScoreDetailByUserno.jspa">我的积分</a><br/> 
【查询】<br />
<a
	href="<%=response.encodeURL(path + "/wap/query/betingOrdersSelect.jspx"
							)%>">投注查询</a><br />
<%
	if (HMSwitch.equals("1")) {
%><a
	href="<%=response.encodeURL(path
								+ "/orderhm/caseLotBuys.jspa" )%>">合买查询</a>
<%
	}
%><br />
<a
	href="<%=response.encodeURL(path + "/BetingSelectAction/addNumberSelect.jspa")%>">追号查询</a><br />

<a
	href="<%=response.encodeURL(path + "/BetingSelectAction/getWiningSelectTorders.jspa")%>">中奖查询</a><br />

<a href="/w/wap/query/selectGift.jspx">赠送查询</a><br />

【修改】<br />
<a href="/w/userWap/selectMsgs.jspa">我的留言</a><br />
<a
	href="<%=response.encodeURL(path + "/userWap/getUserinfo.jspa")%>">完善信息</a><br />
<%
if(!"521".equals(CHANNEL)){ %>
<a
	href= "/wap/userinfo/updateUserPassword.jspx">密码修改</a><br />
  <a
	href="<%=response.encodeURL(path + "/userWap/bindingMobileDetail.jspa")%>">手机绑定</a><br />
	 <%} %>
<a href="/w/wap/userinfo/messageSetting.jspx">设置</a><br />
<a href="/wap/wapindex.jspx">返回上一页</a>
</body>
