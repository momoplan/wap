<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ruyicai.wap.bean.WinSelectInfoBean"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<WinSelectInfoBean> listball = (List<WinSelectInfoBean>) request
			.getAttribute("listball");
String batchcode="";
String betcode="";
if(listball!=null){
	batchcode = listball.get(0).getBatchcode();
	betcode = listball.get(0).getBetCode()==null?"":listball.get(0).getBetCode();
}
	
	List<WinSelectInfoBean> list3D = (List<WinSelectInfoBean>) request
			.getAttribute("list3D");
	String batchcode3D = "";
	String betcode3D="";
	if(list3D!=null){
		batchcode3D = list3D.get(0).getBatchcode();
		if(batchcode3D.equals("null")) batchcode3D = CommonUtil.getTerm("F47103");
		betcode3D = list3D.get(0).getBetCode()==null?"":list3D.get(0).getBetCode();
	}
	
	List<WinSelectInfoBean> listQilecai = (List<WinSelectInfoBean>) request
			.getAttribute("listQilecai");
	String batchcodelistQilecai="";
	String betcodelistQilecai="";
	if(listQilecai!=null){
		batchcodelistQilecai = listQilecai.get(0).getBatchcode();
		betcodelistQilecai = listQilecai.get(0).getBetCode()==null?"":listQilecai.get(0).getBetCode();
	}
	
	List<WinSelectInfoBean> listDaletou = (List<WinSelectInfoBean>) request.getAttribute("listDaletou");
	String batchcodelistDaletou = "";
	String betcodelistDaletou = "";
	if(listDaletou!=null){
		batchcodelistDaletou = listDaletou.get(0).getBatchcode()==null?"":listDaletou.get(0).getBatchcode();
		betcodelistDaletou = listDaletou.get(0).getBetCode();
	}
	List<WinSelectInfoBean> listPaisan = (List<WinSelectInfoBean>) request
			.getAttribute("listPaisan");
	String batchcodelistPaisan = "";
	String betcodelistPaisan = "";
	if(listPaisan!=null){
		batchcodelistPaisan = listPaisan.get(0).getBatchcode();
		betcodelistPaisan = listPaisan.get(0).getBetCode()==null?"":listPaisan.get(0).getBetCode();
	}
	
	List<WinSelectInfoBean> listQixing = (List<WinSelectInfoBean>) request
			.getAttribute("listQixing");
	String betcodelistQixing="";
	String batchcodelistQixing = "";
	if(listQixing!=null){
		batchcodelistQixing = listQixing.get(0).getBatchcode();
		betcodelistQixing = listQixing.get(0).getBetCode()==null?"":listQixing.get(0).getBetCode();
	}
	
	List<WinSelectInfoBean> listPaiwu = (List<WinSelectInfoBean>) request
			.getAttribute("listPaiwu");
	String batchcodelistPaiwu = "";
	String betcodelistPaiwu = "";
	
	if(listPaiwu!=null){
		batchcodelistPaiwu = listPaiwu.get(0).getBatchcode();
		betcodelistPaiwu = listPaiwu.get(0).getBetCode();
	}
	
	List<WinSelectInfoBean> listSSC = (List<WinSelectInfoBean>) request
			.getAttribute("listSSC");
	String batchcodelistSSC = "";
	String betcodelistSSC = "";
	if(listSSC!=null){
		batchcodelistSSC = listSSC.get(0).getBatchcode();
		betcodelistSSC = listSSC.get(0).getBetCode();
	}
	
	List<WinSelectInfoBean> listDuolecai = (List<WinSelectInfoBean>) request
			.getAttribute("listDuolecai");
	String batchcodelistDuolecai = "";
	String betcodelistDuolecai = "";
	if(listDuolecai!=null){
		batchcodelistDuolecai = listDuolecai.get(0).getBatchcode();
		betcodelistDuolecai = listDuolecai.get(0).getBetCode();
	}
	List<WinSelectInfoBean> listElevenDuoJin = (List<WinSelectInfoBean>) request
	.getAttribute("listElevenDuoJin");
	String batchcodelistElevenDuoJin = "";
	String betcodelistElevenDuoJin = "";
	if(listElevenDuoJin!=null){
		batchcodelistElevenDuoJin = listElevenDuoJin.get(0).getBatchcode();
		betcodelistElevenDuoJin = listElevenDuoJin.get(0).getBetCode();
	}
	List<WinSelectInfoBean> list22Select5 = (List<WinSelectInfoBean>) request
			.getAttribute("list22Select5");
			String batchcodelist22Select5 = "";
			String betcodelist22Select5 = "";
			if(list22Select5!=null){
				batchcodelist22Select5 = list22Select5.get(0).getBatchcode();
				betcodelist22Select5 = list22Select5.get(0).getBetCode();
			}
	List<WinSelectInfoBean> listGuangDongElevenSelectFive = (List<WinSelectInfoBean>) request
				.getAttribute("listGuangDongElevenSelectFive");
				String batchcodelistGuangDongElevenSelectFive = "";
				String betcodelistGuangDongElevenSelectFive = "";
				if(listGuangDongElevenSelectFive!=null){
					batchcodelistGuangDongElevenSelectFive = listGuangDongElevenSelectFive.get(0).getBatchcode();
					betcodelistGuangDongElevenSelectFive = listGuangDongElevenSelectFive.get(0).getBetCode();
				}
	List<WinSelectInfoBean> listGuangDongHappyTenMinutes = (List<WinSelectInfoBean>) request
			.getAttribute("listGuangDongHappyTenMinutes");
	String batchcodelistGuangDongHappyTenMinutes = "";
	String betcodelistGuangDongHappyTenMinutes = "";
	if(listGuangDongHappyTenMinutes!=null){
		batchcodelistGuangDongHappyTenMinutes = listGuangDongHappyTenMinutes.get(0).getBatchcode();
		betcodelistGuangDongHappyTenMinutes = listGuangDongHappyTenMinutes.get(0).getBetCode();
	}
	List<WinSelectInfoBean> listZC14 = (List<WinSelectInfoBean>) request
			.getAttribute("listZC14");
	String batchcodelistZC14 = listZC14.get(0).getBatchcode();
	String betcodelistZC14 = listZC14.get(0).getBetCode();
	List<WinSelectInfoBean> listZC9 = (List<WinSelectInfoBean>) request
			.getAttribute("listZC9");
	String batchcodelistZC9 = listZC9.get(0).getBatchcode();
	String betcodelistZC9 = listZC9.get(0).getBetCode();
	List<WinSelectInfoBean> listZC6 = (List<WinSelectInfoBean>) request
			.getAttribute("listZC6");
	String batchcodelistZC6 = listZC6.get(0).getBatchcode();
	String betcodelistZC6 = listZC6.get(0).getBetCode();
	List<WinSelectInfoBean> listZC4 = (List<WinSelectInfoBean>) request
			.getAttribute("listZC4");
	String batchcodelistZC4 = listZC4.get(0).getBatchcode();
	String betcodelistZC4 = listZC4.get(0).getBetCode();
