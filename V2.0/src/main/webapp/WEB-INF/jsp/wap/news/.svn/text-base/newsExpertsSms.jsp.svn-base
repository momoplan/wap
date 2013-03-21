<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
<%@ page import="com.ruyicai.wap.util.*,com.ruyicai.wap.controller.*" %>
<title>短信服务</title>
<%
	String CHANNEL=com.ruyicai.wap.util.WapUtil.getChannelId(request);
    String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	News nb;
	//取得新闻内容
	Map map = new NewsActionWap().getNewsTypeAndContentListToIndexInMap(request);
	List ZhuanJiaZhanJi =(List) map.get("ZhuanJiaZhanJi");
%>
<body>
<a href="/wap/wapindex.jspx">首页</a>-专家点播<br/>
	<br/>
	如意彩彩研组专家团队，汇聚集体智慧，为您精挑细选！<br/>
	【专家短信点播】资费1元/条 不含通讯费<br/>
	点播成功后,系统即以短信的形式将最新一期的专家推荐发送到您的手机。<br/>
	3D点播：<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47103_00")%>">3D定位摘星</a>：发送696201到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47103_01")%>">3D爱心套餐</a>：发送696202到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47103_02")%>">3D金银双胆</a>：发送696203到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47103_03")%>">3D精选10注</a>：发送696204到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47103_04")%>">3D王牌直选</a>：发送696261到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47103_05")%>">3D变化守号</a>：发送696262到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47103_06")%>">3D和跨夺金</a>：发送696263到106695888<br/>
	排三点播：<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=T01002_00")%>">排列三精选10注</a>：发送696209到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=T01002_01")%>">排列三黄金套餐</a>：发送696210到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=T01002_02")%>">排列三金银双胆</a>：发送696264到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=T01002_03")%>">排列三王牌直选</a>：发送696265到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=T01002_04")%>">排列三变化守号</a>：发送696266到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=T01002_05")%>">排列三和跨夺金</a>：发送696267到106695888<br/>
	双色球点播：<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47104_00")%>">双色球非常6+1</a> ：发送696205到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47104_01")%>">双色球四码蓝球</a>：发送696206到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47104_02")%>">双色球绝杀十红</a>：发送696207到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47104_03")%>">双色球复式推荐</a>：发送696208到106695888<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47104_04")%>">双色球三区定胆</a>：发送696268到106695888<br/>
	【专家短信包月】资费5元/月<br/>
	订购成功后，双色球每期开奖后次日上午10点，将最新一期专家推荐以短信形式发送至您的手机。<br/>
	<a href="<%=response.encodeURL(path+"/wap/news/newSmsDetail.jspx"+"?type=F47104_05")%>">双色球·荐号王：发送108153到106695888</a><br/>
	<%if (ZhuanJiaZhanJi!=null && ZhuanJiaZhanJi.size()>0) { %>
	【往期成绩回顾】<br/>
		<%for(int i = 0 ; i < ZhuanJiaZhanJi.size() ; i ++){ 
		nb = (News)ZhuanJiaZhanJi.get(i);
		%>
		<%=nb.getTitle() %><br/>
		<%} %>
	<%} %>
	温馨提示:<br/>
	(1)以上业务全面支持移动、联通、电信C网用户。<br/>
	(2)3D、排列三推荐每日上午10时更新，双色球推荐每周一、三、五上午10时更新。请在每期内容更新后点播，以免收到重复内容，给您造成损失。<br/>
	(3)如10分钟内没有收到下行的专家推荐信息，请拨打客服电话咨询。<br/>
	客服电话：010-82335566
	<img src="http://113.31.29.205/docs/O10l/rc.jsp?sessionid=<%=request.getSession().getId()%>&amp;cn=<%=CHANNEL%>" /><br/>
	<a href="<%=response.encodeURL(path + "/wap/wapindex.jspx")%>">返回首页</a>
	</body>
