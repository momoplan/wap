<%@ page pageEncoding="UTF-8"%>
<%@page import="com.ruyicai.wap.bean.*"%>
<%@page import="com.ruyicai.wap.util.WapUtil"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.math.BigDecimal"%>
<%@ page import="com.ruyicai.wap.util.*,net.sf.json.JSONObject,net.sf.json.JSONArray"%>
<%@ page import="com.jrt.common.Http"%>
<%@page import="com.ruyicai.wap.bean.WinSelectInfoBean"%>
<%@ page import="com.ruyicai.wap.controller.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>如意彩专业手机购彩平台</title>

<%
	out.print(CommonUtil.printHeaderIndex());
	String CHANNEL = com.ruyicai.wap.util.WapUtil.getChannelId(request);
%>
<%
	String userName = "";
	TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
			.getAttribute("user");
	if (tuserInfoBean != null) {
		userName = tuserInfoBean.getUserName();

	}
	String cn = (String)request.getParameter("cn");
	if("809".equals(cn)){
		request.getSession().setAttribute("cn", cn);
	}
	String sessionid = (String) request.getSession().getAttribute(
			"sessionid");
	String nomobile = (String) request.getSession().getAttribute(
			"nomobile");//互联星空用来判断加密信息是否带有电信手机号。没有nomobile不为空

	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite"); //读取七乐彩开关
	String qilecaiDefine = rbint.getString("qilecaiDefine"); //取得七乐彩开关
	String TCDefine = rbint.getString("TCDefine"); //取得体彩开关
	String clientSwitch = rbint.getString("clientSwitch"); //取得客户端开关
	String shishicaiSwitch = rbint.getString("shishicaiSwitch"); //取得时时彩开关
	String qixingcaiSwitch = rbint.getString("qixingcaiSwitch"); //取得七星彩开关
	String HMSwitch = rbint.getString("HMSwitch"); //取得合买 开关
	String TCDefineDLT = rbint.getString("TCDefineDLT");//获取体彩大乐透的开关
	String TCDefineArray3 = rbint.getString("TCDefineArray3");//获取体彩排列三的开关
	String TCDefineArray5 = rbint.getString("TCDefineArray5");//获取体彩排列五的开关
	String duolecaiSwitch = rbint.getString("duolecaiSwitch");//获取体彩11选5的开关
	String elevenDuoJinSwitch = rbint.getString("elevenDuoJinSwitch");//获取山东十一运夺金开关
	String touTiaoSize = rbint.getString("touTiaoSize"); //取得条头新闻个数
	String TulingSwitch = rbint.getString("TULINGINDEX");//获取图铃开关
	String openJieRi = rbint.getString("openJieRi"); //获取节日增彩开关
	String JieRipresend = rbint.getString("JieRi"); // 增彩节日
	String jieString = new String(JieRipresend.getBytes("ISO8859-1"),
			"UTF-8");
	int TTsize = Integer.parseInt(touTiaoSize);
	News nb;
	News nb1;
	//取得新闻内容
	Map map = new NewsActionWap()
			.getNewsTypeAndContentListToIndexInMap(request);
	List ZiXunXinWen = (List) map.get("ZiXunXinWen");
	List TCXinWen = (List) map.get("TCXinWen");
	List FuCaiXinWen = (List) map.get("FuCaiXinWen");
	List TouTiaoXinWen = (List) map.get("TouTiaoXinWen");
	List ZhongJiang = (List) map.get("ZhongJiang");
	List ZuCaiZiXun = (List) map.get("ZuCaiZiXun");
	List ZhuanJiaDuanxin = (List) map.get("ZhuanJiaDuanxin");
	List ZhuanJiaHeMai = (List) map.get("ZhuanJiaHeMai");
	List JieRi = (List) map.get("JieRi");
	List ZuCaiFenXi = (List) map.get("ZuCaiFenXi");
	List ZuCaiPeiLv = (List) map.get("ZuCaiPeiLv");
	List TianYaTouTiao = (List) map.get("TianYaTouTiao");
	List HLXKTouTiao = (List) map.get("HLXKTouTiao");

	//新闻头条
		List<WapNews> wapHotNews = new ArrayList<WapNews>();
		wapHotNews = new NewsController().selectWapNewsList("4",0,2);
	//专家分析
	List<WapNews> wapNews = new ArrayList<WapNews>();
	wapNews = new NewsController().selectWapNewsList("2",0,5);
	//彩民故事
		List<WapNews> wapnews = new ArrayList<WapNews>();
		wapnews = new NewsController().selectWapNewsList("1",0,5);
	//获取开奖公告
	List<WinSelectInfoBean> listSSQ =new ArrayList<WinSelectInfoBean>();
	listSSQ = WinSelectAction.getWinfo("F47104", "1");
	String batchcodeSSQ = "";
	String betcodeSSQ = "";
	if(listSSQ!=null){
		batchcodeSSQ = listSSQ.get(0).getBatchcode();
		betcodeSSQ = listSSQ.get(0).getBetCode();
		betcodeSSQ = "<a style='color: red'>"+betcodeSSQ.substring(0,betcodeSSQ.indexOf('|'))+"</a>|<a style='color: blue'>"
					+betcodeSSQ.substring(betcodeSSQ.indexOf('|')+1)+"</a>";
	}
	List<WinSelectInfoBean> listDLT =new ArrayList<WinSelectInfoBean>();
	listDLT = WinSelectAction.getWinfo("T01001", "1");
	String batchcodeDLT = "";
	String betcodeDLT = "";
	if(listDLT!=null){
		batchcodeDLT = listDLT.get(0).getBatchcode();
		betcodeDLT = listDLT.get(0).getBetCode();
	}
	List<WinSelectInfoBean> listSSC =new ArrayList<WinSelectInfoBean>();
	listSSC = WinSelectAction.getWinfo("T01007", "1");
	String batchcodeSSC = "";
	String betcodeSSC = "";
	if(listSSC!=null){
		batchcodeSSC = listSSC.get(0).getBatchcode();
		betcodeSSC = listSSC.get(0).getBetCode();
	}
	//List<Client> clientList = new ArrayList<Client>();
	//clientList = ClientContrallor.selectClient();
