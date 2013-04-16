<%@ page pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%><%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%><?xml version="1.0" encoding="utf-8"?>

<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.ruyicai.wap.bean.*"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*"%>
<head>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="Keywords"
	content="如意彩，双色球,彩票,福彩中心,体彩中心,中奖号码,开奖公告,手机彩票，手机购彩，手机投注，3d，福彩3d，彩票开奖，彩票合买,福利彩票" />
<meta name="Description"
	content="如意彩-让手机彩票更精彩。拥有专业手机投注服务平台，全手机彩票牌照运营，手机客户端购彩，彩票送礼服务，中奖通知等多种彩票特色产品服务。如意彩还提供，彩票合买,手机彩票资讯，号码走势，开奖查询等增值服务，充分满足广大彩民的需求。" />
<title><decorator:title /></title>
<link href="/w/wap/images/index.css" rel="stylesheet" type="text/css" />
<%
	NewTopForHLXKBean topForHLXKBean =  new NewTopForHLXKBean();
	topForHLXKBean = CommonUtil.getInfoByJDOM();
	List<NewInfoForHLXKBean> infoList = topForHLXKBean.getNewInfoForHLXKBean();
	List<NewChannelForHLXKBean> channelList = topForHLXKBean.getChannelForHLXKBeans();
%>
</head>
<body>
<div class="logo_box"> 
	<img src="http://hlxk.ruyicai.com/w/wap/images/hlxkLOGO.png" alt="" class="logo"></img> </div>
	<%if(topForHLXKBean.getTitle()!=null&&!"".equals(topForHLXKBean.getTitle())){
	 %>
	<div class="hotnews"> <a
	href="<%=topForHLXKBean.getUrl()%>"><%=topForHLXKBean.getTitle()%></a></div>
	  <br/>
	 <%}%>
	<div class="contant"><decorator:body /></div><br/>
	<%if(channelList.get(0).getTitle()!=null&&!"".equals(channelList.get(0).getTitle())){%>
	<h1>星空热点</h1><div class="contant">
<%
		int info = infoList.size();
		  int arr[] = new int[info];
		  for(int i = 0;i < 4;i++) {//外层的for循环 用来控制对数组赋值的索引的变化
		   while(true){
		    int n = (int)(Math.random()*info);
		    boolean flag = true;
		    for(int j = 0;j < i;j++){
		     if(n == arr[j]){
		      flag = false;
		      break;
		     }
		    }
		    if(flag) {
		     arr[i] = n;
		     break;
		    } 
		   }
		  }
		
		  for(int i = 0 ;i < 4;i++) {
			   %>
				 <a
	href="<%=infoList.get(arr[i]).getUrl()%>"><%=infoList.get(arr[i]).getTitle()%></a><br/> 
			   <%
		  }
	 }
	%>
	 </div><%if(channelList.get(0).getTitle()!=null&&!"".equals(channelList.get(0).getTitle())){%>
<h1>热点频道</h1>
<div class="contant">
	<%
	for(int i =0;i< channelList.size(); i++){
	 %>
	<a
	href="<%=channelList.get(i).getUrl()%>"><%=channelList.get(i).getTitle()%></a>	 
		<% if((i+1)%4==0)
		out.print("<br/>");
	 %>
	 
	<%
	}
	}
	%>

	 <a href='/wap/wapindex.jsp'>返回彩票频道</a><br/>
	<a href="http://wap.vnet.mobi">返回互联星空</a><br/>
客服: <a href="wtai://wp/mc;4006651000">4006651000</a> QQ：1427872305<br /></div>
</body>
</html>
