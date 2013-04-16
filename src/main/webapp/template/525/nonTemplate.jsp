<%@ page pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%><%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%><?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<%String nonPath = com.buybal.lot.util.CommonUtil.removeTrailingSlash(request.getContextPath());%>
<style type="text/css">
body {
	color: #000;
	font-size: 14px;
	line-height: 20px;
}

form {
	margin: 0px;
	padding: 0px;
	display:inline;
}

.crossfirst {
	width: 280px;
	border-top: none;
	border: solid 1px #E98900;
}
</style>
</head>
<body><div class="crossfirst"> 
	<img src="http://www.ruyicai.com/s/wap/Y00003/logo.png" alt=""></img> 
	<br />
	<decorator:body />
	<br />
	<a
		href="<%=nonPath%>/wap/DoubleBall/DoubleBallIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">双色球</a>·
	<a
		href="<%=nonPath%>/wap/3D/3Dindex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">3D</a>·
	<a
		href="<%=nonPath%>/wap/daletou/DaletouIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">大乐透</a>·
	<a
		href="<%=nonPath%>/wap/qilecai/QilecaiIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">七乐彩</a>·
	<a
		href="<%=nonPath%>/wap/array3/ArrayIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">排列三</a>·
	<a
		href="<%=nonPath%>/wap/array5/array5Index.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">排列五</a>·
	<a
		href="<%=nonPath%>/wap/cqshishicai/sscIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">时时彩</a>
	<a
		href="<%=nonPath%>/wap/sevenStar/7StarIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">七星彩</a>·
	<a
		href="<%=nonPath%>/wap/zucai/ZucaiIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">足彩</a>·
	<a
		href="<%=nonPath%>/wap/buyLottery.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">购彩大厅</a>·
	<a
		href="<%=nonPath%>/wap/charge/chargeIndex.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">账户充值</a><br/>
	<a
		href="<%=nonPath%>/wap/userCenter.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">用户中心</a>·
	<a href="<%=nonPath%>/wap/help.jsp;jsessionid=DBE5057C10EFFA0E9199501506F79572">帮助中心</a>·
	<a href='<%=nonPath%>/'>返回首页</a>
	<a href="http://www.ruyicai.com/s/wap/Y00003/link/ecaiwap_link.jad">·存书签</a>
	<a href="http://WAPWAT.com/in.asp?id=682019"><img alt="WAPWAT.com"
		width="0" height="0"
		src="http://s12.WAPWAT.com/wapc?style=0&amp;id=ruyicai&amp;p=http://wap.ruyicai.com/w/wap/daletou/DaletouIndex.jsp?null&amp;r=http://wap.ruyicai.com/w/" />
	</a>
</div></body>
</html>