%> 

<body>
<script type="text/javascript">
	function change(type){
		if("h11"==type){
			document.getElementById("h11").style.color="black";
		}else if("h22"==type){
			document.getElementById("h22").style.color="black";
		}else if("h33"==type){
			document.getElementById("h33").style.color="black";
		}else if("h44"==type){
			document.getElementById("h44").style.color="black";
		}else if("h55"==type){
			document.getElementById("h55").style.color="black";
		}else if("h66"==type){
			document.getElementById("h66").style.color="black";
		}else if("h77"==type){
			document.getElementById("h77").style.color="black";
		}else if("h88"==type){
			document.getElementById("h88").style.color="black";
		}
	}
</script>
<!-- 网站导航栏 --> 
<%
 	if (nomobile != null && "521".equals(CHANNEL)) {

 	} else {
 		if (!"".equals(userName)) {
 %>
	<div class="menu">
		<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>| <a
			href="/w/wap/buyLottery.jspx">购彩</a>| <a 
		href="/w/orderhm/caseLotLottery.jspx">合买</a>|  <a
			href="/w/wap/charge/chargeIndex.jspx">充值</a>| <a
			href="/w/select/getWinningInfoBylottery.jspx">开奖</a>| <a 
			href="/w/winningRankingHistory/userRankingHistory.jspx?type=5">排行</a>|
		<%
			if (!"521".equals(CHANNEL)) {
		%>
		<a href="/w/client/download.jspx">下载 </a>|
		<%
			}
		%>
		<a
			href="/w/wap/userinfo/feedBack.jspx">留言</a>
	</div>
	<%
		} else {
	%> 
<!--menu -->
	<div class="menu">
	
		<a href="/w/wap/userinfo/login.jspx">登录</a>|
		<%
			if (!"521".equals(CHANNEL)) {
		%>
		<a href="/w/userWap/registerIndex.jspx">注册</a>|
		<%
			}
		%>
		<a href="/w/wap/charge/chargeIndex.jspx">充值</a>| <a
			href="/w/wap/buyLottery.jspx">购彩</a>| <a
			href="/w/orderhm/caseLotLottery.jspx">合买</a>| <a
			href="/w/select/getWinningInfoBylottery.jspx">开奖</a>| <a 
			href="/w/winningRankingHistory/userRankingHistory.jspx?type=5">排行</a>|
		<%
			if (!"521".equals(CHANNEL)) {
		%>
		<a href="/w/client/download.jspx">下载 </a> |
		<%
			}
		%><a
			href="/w/wap/userinfo/feedBack.jspx">留言</a>
	</div>
	<%
		}
		}
	%> 