%>

<title>开奖信息</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-开奖公告
<br />
<div>
</div>
<%if(listball!=null){%>
	<div><%=batchcode%>期双色球:
<%=betcode%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=F47104&amp;issue=<%=batchcode%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=F47104&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=F47104&batchcode=<%=batchcode%>&key=F47104MV_X">遗漏</a>·<a href="/w/wap/trendChart/trendChartSSQ.jspx">走势</a>·<a
	href="/w/wap/DoubleBall/SingleSelfSelection.jspx">购买</a> <br />
<br />
</div>
<% } %>
<%if(list3D!=null){%>
	<div><%=batchcode3D%>期福彩3D:
<%=betcode3D%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=F47103&amp;issue=<%=batchcode3D%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=F47103&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=F47103&batchcode=<%=batchcode3D%>&key=F47103MV_ZX">遗漏</a>·<a href="/w/wap/trendChart/trendChart3D.jspx">走势</a>·<a
	href="/w/wap/3D/3DDirectSingle.jspx">购买</a> <br /><br />
</div>
<% } %>

<%if(listQilecai!=null){%>
	<div><%=batchcodelistQilecai%>期七乐彩:
<%=betcodelistQilecai%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=F47102&amp;issue=<%=batchcodelistQilecai%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=F47102&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=F47102&batchcode=<%=batchcodelistQilecai%>&key=F47102MV_X">遗漏</a>·<a href="/w/wap/trendChart/trendChartQLC.jspx">走势</a>·<a
	href="/w/wap/qilecai/SingleSelfSelection.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listDaletou!=null){%>
	<div><%=batchcodelistDaletou%>期大乐透:
<%=betcodelistDaletou%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01001&amp;issue=<%=batchcodelistDaletou%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01001&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01001&batchcode=<%=batchcodelistDaletou%>&key=T01001MV_X">遗漏</a>·<a href="/w/wap/trendChart/trendChartDLT.jspx">走势</a>·<a
	href="/w/wap/daletou/SingleSelfSelection.jspx">购买</a> <br />
<br />
</div>
<% } %>

