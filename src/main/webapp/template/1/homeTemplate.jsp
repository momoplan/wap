<%@ page pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%><%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%><?xml version="1.0" encoding="utf-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ruyicai.wap.bean.*"%>
<%@page import="com.ruyicai.wap.util.*"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta name="Keywords"
	content="如意彩，双色球,彩票,福彩中心,体彩中心,中奖号码,开奖公告,手机彩票，手机购彩，手机投注，3d，福彩3d，彩票开奖，彩票合买,福利彩票" />
<meta name="Description"
	content="如意彩-让手机彩票更精彩。拥有专业手机投注服务平台，全手机彩票牌照运营，手机客户端购彩，彩票送礼服务，中奖通知等多种彩票特色产品服务。如意彩还提供，彩票合买,手机彩票资讯，号码走势，开奖查询等增值服务，充分满足广大彩民的需求。" />
<link href="<%=request.getContextPath() %>/wap/images/index.css" rel="stylesheet" type="text/css" />

<title><decorator:title /></title>

</head>
<%
TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute(
				"user");
		String user_name ="";
		String nick_Name ="";
		if(tuserInfoBean!=null){
			user_name = tuserInfoBean.getUserName();
			nick_Name = tuserInfoBean.getNickname();
		}
		 
		String user_session = (String) request.getSession().getAttribute(
				"sessionid");

		if (user_name != null && !"".equals(user_name) && user_session != null
				&& !"".equals(user_session)) {
			if (nick_Name != null && !"".equals(nick_Name)
					&& !"null".equals(nick_Name)) {
				user_name = nick_Name;
			} else {
				boolean verifyMobileId = VerificationAlgorithmUtil
						.verifyMobileId(user_name.trim());
				if (verifyMobileId && user_name.length() > 10) {
					user_name = user_name.substring(0, 3) + "****"
							+ user_name.substring(7, 11);
				}
			}
		}


%>
<body>
		<div class="top">
			<img src="<%=request.getContextPath() %>/wap/images/LOGOEN.png" />
		</div>

<div class="logo_box"> 
	<img src="<%=request.getContextPath() %>/wap/images/ruyicai.png" alt="" class="logo"></img> </div>
<%
if(user_name!=null&&!"".equals(user_name)){
%>

		<div class="top_mb2">
			<font style="color:#ff9900"><%=user_name%></font>，您好！<a
				href="<%=request.getContextPath() %>/userWap/loginOut.jspx"><font style="color:#0000FF">[退出]</font></a>
		</div>
	<%}%>
	<decorator:body />
	 <div class="contant">
<a href="<%=request.getContextPath() %>/wap/help/aboutUs.jspx">关于</a>
<br />

客服: <a href="wtai://wp/mc;4006651000">4006651000</a> QQ：1427872305<br />
中国福彩体彩中心指定购彩平台<br />
京ICP备10007936号<br />
	</div>
<div id="div" style="display: none">
<script language="javascript" type="text/javascript" src="http://js.users.51.la/14410524.js"></script>
<noscript><a href="http://www.51.la/?14410524" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/14410524.asp" style="border:none" /></a></noscript></div>
</body>
</html>