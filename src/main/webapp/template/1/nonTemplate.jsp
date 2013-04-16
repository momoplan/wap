<%@ page pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%><%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%><?xml version="1.0" encoding="utf-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="Keywords"
	content="如意彩，双色球,彩票,福彩中心,体彩中心,中奖号码,开奖公告,手机彩票，手机购彩，手机投注，3d，福彩3d，彩票开奖，彩票合买,福利彩票" />
<meta name="Description"
	content="如意彩-让手机彩票更精彩。拥有专业手机投注服务平台，全手机彩票牌照运营，手机客户端购彩，彩票送礼服务，中奖通知等多种彩票特色产品服务。如意彩还提供，彩票合买,手机彩票资讯，号码走势，开奖查询等增值服务，充分满足广大彩民的需求。" />
	
<title><decorator:title /></title> 
<link href="/w/wap/images/index.css" rel="stylesheet" type="text/css" />
</head>
<body><a name="top" id="top"></a>
		<div class="top">
			<img src="<%=request.getContextPath() %>/wap/images/LOGOEN.png" />
		</div>

<div class="logo_box"> 
	<img src="<%=request.getContextPath() %>/wap/images/ruyicai.png" alt="" class="logo"></img> </div> <c:if test="${not empty user_name }">
		<div class="top_mb2">
			<font style="color:#ff9900">${user_name}</font>，您好！<a
				href="/w/userWap/loginOut.jspx"><font style="color:#0000FF">[退出]</font></a>
		</div>
	</c:if><div class="contant">
	<decorator:body /></div>
	<div class="jc_15"></div>
<div style="color: blue;">
<a name="top"></a>
  <a href="<%=request.getContextPath() %>/wap/wapindex.jspx">首页</a>-<a href="<%=request.getContextPath() %>/wap/buyLottery.jspx">购彩大厅</a>-
   <a href="<%=request.getContextPath() %>/wap/userinfo/userCenter.jspx">用户中心</a>
</div>
	<div class="contant"><a
		href="/w/wap/buyLottery.jspx">购彩大厅</a>·<a
		href="/w/wap/charge/chargeIndex.jspx">账户充值</a>·<a
		href="/w/wap/userinfo/userCenter.jspx">用户中心</a>·<a href="/w/wap/help/help.jspx">帮助中心</a>·<a href="#top" onclick="">回到顶部</a>·
<c:if test="${not empty user_name }">
<a href="/w/userWap/loginOut.jspx">退出</a>
	</c:if>
	<c:if test="${  empty user_name }">
<a href="/w/wap/userinfo/login.jspx">登录</a>
	</c:if>

<a href="http://WAPWAT.com/in.asp?id=682019"><img alt="WAPWAT.com"
		width="0" height="0"
		src="http://s12.WAPWAT.com/wapc?style=0&amp;id=ruyicai&amp;p=http://wap.ruyicai.com/w/wap/daletou/DaletouIndex.jsp?null&amp;r=http://wap.ruyicai.com/w/" />
	</a>
</div>
</body>
</html>