<%if(listPaisan!=null){%>
	<div><%=batchcodelistPaisan%>期排列三:
<%=betcodelistPaisan%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01002&amp;issue=<%=batchcodelistPaisan%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01002&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01002&batchcode=<%=batchcodelistPaisan%>&key=T01002MV_ZX">遗漏</a>·<a href="/w/wap/trendChart/trendChartPLS.jspx">走势</a>·<a
	href="/w/wap/array3/Array3DirectSingle.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listPaiwu!=null){%>
	<div><%=batchcodelistPaiwu%>期排列五:
<%=betcodelistPaiwu%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01011&amp;issue=<%=batchcodelistPaiwu%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01011&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01011&batchcode=<%=batchcodelistPaiwu%>&key=T01011MV_ZX">遗漏</a>·<a href="/w/wap/trendChart/trendChartPLW.jspx">走势</a>·<a
	href="/w/wap/array5/array5ByHand.jspx">购买</a> <br /><br />
</div>
<%} %>

<%if(listQixing!=null){%>
	<div><%=batchcodelistQixing%>期七星彩:
<%=betcodelistQixing%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01009&amp;issue=<%=batchcodelistQixing%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01009&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01009&batchcode=<%=batchcodelistQixing%>&key=T01009MV_ZX">遗漏</a>·<a href="/w/wap/trendChart/trendChartQXC.jspx">走势</a>·<a
	href="/w/wap/sevenStar/sevenStarByHand.jspx">购买</a> <br /><br />
</div>
<%} %>