<%
 	if ("158".equals(CHANNEL)) {
 %>
<%
	if (TianYaTouTiao != null && TianYaTouTiao.size() > 0) {

			for (int i = 0; i < TianYaTouTiao.size() && i < TTsize; i++) {
				nb = (News) TianYaTouTiao.get(i);
				if (nb.getId() != "" && nb.getId() != null
						&& !"".equals(nb.getId())) {
%>
 <div class="hotnews">
<a
	href="/newsAction/getNewsContent.jspx?amp;newsId=<%=nb.getId()%>&amp;id=<%=nb.getId()%>"><%=nb.getTitle()%></a><br />
</div>
<%
	}
			}
		}
%>
<%
	} else if ("521".equals(CHANNEL)) {
%>
<!--top news -->
<%
	if (HLXKTouTiao != null && HLXKTouTiao.size() > 0) {

			for (int i = 0; i < HLXKTouTiao.size() && i < TTsize; i++) {
				nb = (News) HLXKTouTiao.get(i);
				if (nb.getId() != "" && nb.getId() != null
						&& !"".equals(nb.getId())) {
%>
<div class="hotnews">
<a
	href="/newsAction/getNewsContent.jspx?amp;newsId=<%=nb.getId()%>&amp;id=<%=nb.getId()%>"><%=nb.getTitle()%></a><br />
</div>
<%
	}
			}
		}
%>

<%
	} else {
%>
<!-- 新闻头条 -->
<div class="hotnews">
<p>当前为<span style="color:#CC0000">旧版如意彩</span>&nbsp;&nbsp;<a href="http://3g.ruyicai.com" style="font-weight:bold; color:#039">【<img src="/w/wap/images/3g.gif" alt="" width="21" height="10" />版如意彩】</a></p>
<%if(wapHotNews!=null){
	for(int i=0;i<wapHotNews.size()&&i<5;i++){
	WapNews news = new WapNews();
	news = wapHotNews.get(i);
	%>
		<a href="<%=path%>/news/wapNews.jspx?id=<%=news.getId()%>&type=<%=news.getVol_typeid_fk()%>"><%=news.getVol_title()%></a><br />
	
	<%} %>
<%}%>
</div> 
<%
	}
%>

<%
 	if (!"521".equals(CHANNEL)) {
 %>
<h1>客户端下载</h1>
<div class="contant">
&nbsp<a href="http://itunes.apple.com/cn/app/ru-yi-cai-nin-shen-bian-cai/id492164095?mt=8"><img
		src="/w/wap/newImages/p.png" alt="立即下载"></img>Iphone客户端下载</a><br/>
	&nbsp<a href="/w/wap/client/down/android/ruyicai_android_3.8.1_533_wapguanwang.apk"><img 
		src="/w/wap/newImages/a.png" alt="立即下载"></img>Android客户端下载</a><br/>
	&nbsp<a href="http://www.windowsphone.com/zh-cn/store/app/%E5%A6%82%E6%84%8F%E5%BD%A9/b0bda9c5-2887-47e2-a487-ac9f481e8632"><img 
		src="/w/wap/images/wp8.png" alt="立即下载"></img>Windows Phone客户端下载</a><br/>
	&nbsp<a href="/w/wap/client/down/symbian/ruyicai_S60_V5_1.2_779_gwwap.sisx"><img
		src="/w/wap/newImages/s.png" alt="立即下载"></img>客户端下载</a><br/>
</div>
	
<%
		}
	%>
<div class="contant">
<%
	if ("521".equals(CHANNEL)) {
%>
<a
href="<%=response.encodeURL(path
						+ "/news/wapNewsList.jspx?type=2&startLine=0&endLine=10")%>">专家分析</a>|<a
href="<%=response.encodeURL(path
						+ "/news/wapNewsList.jspx?type=1&startLine=0&endLine=10")%>">彩民故事</a>|<a
