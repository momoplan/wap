<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@page import="com.ruyicai.wap.util.WapUtil"%>
<%@page import="com.ruyicai.wap.bean.Channel"%>
<%@page import="com.ruyicai.wap.Global"%>
<%@page import="com.ruyicai.wap.bean.ChargeMode"%>
<%@ page import="com.ruyicai.wap.util.*" %>
<%@ page import="java.util.*" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
    ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
    Channel channel=WapUtil.getChannel(request);
    String CHANNEL=com.ruyicai.wap.util.WapUtil.getChannelId(request);
%>
<title>账户充值</title>
	<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-账户充值<br/>
	<c:if test="${not empty message }">
		<a style="color: red">${message }</a><br/>
	</c:if>
			完成充值,即可感受彩票激情魅力.百万大奖,正在降临!<br/>
			<%
			if("521".equals(CHANNEL)){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String beginTime = "2011-12-01";
				String endTime = dateFormat.format(new Date());
				String total = SelectAllUtil.getStatChargeUsersTotal(beginTime,endTime,CHANNEL);
				if(!"".equals(total)){
					if(10000<Integer.parseInt(total)){
						total = "10000";
					}
				%>
				目前已经有<a style = "color:#DE0201"><%=total %></a>名用户参与了充值送彩金活动，名额有限，赶快参加吧！<br/>
				<%
				}
			}
			%>
			
			<%
			
			List<ChargeMode> list=Global.newsDAO.getChargeMode( channel.getChannelId());
		    if(list!=null)	for(ChargeMode cm:list){
				String chargeName = cm.getName();
		    	if("支付宝充值".equals(chargeName)){%>
		    		<div class="charge">
						<%=chargeName %><br/>
					</div>
					<a href="<%=response.encodeURL(cm.getVal() ) %>">支付宝wap充值</a> | <a href="/w/wap/charge/chargeByZFBKJ.jspx">支付宝快捷充值</a>(无支付宝账户也可以充值)<br/>
		    	<%}else if("银联语音充值".equals(chargeName)){%>
		    		<div class="charge">
					<%=chargeName %><br/>
					</div>
					<a href="<%=response.encodeURL(cm.getVal() ) %>">支持工、农、建、招、邮政、华夏、兴业、中信、深发银行</a><br/>
		    	<%}else if("充值卡充值".equals(chargeName)){%>
		    		<div class="charge">
					<%=chargeName %><br/>
					</div>
					<a href="/w/wap/charge/chargeByPointMoveEpayTofaceValue.jspx">移动充值卡</a> |
					<a href="/w/wap/charge/chargeByPointMoveLTEpayTofaceValue.jspx">联通充值卡</a> |
					<a href="/w/wap/charge/chargeByPointMoveDXEpayTofaceValue.jspx">电信充值卡</a><br/>
		    	<%}else if("银行转账".equals(chargeName)){%>
					<div class="charge">
					<%=chargeName %><br/>
					</div>
					<a href="<%=response.encodeURL(cm.getVal() ) %>">通过银行柜台、ATM或者网上银行转帐</a><br/><br/>
					
		    	<%}
			}
		
			if( channel.getChargeDescribe()!=null) out.println( channel.getChargeDescribe() );%> 
			
			  
<br/><a href="/wap/wapindex.jspx">返回首页</a>
			 <br/>
	<a href="/wap/userinfo/userCenter.jspx">返回用户中心</a>
	</body>		
