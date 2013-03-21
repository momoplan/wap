<%@ page pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%><%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%><?xml version="1.0" encoding="utf-8"?>
<%@ page import="com.ruyicai.wap.util.*"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=0" /> 
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width;initial-scale=1.25;minimum-scale=1.0; maximum-scale=2.0" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="kiben" content="no-cache" />
<meta http-equiv='Cache-Control' content='max-age=0' />
<meta http-equiv='Cache-Control' content='no-cache' forua='true' />
<meta http-equiv='Cache-Control' content='must-revalidate' forua='true' />
<META NAME="Keywords"
	CONTENT="如意彩，双色球,彩票,福彩中心,体彩中心,中奖号码,开奖公告,手机彩票，手机购彩，手机投注，3d，福彩3d，彩票开奖，彩票合买,福利彩票" />
<META NAME="Description"
	CONTENT="如意彩-让手机彩票更精彩。拥有专业手机投注服务平台，全手机彩票牌照运营，手机客户端购彩，彩票送礼服务，中奖通知等多种彩票特色产品服务。如意彩还提供，彩票合买,手机彩票资讯，号码走势，开奖查询等增值服务，充分满足广大彩民的需求。" />
	
<title><decorator:title /></title> 
<link href="/w/wap/images/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
<% String user_name = (String)request.getSession().getAttribute(
		"userName");
String nick_Name= (String)request.getSession().getAttribute(
		"nickName");
String user_session= (String)request.getSession().getAttribute(
		"sessionid");
if(user_name!=null&&!"".equals(user_name)&&user_session!=null&&!"".equals(user_session)){
if(nick_Name!=null&&!"".equals(nick_Name)&&!"null".equals(nick_Name)){
user_name = nick_Name;
}else{
boolean verifyMobileId = VerificationAlgorithmUtil
								.verifyUserName(user_name.trim());
								if(verifyMobileId&&user_name.length()>10){
									user_name= user_name.substring(0, 3)+"****"+user_name.substring(7, 11);
}}
%><div class="top_mb2"><font style="color:#ff9900"><%=user_name%></font>，您好！<a	href="/w/userWap.do?method=loginOut"><font style="color:#0000FF">[退出]</font></a></div>
<%}else{%>
<div class="top"><img src="http://wap.ruyicai.com/w/wap/images/LOGOEN.png" /></div>	
<%}%>


<div class="logo_box"> 
	<img src="http://192.168.0.118/w/wap/images/ruyicai.png" alt="" class="logo"></img> </div> 
<div class="contant">
	<decorator:body />
</div>
	<br />
	<div class="contant"><a
		href="/wap/11select5/11select5Index.jsp">江西11选5</a>·<a
		href="/wap/ElevenDuoJin/index.jsp">十一运夺金</a>·<a
		href="/wap/DoubleBall/DoubleBallIndex.jsp">双色球</a>·<a
		href="/wap/3D/3Dindex.jsp">3D</a>·<a
		href="/wap/daletou/DaletouIndex.jsp">大乐透</a>·<a
		href="/wap/qilecai/QilecaiIndex.jsp">七乐彩</a>·<a
		href="/wap/array3/ArrayIndex.jsp">排列三</a>·<a
		href="/wap/array5/array5Index.jsp">排列五</a>·<a
		href="/wap/cqshishicai/sscIndex.jsp">重庆时时彩</a>·<a
		href="/wap/sevenStar/7StarIndex.jsp">七星彩</a>·<a
		href="/wap/zucai/ZucaiIndex.jsp">足彩</a>·<a
		href="/wap/buyLottery.jsp">购彩大厅</a>·<a
		href="/wap/charge/chargeIndex.jsp">账户充值</a>·<a
		href="/wap/userinfo/userCenter.jsp">用户中心</a>·<a href="/wap/help.jsp">帮助中心</a>·<a href="http://WAPWAT.com/in.asp?id=682019"><img alt="WAPWAT.com"
		width="0" height="0"
		src="http://s12.WAPWAT.com/wapc?style=0&amp;id=ruyicai&amp;p=http://wap.ruyicai.com/w/wap/daletou/DaletouIndex.jsp?null&amp;r=http://wap.ruyicai.com/w/" />
	</a>
</div>
</body>
</html>