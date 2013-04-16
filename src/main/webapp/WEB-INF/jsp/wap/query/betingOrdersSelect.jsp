<%@page import="com.ruyicai.wap.controller.WinSelectAction"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page
	import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ruyicai.wap.controller.vo.BetBean"%>
<%@ page import="com.ruyicai.wap.util.*,com.ruyicai.wap.bean.WinSelectInfoBean"%>
<%@ page import="java.util.logging.Logger"%>
<body>
<title>投注查询</title>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	int pageNum = 5;//每页显示条数 

	String startDate = request.getAttribute("startDate") == null ? ""
			: (String) request.getAttribute("startDate");
	String endDate = request.getAttribute("endDate") == null ? ""
			: (String) request.getAttribute("endDate");
	String nowPage = request.getAttribute("nowPage") == null ? "1"
			: (String) request.getAttribute("nowPage");
	String counter = request.getAttribute("count") == null ? ""
			: request.getAttribute("count").toString();

%>
<a href="/w/wap/wapindex.jspx">首页</a>-<a
	href="<%=response.encodeURL(path + "/wap/userinfo/userCenter.jspx")%>">用户中心</a>
<%
	out.print(CommonUtil.printChar());
%>投注查询
<br />
近期投注:
<br />
<%if (request.getParameter("select") == null
			|| request.getParameter("select") == "") {
		//进入查询页面时 不显示查询后的内容 执行查询后 才显示 
	} else {
		//获取request.getAttribute中的存储
		List jsonObjectList = request.getAttribute("error_code_list") == null ? new ArrayList()
				: (ArrayList) request.getAttribute("error_code_list");
		if (request.getAttribute("messageDate") != null) {
			out.print(request.getAttribute("messageDate"));
		} else if (jsonObjectList.size() < 1) {
			out.print("没有找到投注记录...");
		} else {
			int maxLine = 0;
			int pageCount = 0;
			BetBean jsonObject = (BetBean) jsonObjectList.get(0);
			if (counter.equals("") || counter == null) {
				maxLine = jsonObject.getMaxLine();
			} else {
				maxLine = Integer.parseInt(counter);
			}
			pageCount = maxLine % pageNum > 0 ? maxLine / pageNum + 1
					: maxLine / pageNum;
			for (int i = 0; i < jsonObjectList.size(); i++) {
				BetBean jsonObject2 = (BetBean) jsonObjectList.get(i);
				if("JCzq".equals(jsonObject2.getType())){
					out.println("<br/>彩种:"
							+jsonObject2.getPlay_name()+ "<br/>");
					out.println(jsonObject2.getBetType()
							+ "<br/>");
					out.println("倍数:" + jsonObject2.getMulNo() + "<br/>");
					out.println("金额:" + jsonObject2.getAmt() + "<br/>");
					out.println("投注时间:<br/>" + jsonObject2.getSell_cate()
							+ "<br/>");
					out.println("投注类型:" + jsonObject2.getOrderBetType()
							+ "<br/>");
					out.println("方案内容:<br/>" + jsonObject2.getBetcodeAll() + "<br/>");
					if(Integer.parseInt(jsonObject2.getPrizeamt())/100>0){
					out.println("中奖金额:" + Integer.parseInt(jsonObject2.getPrizeamt())/100 + "<br/>");
					}%>
					赛果开奖: <a
							href="/w/select/getJClqResult.jspx?type=1">点击查看</a><br/>
				<% }else if("JC".equals(jsonObject2.getType())){
					out.println("<br/>彩种:"
							+jsonObject2.getPlay_name()+ "<br/>");
					out.println(jsonObject2.getBetType()
							+ "<br/>");
					out.println("倍数:" + jsonObject2.getMulNo() + "<br/>");
					out.println("金额:" + jsonObject2.getAmt() + "<br/>");
					out.println("投注时间:<br/>" + jsonObject2.getSell_cate()
							+ "<br/>");
					out.println("投注类型:" + jsonObject2.getOrderBetType()
							+ "<br/>");
					out.println("方案内容:<br/>" + jsonObject2.getBetcodeAll() + "<br/>");
					if(Integer.parseInt(jsonObject2.getPrizeamt())/100>0){
					out.println("中奖金额:" + Integer.parseInt(jsonObject2.getPrizeamt())/100 + "<br/>");
					}%>
					赛果开奖: <a
							href="/w/select/getJClqResult.jspx?type=0">点击查看</a><br/>
				<% }else if("ZC".equals(jsonObject2.getType())){
					out.println("<br/>彩种:"
							+jsonObject2.getPlay_name()+ "<br/>");
					out.println("期号:" + jsonObject2.getBatchcode()
							+ "<br/>");
					out.println("倍数:" + jsonObject2.getMulNo() + "<br/>");
					out.println("金额:" + jsonObject2.getAmt() + "<br/>");
					out.println("投注时间:<br/>" + jsonObject2.getSell_cate()
							+ "<br/>");
					out.println("投注类型:" + jsonObject2.getOrderBetType()
							+ "<br/>");
					out.println("方案内容:<br/>" + jsonObject2.getBetcodeAll() + "<br/>");
					WinSelectInfoBean winSelectInfoBean = new WinSelectInfoBean();
					winSelectInfoBean = WinSelectAction.getWinfoByIssue(jsonObject2.getPlay_name(), jsonObject2
							.getBatchcode());
					if(Integer.parseInt(jsonObject2.getPrizeamt())/100>0){
						out.println("中奖金额:" + Integer.parseInt(jsonObject2.getPrizeamt())/100 + "<br/>");
						}
					if(winSelectInfoBean !=null){
						out.print("本期开奖:<br/>");
						out.print(winSelectInfoBean.getBetCode() + "<br/>");
					}%>				<% }else{
					out.println("<br/>彩种:"
							+ CommonUtil.getLotnameByLotno(jsonObject2
									.getPlay_name()) + "<br/>");
					out.println("期号:" + jsonObject2.getBatchcode()
							+ "<br/>");
					out.println("投注时间:<br/>" + jsonObject2.getSell_cate()
							+ "<br/>");
					Map jsonMap = jsonObject2.getBetcode();
					if(jsonMap.get("betType")!=null&&!"".equals(jsonMap.get("betType"))){
						out.println("投注方式:" + jsonMap.get("betType")
								+ "<br/>");
					}
					out.println("投注类型:" + jsonObject2.getOrderBetType()
							+ "<br/>");
					out.println("倍数:" + jsonMap.get("multiples") + "<br/>");
					out.println("金额:" + jsonObject2.getAmt() + "<br/>");
					out.println("注码:<br/>" + jsonObject2.getBetcodeAll() + "<br/>");
					WinSelectInfoBean winSelectInfoBean = new WinSelectInfoBean();
					winSelectInfoBean = WinSelectAction.getWinfoByIssue(jsonObject2.getPlay_name(), jsonObject2
							.getBatchcode());
					if(Integer.parseInt(jsonObject2.getPrizeamt())/100>0){
						out.println("中奖金额:" + Integer.parseInt(jsonObject2.getPrizeamt())/100 + "<br/>");
						}
					if(winSelectInfoBean !=null){
						out.print("本期开奖:<br/>");
						out.print(winSelectInfoBean.getBetCode() + "<br/>");
					}
				}
			
			}
			int nowPages = Integer.parseInt(nowPage);
			if (nowPages > 1 && nowPages < pageCount) {
%>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId" value="0" />
<input type="hidden" name="endId" value="<%=pageNum%>" /> <input
	type="hidden" name="select" value="yes" /> <input type="hidden"
	name="nowPage" value="1" /> <input type="submit" value="首页 " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=pageNum * (nowPages - 2) %>" /> <input type="hidden" name="endId"
	value="<%=pageNum%>" /> <input type="hidden"
	name="select" value="yes" /> <input type="hidden" name="nowPage"
	value="<%=nowPages - 1%>" /> <input type="submit" value="上一页 " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=pageNum * nowPages%>" /> <input type="hidden" name="endId"
	value="<%=pageNum%>" /> <input type="hidden" name="select" value="yes" />
<input type="hidden" name="nowPage" value="<%=nowPages + 1%>" /> <input
	type="submit" value="下一页  " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=(pageCount - 1) * pageNum%>" /> <input type="hidden"
	name="endId" value="<%=pageNum%>" /> <input type="hidden"
	name="select" value="yes" /> <input type="hidden" name="nowPage"
	value="<%=pageCount%>" /> <input type="submit" value="尾页  " /></form>
<%
	} else if (nowPages == 1 && pageCount > 1) {
%>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=pageNum * nowPages%>" /> <input type="hidden" name="endId"
	value="<%=pageNum%>" /> <input type="hidden" name="select" value="yes" />
<input type="hidden" name="nowPage" value="<%=nowPages + 1%>" /> <input
	type="submit" value="下一页  " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=(pageCount - 1) * pageNum%>" /> <input type="hidden"
	name="endId" value="<%=pageNum%>" /> <input type="hidden"
	name="select" value="yes" /> <input type="hidden" name="nowPage"
	value="<%=pageCount%>" /> <input type="submit" value="尾页  " /></form>
<%
	} else if (nowPages == pageCount && nowPages > 1) {
%>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId" value="0" />
<input type="hidden" name="endId" value="<%=pageNum%>" /> <input
	type="hidden" name="select" value="yes" /> <input type="hidden"
	name="nowPage" value="1" /> <input type="submit" value="首页  " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get"><input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=pageNum * (nowPages - 2)%>" /> <input type="hidden"
	name="endId" value="<%=pageNum%>" /> <input type="hidden"
	name="select" value="yes" /> <input type="hidden" name="nowPage"
	value="<%=nowPages - 1%>" /> <input type="submit" value="上一页  " /></form>
<%
	}
		}
	}
