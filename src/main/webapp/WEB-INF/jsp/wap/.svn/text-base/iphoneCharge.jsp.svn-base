<%@page import="java.text.SimpleDateFormat"%>
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
<a href="/w/wap/wapindex.jspx">首页</a>-账户充值<br/>
			完成充值,即可感受彩票激情魅力.百万大奖,正在降临!<br/>
			<%
			List<ChargeMode> list=Global.newsDAO.getChargeMode("2");
		    if(list!=null)	for(ChargeMode cm:list){
				out.println("★ <a href=\"" +response.encodeURL(cm.getVal() )  +"\">"+cm.getName()+"</a><br/>");	
			}
	%>  <br>
	小提示:目前支付宝和招行手机银行暂不支持部分手机,如无法使用请改用其它充值方式或登录如意彩web网站www.ruyicai.com进行充值<br/>
	1.如意彩是中国领先的电子彩票投注平台,拥有雄厚的实力和先进的彩票投注技术,并为用户提供一贯的诚信服务.<br/>
	2.您的账户资金存放于国家福彩中心指定的银行账户,由福彩中心资金帐户管理系统进行管理,切实保障您的资金安全.<br/> 
	3.如意彩为支付宝信任商家和签约合作伙伴,请放心充值. <br/>
	 4.如意彩为中国银联手机支付合作伙伴,请放心充值. <br/>
	 5.银行卡电话充值,支付宝充值,手机银行充值免手续费. 
<br/><a href="/wap/wapindex.jspx">返回首页</a>
			 <br/>
<a href="/wap/userinfo/userCenter.jspx">返回用户中心</a>
	</body>		