href="<%=response
						.encodeURL(path
								+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=49")%>">公告信息</a><br/>
 <%
 	}
 %></div>
 <h1>彩种推荐</h1>
 <div class="contant">
 [<a href="/w/wap/DoubleBall/SingleSelfSelection.jspx">双色球</a>]
		<a href="/w/wap/DoubleBall/SingleSelfSelection.jspx">自选</a>|<a 
		   href="/w/wap/DoubleBall/SingleAutoSelection.jspx">机选</a> <a href="/w/wap/trendChart/trendChartSSQ.jspx">走势</a><br/>
	[<a href="/w/wap/3D/3DDirectSingle.jspx">福彩3D</a>]
		<a href="/w/wap/3D/3DDirectSingle.jspx">直选</a>|<a 
		   href="/w/wap/3D/3DGroup3Single.jspx">组三</a>|<a 
		   href="/w/wap/3D/3DGroup6Single.jspx">组六</a>|<a 
		   href="/w/wap/3D/3DDantuoMultiple.jspx">胆拖</a> <a href="/w/wap/trendChart/trendChart3D.jspx">走势</a><br/>
  	[<a href="/w/wap/qilecai/SingleSelfSelection.jspx">七乐彩</a>]
		<a href="/w/wap/qilecai/SingleSelfSelection.jspx">自选</a>|<a 
		   href="/w/wap/qilecai/SingleAutoSelection.jspx">机选</a> <a href="/w/wap/trendChart/trendChartQLC.jspx">走势</a><br/>
 	[<a href="/w/wap/daletou/SingleSelfSelection.jspx">大乐透</a>]
		<a href="/w/wap/daletou/SingleSelfSelection.jspx">自选</a>|<a 
		   href="/w/wap/daletou/SingleAutoSelection.jspx">机选</a> <a href="/w/wap/trendChart/trendChartDLT.jspx">走势</a><br/>
 	[<a href="/w/wap/array3/Array3DirectSingle.jspx">排列三</a>]
		<a href="/w/wap/array3/Array3DirectSingle.jspx">直选</a>|<a 
		   href="/w/wap/array3/Array3GroupHeZhi.jspx">组选</a>|<a 
		   href="/w/wap/array3/Array3Group3BH.jspx">组三</a>|<a 
		   href="/w/wap/array3/Array3Group6BH.jspx">组六</a> <a href="/w/wap/trendChart/trendChartPLS.jspx">走势</a><br/>
	[<a href="/w/wap/array5/array5ByHand.jspx">排列五</a>]
		<a href="/w/wap/array5/array5ByHand.jspx">自选</a>|<a 
		   href="/w/wap/array5/singleAutoSelection.jspx">机选</a> <a href="/w/wap/trendChart/trendChartPLW.jspx">走势</a><br/>
	[<a href="/w/wap/sevenStar/sevenStarByHand.jspx">七星彩</a>]
		<a href="/w/wap/sevenStar/sevenStarByHand.jspx">自选</a>|<a 
		   href="/w/wap/sevenStar/SingleAutoSelection.jspx">机选</a>|<a 
		   href="/w/wap/sevenStar/ddSevenStar.jspx">定胆</a> <a href="/w/wap/trendChart/trendChartQXC.jspx">走势</a><br/>
	[<a href="/w/select5from22/selfSelect.jspx">22选5</a>]
		<a href="/w/select5from22/selfSelect.jspx">自选</a>|<a 
		   href="/w/select5from22/autoSelect.jspx">机选</a>|<a 
		   href="/w/select5from22/dantuoSelect.jspx">胆拖</a> <a href="/w/wap/trendChart/trendChart22X5.jspx">走势</a><br/>
	[<a href="/w/wap/cqshishicai/sscOneStar.jspx">重庆时时彩</a>]
		<a href="/w/wap/cqshishicai/sscOneStar.jspx">一星</a>|<a 
		   href="/w/wap/cqshishicai/sscTowStar.jspx">二星</a>|<a 
		   href="/w/wap/cqshishicai/sscThreeStar.jspx">三星</a>|<a 
		   href="/w/wap/cqshishicai/sscFiveStar.jspx">五星</a>|<a 
		   href="/w/wap/cqshishicai/xddsFiveStar.jspx">大小单双</a><br/>
	[<a href="/w/wap/11select5/optionOne.jspx">江西11选5</a>]
		<a href="/w/wap/11select5/optionOne.jspx">任选</a>|<a 
		   href="/w/wap/11select5/ForwardTwoZX.jspx">直选</a>|<a 
		   href="/w/wap/11select5/ForwardTwoGroup.jspx">组选</a>|<a 
		   href="/w/wap/11select5/dantuoselection.jspx?type=2">胆拖</a> <a href="/w/trendChart/trendChartDetail.jspx?lotno=T01010&imgName=dlcRed1&type=1">走势</a><br/>
	[<a href="/w/wap/ElevenDuoJin/optionOne.jspx">十一运夺金</a>]
		<a href="/w/wap/ElevenDuoJin/optionOne.jspx">任选</a>|<a 
		   href="/w/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选</a>|<a 
		   href="/w/wap/ElevenDuoJin/ForwardTwoGroup.jspx">组选</a>|<a 
		   href="/w/wap/ElevenDuoJin/dantuoR2.jspx">胆拖</a>|<a 
		   href="/w/add/toAddNumberBet.jspx?flag=rx1">特别追号</a> <a href="/w/trendChart/trendChartDetail.jspx?lotno=T01012&imgName=11ydjRed1&type=1">走势</a><br/>
	[<a href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">广东11选5</a>]
		<a href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">任选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/directSelectForwardTwoSingleSelf.jspx">直选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/groupSelectForwardTwoSelf.jspx">组选</a>|<a 
		   href="/w/guangDongElevenSelectFiveIndex/optionTwoDanTuoSelf.jspx">胆拖</a> <a 
	href="/w/trendChart/trendChartDetail.jspx?lotno=T01014&imgName=gd11x5Red1&type=1">走势</a><br/>
	[<a href="/w//guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">广东快乐十分</a>]
		<a href="/w//guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">任选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkDirectSelf.jspx">直选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/selectTwoLinkGroupSelf.jspx">组选</a>|<a 
		   href="/w/guangDongHappyTenMinutesIndex/optionTwoDanTuoSelf.jspx">胆拖</a> <a 
	href="/w/wap/trendChart/trendChartKL10.jspx">走势</a><br/>	   
	[<a href="/w/zc/zcduizhen.jspx?lotno=T01003">足彩</a>]
		<a href="/w/zc/zcduizhen.jspx?lotno=T01003">胜负彩</a>|<a 
		   href="/w/zc/zcduizhen.jspx?lotno=T01004">任选九场</a>|<a 
		   href="/w/zc/zcduizhen.jspx?lotno=T01006">六场半</a>|<a 
		   href="/w/zc/zcduizhen.jspx?lotno=T01005">四场进球</a><br>
	[<a href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">竞彩足球</a>]
		<a href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT001">胜平负</a>|<a 
		   href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT002">比分</a>|<a 
		   href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT003">总进球</a>|<a 
		   href="/w/jc/jczqAgainst.jspx?type=1&valueType=1&wanfa=FT004">半场胜平负</a><br/>
 	[<a href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">竞彩篮球</a>]
 		<a href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK001">胜负</a>|<a 
 		   href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK002">让分胜负</a>|<a 
 		   href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK003">胜分差</a>|<a 
 		   href="/w/jc/jclqshengfu.jspx?type=0&valueType=1&wanfa=BSK004">大小分</a><br/>
<%
	if (ZhuanJiaHeMai.size() > 0 && ZhuanJiaHeMai != null) {
%> <%
 	for (int i = 0; i < ZhuanJiaHeMai.size() && i < 2; i++) {
 			nb = (News) ZhuanJiaHeMai.get(i);
 %><a
	href="<%=response.encodeURL(path
							+ "/wap/buyHMLottery.jspx")%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> 

 </div> 
 <!-- 充值 --> <h1><a id="h33"
	href="<%=response.encodeURL(path + "/wap/charge/chargeIndex.jspx")%>" onclick="change('h33')">在线充值</a></h1>
<div class="contant"><img src="<%=path%>/wap/images/tuijian.gif" alt=""></img><a
	href="<%=response.encodeURL(path + "/chargeWap/getDNA.jspa")%>">银联语音充值</a> <a
	href="<%=response.encodeURL(path + "/wap/charge/chargeByZFB.jspx")%>">支付宝</a><br />
<%
	if ("521".equals(CHANNEL)) {
%>
<a href="<%=response.encodeURL(path
								+ "/wap/charge/chargeByPointMoveDXEpayTofaceValue.jspx")%>">电信充值卡</a>
<a href="<%=response.encodeURL(path
						+ "/wap/charge/chargeBankTransfer.jspx")%>">银行转账</a>
								
<%
	} else {
%>
<a href="<%=response.encodeURL(path
						+ "/wap/charge/chargeByPointMoveIndex.jspx")%>">充值卡</a>
<a href="<%=response.encodeURL(path
						+ "/wap/charge/chargeBankTransfer.jspx")%>">银行转账</a>
<%
	}
