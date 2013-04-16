<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="com.ruyicai.wap.controller.vo.WiningBean"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<%
	List jsonObjectList = request.getAttribute("error_code_list") == null ? new ArrayList()
			: (ArrayList) request.getAttribute("error_code_list");
	String nowPage = request.getAttribute("nowPage") == null ? "1"
			: (String) request.getAttribute("nowPage");
	String counter = request.getAttribute("count") == null ? ""
			: request.getAttribute("count").toString();
	int pageNum = 5;//每页显示条数
%>
<title>中奖信息</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
<%
	out.print(CommonUtil.printChar());
%>中奖查询<br />
<br />
<%
	try {
		if (jsonObjectList.size() == 0) {
			out.println("没有中奖记录...");
		} else {
			int maxLine = 0;
			int pageCount = 0;
			WiningBean jsonObject = (WiningBean) jsonObjectList.get(0);
			if (counter.equals("") || counter == null) {
				maxLine = jsonObject.getMaxLine();
			} else {
				maxLine = Integer.parseInt(counter);
			}
			pageCount = maxLine % pageNum > 0 ? maxLine / pageNum + 1
					: maxLine / pageNum;

			for (int i = 0; i < jsonObjectList.size(); i++) {
				WiningBean jsonObject2 = (WiningBean) jsonObjectList
						.get(i);
				if("JCzq".equals(jsonObject2.getType())){
					out.println("<br/><br/>彩种:"
							+jsonObject2.getPlay_name()+ "<br/>");
					out.println("玩法:" + jsonObject2.getBetType()
							+ "<br/>");
					out.println("倍数:" + jsonObject2.getMulNo() + "<br/>");
					out.println("中奖金额:" + (float)jsonObject2.getPrizeamt()/100
							+ "元<br/>");
					out.println("方案内容:<br/>" + jsonObject2.getBetcodeAll() + "<br/>");%>
					赛果开奖: <a
							href="/w/select/getJClqResult.jspx?type=1">点击查看</a><br/>
				<% }else if("JC".equals(jsonObject2.getType())){
					out.println("<br/><br/>彩种:"
							+jsonObject2.getPlay_name()+ "<br/>");
					out.println("玩法:" + jsonObject2.getBetType()
							+ "<br/>");
					out.println("倍数:" + jsonObject2.getMulNo() + "<br/>");
					out.println("中奖金额:" + (float)jsonObject2.getPrizeamt()/100
							+ "元<br/>");
					out.println("方案内容:<br/>" + jsonObject2.getBetcodeAll() + "<br/>");%>
					赛果开奖: <a
							href="/w/select/getJClqResult.jspx?type=0">点击查看</a><br/>
				<% }else{
					out.println("彩种:"
							+ CommonUtil.getLotnameByLotno(jsonObject2.getPlay_name())
							+ "<br/>");
					out.println("期号:" + jsonObject2.getBatchcode()
							+ "<br/>");

					String prizeinfo = (String) jsonObject2.getPrizeinfo();
					int winBit = 0;
					int num = 0;
					int startIndex = 0;
					int endIndex = -1;
					out.println("中奖金额:" + (float)jsonObject2.getPrizeamt()/100
							+ "元<br/>");
					out.println("兑奖标记:");
					String encash_flag = jsonObject2.getEncash_flag();
					if (encash_flag.equals("0"))
						encash_flag = "未兑奖";
					else if (encash_flag.equals("1"))
						encash_flag = "等待开奖";
					else if (encash_flag.equals("2"))
						encash_flag = "开奖处理中";
					else if (encash_flag.equals("3"))
						encash_flag = "已兑奖";
					else if (encash_flag.equals("4"))
						encash_flag = "大奖";
					else if (encash_flag.equals("5"))
						encash_flag = "小奖";
					out.println(encash_flag + "<br/>");
					String selltime = jsonObject2.getSell_cate();
				
					out.println("投注时间:" + selltime+ "<br/>");
					//out.println("弃奖时间:"+jsonObject2.getString("abandon_date_time")+"<br/>");

					Map jsonMap = jsonObject2.getBetcode();
					if (jsonMap.get("betType") != null
							|| !jsonMap.get("betType").equals("")) {
						out.println("投注方式:" + jsonMap.get("betType")
								+ "<br/>");
				}	
					String beishu=(String)jsonObject2.getBetcode().get("multiples");
					if(!"".equals(beishu)&&beishu!=null){
						out.println("倍数:"
								+ beishu
								+ "<br/>");
					}
					String betCodeView=(String)jsonObject2.getBetcode().get("betCodeView");
					
					if(!"".equals(betCodeView)&&beishu!=null){
						out.println("注码:<br/>"
								+ betCodeView
								+ "<br/><br/>");
					}
				}
				}
				

			int nowPages = Integer.parseInt(nowPage);
			if (nowPages > 1 && nowPages < pageCount) {
%>
<form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden"  name="beginId" value="0" />
<input type="hidden"  name="endId" value="<%=pageNum%>" />
<input type="hidden"  name="nowPage" value="1" />
<input type="submit" value="首页 "/>
</form>
<form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden" name="beginId" value="<%=pageNum * (nowPages - 2)%>" />
<input type="hidden" name="endId" value="<%=pageNum * (nowPages - 1)%>" />
<input type="hidden" name="nowPage" value="<%=nowPages - 1%>" />
<input type="submit" value="上一页 "/>
</form>
<form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden" name="beginId" value="<%=pageNum * nowPages%>" />
<input type="hidden" name="endId" value="<%=pageNum * (nowPages + 1)%>" />
<input type="hidden" name="nowPage" value="<%=nowPages + 1%>" />
<input type="submit" value="下一页 "/>
</form>
<form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden" name="beginId" value="<%=(pageCount - 1) * pageNum%>" />
<input type="hidden" name="endId" value="<%=pageNum * pageCount%>" />
<input type="hidden" name="nowPage" value="<%=pageCount%>" />
<input type="submit" value="尾页 "/>
</form><%
	} else if (nowPages == 1 && pageCount > 1) {
%> 
 <form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden" name="beginId" value="<%=pageNum * nowPages%>" />
<input type="hidden" name="endId" value="<%=pageNum * (nowPages + 1)%>" />
<input type="hidden" name="nowPage" value="<%=nowPages + 1%>" />
<input type="submit" value="下一页 "/>
</form>
 <form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden" name="beginId" value="<%=(pageCount - 1) * pageNum%>" />
<input type="hidden" name="endId" value="<%=pageNum * pageCount%>" />
<input type="hidden" name="nowPage" value="<%=pageCount%>" />
<input type="submit" value="尾页 "/>
</form> <%
 	} else if (nowPages == pageCount && nowPages > 1) {
 %> 
  <form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden" name="beginId" value="0" />
<input type="hidden" name="endId" value="<%=pageNum%>" />
<input type="hidden" name="nowPage" value="1" />
<input type="submit" value="首页 "/>
</form> 
  <form action="<%=response.encodeURL(path
										+ "/BetingSelectAction/getWiningSelectTorders.jspa")%>" method="get">
<input type="hidden"  name="beginId" value="<%=pageNum * (nowPages - 2)%>" />
<input type="hidden"  name="endId" value="<%=pageNum * (nowPages - 1)%>" />
<input type="hidden"  name="nowPage" value="<%=nowPages - 1%>" />
<input type="submit" value="上一页 "/>
</form>  <%
  	}

  		}

  	} catch (Exception e) {
  		e.printStackTrace();
  	}
  %> <br />
 <a href="<%=response.encodeURL(path + "/wap/userinfo/userCenter.jspx")%>">返回上一页</a>
</body>