<%if(list22Select5!=null){%>
	<div><%=batchcodelist22Select5%>期22选5:
<%=betcodelist22Select5%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01013&amp;issue=<%=batchcodelist22Select5%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01013&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01013&batchcode=<%=batchcodelist22Select5%>&key=T01013MV_X">遗漏</a>·<a href="/w/wap/trendChart/trendChart22X5.jspx">走势</a>·<a
	href="/w/select5from22/selfSelect.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listSSC!=null){%>
	<div>
	<%=batchcodelistSSC%>期时时彩:
<%=betcodelistSSC%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01007&amp;issue=<%=batchcodelistSSC%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01007&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01007&batchcode=<%=batchcodelistSSC%>&key=T01007MV_5X&type=WXZhX">遗漏</a>·<a
	href="/w/wap/cqshishicai/sscOneStar.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listDuolecai!=null){%>
<div><%=batchcodelistDuolecai%>期江西11选5:
<%=betcodelistDuolecai%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01010&amp;issue=<%=batchcodelistDuolecai%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01010&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01010&batchcode=<%=batchcodelistDuolecai%>&key=T01010MV_RX&type=rx2">遗漏</a>·<a href="/w/trendChart/trendChartDetail.jspx?lotno=T01010&imgName=dlcRed1&type=1">走势</a>·<a
	href="/w/wap/11select5/optionOne.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listElevenDuoJin!=null){%>
<div><%=batchcodelistElevenDuoJin%>期山东十一运夺金:
<%=betcodelistElevenDuoJin%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01012&amp;issue=<%=batchcodelistElevenDuoJin%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01012&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01012&batchcode=<%=batchcodelistElevenDuoJin%>&key=T01012MV_RX&type=rx2">遗漏</a>·<a href="/w/trendChart/trendChartDetail.jspx?lotno=T01012&imgName=11ydjRed1&type=1">走势</a>·<a
	href="/w/wap/ElevenDuoJin/optionOne.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listGuangDongElevenSelectFive!=null){%>
<div><%=batchcodelistGuangDongElevenSelectFive%>期广东11选5:
<%=betcodelistGuangDongElevenSelectFive%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01014&amp;issue=<%=batchcodelistGuangDongElevenSelectFive%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01014&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01014&batchcode=<%=batchcodelistGuangDongElevenSelectFive%>&key=T01014MV_RX&type=rx2">遗漏</a>·<a 
	href="/w/trendChart/trendChartDetail.jspx?lotno=T01014&imgName=gd11x5Red1&type=1">走势</a>·<a
	href="/w/guangDongElevenSelectFiveIndex/optionOneSelf.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listGuangDongHappyTenMinutes!=null){%>
<div><%=batchcodelistGuangDongHappyTenMinutes%>期广东快乐十分:
<%=betcodelistGuangDongHappyTenMinutes%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01015&amp;issue=<%=batchcodelistGuangDongHappyTenMinutes%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01015&qiShu=10">历史</a>·<a
	href="/w/leaveOut/leaveOutDetail.jspx?lotno=T01015&batchcode=<%=batchcodelistGuangDongHappyTenMinutes%>&key=T01015MV_S1&type=S1">遗漏</a>·<a 
	href="/w/wap/trendChart/trendChartKL10.jspx">走势</a>·<a
	href="/w/guangDongHappyTenMinutesIndex/selectOneNumberSelf.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listZC14!=null){%>
<div><%=batchcodelistZC14%>期足彩胜负彩:
<%=betcodelistZC14%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01003&amp;issue=<%=batchcodelistZC14%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01003&qiShu=10">历史</a>·<a
	href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01003">购买</a> <br /><br />
</div>
<%} %>
<%if(listZC9!=null){%>
<div><%=batchcodelistZC9%>期足彩任选九:
<%=betcodelistZC9%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01004&amp;issue=<%=batchcodelistZC9%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01004&qiShu=10">历史</a>·<a
	href="<%=request.getContextPath()%>/zc/zcduizhen.jspx?lotno=T01004">购买</a> <br /><br />
</div>
<%} %>
<%if(listZC6!=null){%>
<div><%=batchcodelistZC6%>期足彩六场半:
<%=betcodelistZC6%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01006&amp;issue=<%=batchcodelistZC6%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01006&qiShu=10">历史</a>·<a
	href="/wap/11select5/11select5Index.jspx">购买</a> <br /><br />
</div>
<%} %>
<%if(listZC4!=null){%>
<div><%=batchcodelistZC4%>期足彩四场进球:
<%=betcodelistZC4%><br />
<a
	href="/w/select/lotterySelect.jspx?lotno=T01005&amp;issue=<%=batchcodelistZC4%>">详细</a>·<a
	href="/w/select/lotterySelectByType.jspx?lotno=T01005&qiShu=10">历史</a>·<a
	href="/wap/ElevenDuoJin/index.jspx">购买</a> <br /><br />
</div>
<%} %>
竞彩篮球：<br/>
<a href="/w/select/getJClqResult.jspx?type=0">查看比赛结果</a> <br />
竞彩足球：<br/>
<a href="/w/select/getJClqResult.jspx?type=1">查看比赛结果</a> <br />
<br />
<a href="/wap/wapindex.jspx">返回上一页</a>

</body>