%>
<br />
<a
	href="<%=response.encodeURL(path + "/BetingSelectAction/bettingOrdersSelect.jspa?select=yes&amp;type=JQ")%>">查看近期投注</a>
<br />
<form
	action="<%=response.encodeURL(path + "/BetingSelectAction/bettingOrdersSelect.jspa")%>"
	method="get">开始日期: <%
	if (!startDate.equals("")) {
%> <input name="startDate" type="text" emptyok="true" maxlength="10"
	size="10" value="<%=startDate%>" /><br />
<%
	} else {
%> <input name="startDate" type="text" emptyok="true" maxlength="10"
	size="10"
	value="<%=new java.text.SimpleDateFormat("yyyyMMdd")
								.format(new Date(new Date().getTime() - 7 * 24
										* 60 * 60 * 1000))%>" /><br />
<%
	}
%> 结束日期: <%
	if (!startDate.equals("")) {
%> <input name="stopDate" type="text" emptyok="true" maxlength="10"
	size="10" value="<%=endDate%>" /><br />
<%
	} else {
%> <input name="stopDate" type="text" emptyok="true" maxlength="10"
	size="10"
	value="<%=new java.text.SimpleDateFormat("yyyyMMdd")
								.format(new Date())%>" /><br />
<%
	}
%>  <input
	type="hidden" name="select" value="yes" /> <input type="submit"
	value="查询" /></form>
<br />
<a
	href="<%=response.encodeURL(path + "/wap/userinfo/userCenter.jspx")%>">返回上一页</a>
</body>