%></div>  
<h1><a id="h44"
href="/w/select/getWinningInfoBylottery.jspx" onclick="change('h44')">开奖公告</a></h1>
<div class="contant">
<%if(listSSQ!=null){%>
	<b>双色球</b><%=batchcodeSSQ%>期开奖>><a 
	href="/w/select/lotterySelect.jspx?lotno=F47104&amp;issue=<%=batchcodeSSQ%>">详细

</a><br/>
<%=betcodeSSQ%>
<br />
<% } %>
<%if(listDLT!=null){%>
	<b>大乐透</b><%=batchcodeDLT%>期开奖>><a
	href="/w/select/lotterySelect.jspx?lotno=T01001&amp;issue=<%=batchcodeDLT%>">详细

</a><br/>
<%=betcodeDLT%>
<br />
<% } %>
<%if(listSSC!=null){%>
	<b>时时彩</b><%=batchcodeSSC%>期开奖>><a
	href="/w/select/lotterySelect.jspx?lotno=T01007&amp;issue=<%=batchcodeSSC%>">详细

</a><br/>
<%=betcodeSSC%>
<br />
<% } %>
</div>
<!-- 专家推荐 -->
<%if(wapNews!=null){%>
	 <h1><a id="h55" href="<%=response.encodeURL(path
						+ "/news/wapNewsList.jspx?type=2&startLine=0&endLine=10")%>" onclick="change('h55')">专家推荐</a></h1>	
		
	<div class="contant">	
<%
	for(int i=0;i<wapNews.size()&&i<5;i++){
	WapNews news = new WapNews();
	news = wapNews.get(i);
	%>
		<a href="<%=path%>/news/wapNews.jspx?id=<%=news.getId()%>&type=<%=news.getVol_typeid_fk()%>"><%=news.getVol_title()%></a><br />
	
	<%} %></div>
<%}%>
<!-- 彩民故事 -->
<%if(wapnews!=null){%>
	 <h1><a id="h66" href="<%=response.encodeURL(path
						+ "/news/wapNewsList.jspx?type=1&startLine=0&endLine=10")%>" onclick="change('h66')">资讯新闻</a></h1>	
		
	<div class="contant">	
<%
	for(int i=0;i<wapnews.size()&&i<5;i++){
	WapNews news = new WapNews();
	news = wapnews.get(i);
	%>
		<a href="<%=path%>/news/wapNews.jspx?id=<%=news.getId()%>&type=<%=news.getVol_typeid_fk()%>"><%=news.getVol_title()%></a><br />
	
	<%} %></div>
<%}%>

 <!-- 帮助 --> <h1><a id="h77" href="/w/wap/help/help.jspx" onclick="change('h77')">帮助中心</a></h1>
 <div class="contant">
<a href="<%=response.encodeURL(path + "/wap/help/buySafe.jspx")%>">购彩安全</a>
<a
	href="<%=response.encodeURL(path
					+ "/wap/playIntroduced/introduce.jspx")%>">玩法介绍</a>
<a
	href="<%=response.encodeURL(path + "/wap/help/howCharge.jspx")%>">如何充值</a><br />
<a href="<%=response.encodeURL(path + "/wap/help/howBet.jspx")%>">如何投注</a>
<a
	href="/w/wap/help/howReward.jspx">如何兑奖</a>
<a
	href="/w/wap/help/howTCash.jspx">如何提款</a><br />
	<%
 	if (!"521".equals(CHANNEL)) {
 %>
<h1>客户端下载</h1>
<div class="contant">
&nbsp<a href="http://itunes.apple.com/cn/app/ru-yi-cai-nin-shen-bian-cai/id492164095?mt=8"><img
		src="/w/wap/newImages/p.png" alt="立即下载"></img>Iphone客户端下载</a><br/>
&nbsp<a href="/w/wap/client/down/android/ruyicai_android_3.8.1_533_wapguanwang.apk"><img 
		src="/w/wap/newImages/a.png" alt="立即下载"></img>Android客户端下载</a><br/>
	&nbsp<a href="http://www.windowsphone.com/zh-cn/store/app/%E5%A6%82%E6%84%8F%E5%BD%A9/b0bda9c5-2887-47e2-a487-ac9f481e8632"><img 
		src="/w/wap/images/wp8.png" alt="立即下载"></img>Windows Phone客户端下载</a><br/>	&nbsp<a href="/w/wap/client/down/symbian/ruyicai_S60_V5_1.2_779_gwwap.sisx"><img
		src="/w/wap/newImages/s.png" alt="立即下载"></img>客户端下载</a><br/>
</div>
	
<%
		}
	%>
	<%
		if (!"521".equals(CHANNEL)) {
	%>
		<a href="<%=response.encodeURL(path + "/client/360.jspx")%>">史上最强的手机工具新版发布</a><br/>
		
	<%
				}
			%>	
</div>
</body> 