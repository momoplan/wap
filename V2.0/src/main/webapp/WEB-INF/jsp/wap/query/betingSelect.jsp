<%@ page pageEncoding="UTF-8"%>
<%@ page
	import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*"%>
<%@page import="com.ruyicai.wap.controller.WinSelectAction"%>
<%@ page import="com.ruyicai.wap.controller.vo.BetBean,com.ruyicai.wap.bean.WinSelectInfoBean"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%@ page import="java.util.logging.Logger"%>
<body><title>投注查询</title>
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
<%
	if (request.getParameter("select") == null
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
				out.println("<br/><br/>彩种:"
						+ CommonUtil.getLotnameByLotno(jsonObject2
								.getPlay_name()) + "<br/>");
				out.println("期号:" + jsonObject2.getBatchcode()
						+ "<br/>");
				out.println("投注时间:<br/>" + jsonObject2.getSell_cate()
						+ "<br/>");
				Map jsonMap = jsonObject2.getBetcode();
				out.println("投注方式:" + jsonMap.get("bet_code_type")
						+ "<br/>");
				out.println("倍数:" + jsonMap.get("multiples") + "<br/>");
				out.println("金额:" + jsonObject2.getAmt() + "<br/>");
				if (((String) jsonMap.get("doubleBallType"))
						.equals("S")
						|| ((String) jsonMap.get("doubleBallType"))
								.equals("0")) {
					out.println("注码:<br/>");
					for (int j = 0; jsonMap.get("bet_code" + j) != null; j++) {
						out.println(jsonMap.get("bet_code" + j)
								+ "<br/>");
						String url = CommonUtil.getUrl(jsonObject2, j);
%>
<a href="<%=response.encodeURL(path + "/" + url)%>">继续投注该号码</a>
<br />
<%
	}
				} else if (((String) jsonMap.get("doubleBallType"))
						.equals("T")) {
					out.println("红球胆码:<br/>"
							+ jsonMap.get("bet_code_redDanma")
							+ "<br/>");
					out.println("红球拖码:<br/>"
							+ jsonMap.get("bet_code_redTuoma")
							+ "<br/>");
					out.println("蓝球号码:<br/>"
							+ jsonMap.get("bet_code_blue") + "<br/>");
%>
<a
	href="<%=response
											.encodeURL(path
													+ "/"
													+ CommonUtil.getUrl(
															jsonObject2, 0))%>">继续投注该号码</a>
<br />
<%
	} else if (((String) jsonMap.get("doubleBallType"))
						.equals("QT")) {
					out.println("胆码:<br/>" + jsonMap.get("bet_codeD")
							+ "<br/>");
					out.println("拖码:<br/>" + jsonMap.get("bet_codeT")
							+ "<br/>");
%>
<a
	href="<%=response
											.encodeURL(path
													+ "/"
													+ CommonUtil.getUrl(
															jsonObject2, 0))%>">继续投注该号码</a>
<br />
<%
	} else if (((String) jsonMap.get("doubleBallType"))
						.equals("WX")) {
					out.println("百位:" + jsonMap.get("bai") + "<br/>");
					out.println("十位:" + jsonMap.get("shi") + "<br/>");
					out.println("个位:" + jsonMap.get("ge") + "<br/>");
%>
<a
	href="<%=response
											.encodeURL(path
													+ "/"
													+ CommonUtil.getUrl(
															jsonObject2, 0))%>">继续投注该号码</a>
<br />
<%
	} else if (((String) jsonMap.get("doubleBallType"))
						.equals("SSC")) {
					out.println("注码:<br/>");
					out.println((String) jsonMap.get("bet_code")
							+ "<br/>");

				} else if (((String) jsonMap.get("doubleBallType"))
						.equals("QXC")) {
					out.println("注码:<br/>");
					out.println((String) jsonMap.get("bet_code")
							+ "<br/>");

				} else if (((String) jsonMap.get("doubleBallType"))
						.equals("DLC")) {
					out.println("注码:<br/>");
					out.println((String) jsonMap.get("bet_code")
							+ "<br/>");

				}else if (((String) jsonMap.get("doubleBallType"))
						.equals("EDJ")) {
					out.println("注码:<br/>");
					out.println((String) jsonMap.get("bet_code")
							+ "<br/>");

				} else {
					out.println("注码:<br/>");
					String code = (String) jsonMap.get("bet_code0");
					if(code==null){
						code = (String) jsonMap.get("bet_code");
					}
					if (code.indexOf(";") > -1) {
						String arrCode[] = code.split(";");
						for (int k = 0; k < arrCode.length; k++) {
							out.println(arrCode[k] + "<br/>");
						}
						out.println("<br/>");
					} else {
						if(jsonMap.get("bet_code0")==null){
							out.println(jsonMap.get("bet_code") + "<br/>");
						}else{
							out.println(jsonMap.get("bet_code0") + "<br/>");
						}
						out.println("<br/>");
					}

					if (jsonObject2.getPlay_name().equals("T01001")
							|| jsonObject2.getPlay_name().equals(
									"T01002")
							|| jsonObject2.getPlay_name().equals(
									FinalVar.SHENGFUCAI14)
							|| jsonObject2.getPlay_name().equals(
									FinalVar.SHENGFUCAI9)
							|| jsonObject2.getPlay_name().equals(
									FinalVar.SIX_HALF)
							|| jsonObject2.getPlay_name().equals(
									FinalVar.FOUR_GOAL)) {
					} else {
%>
<%			
	}
				}
				WinSelectInfoBean winSelectInfoBean = new WinSelectInfoBean();
				winSelectInfoBean = WinSelectAction.getWinfoByIssue(jsonObject2.getPlay_name(), jsonObject2
						.getBatchcode());
				if(winSelectInfoBean !=null){
					out.print("本期开奖:<br/>");
					out.print(winSelectInfoBean.getBetCode() + "<br/>");
				}
				/**String zhumaDoubleBallStr = CommonUtil.getWinCode(
						jsonObject2.getPlay_name(), jsonObject2
								.getBatchcode());
				String zhuma = "";
				if (zhumaDoubleBallStr != null
						&& !zhumaDoubleBallStr.equals("")) {
					out.print("本期开奖：<br/>");
					String zhumaDoubleBallStr1 = zhumaDoubleBallStr
							.substring(7);
					if (jsonObject2.getPlay_name().equals("F47103")) {
						String zhuma3DStr11 = LotteryAlgorithmUtil
								.getStringFromZeroString3D(zhumaDoubleBallStr1);
						Vector vector = LotteryAlgorithmUtil
								.getStringArrayFromString3D(zhuma3DStr11);
						zhuma = LotteryAlgorithmUtil
								.joinStringArrayWithComma(vector);
					} else if (jsonObject2.getPlay_name().equals(
							"T01001")) {
						zhuma = zhumaDoubleBallStr1;
					} else if (jsonObject2.getPlay_name().equals(
							"T01002")) {
						Vector vector = LotteryAlgorithmUtil
								.getStringArrayFromString3D(zhumaDoubleBallStr1);
						zhuma = LotteryAlgorithmUtil
								.joinStringArrayWithComma(vector);
					} else if (jsonObject2.getPlay_name().equals(
							FinalVar.SHENGFUCAI14)
							|| jsonObject2.getPlay_name().equals(
									FinalVar.SHENGFUCAI9)
							|| jsonObject2.getPlay_name().equals(
									FinalVar.SIX_HALF)
							|| jsonObject2.getPlay_name().equals(
									FinalVar.FOUR_GOAL)) {
						zhuma = CommonUtil
								.getWinCodeWithCommaZc(zhumaDoubleBallStr1);
					} else if (jsonObject2.getPlay_name().equals(
							"T01007")) {
						zhuma = CommonUtil
								.getWinCodeReplace(zhumaDoubleBallStr
										.substring(9));
					} else if (jsonObject2.getPlay_name().equals(
							"T01009")) {
						zhuma = CommonUtil
								.getWinCodeReplace(zhumaDoubleBallStr1);
					} else if (jsonObject2.getPlay_name().equals(
							"T01011")) {
						zhuma = CommonUtil
								.getWinCodeReplace(zhumaDoubleBallStr1);
					} else if (jsonObject2.getPlay_name().equals(
							"T01010")) {
						zhuma = zhumaDoubleBallStr1.substring(3)
								.replaceAll(" ", ",");
					} else {
						zhuma = CommonUtil
								.getWinCodeWithComma(zhumaDoubleBallStr1);
					}
					out.print(zhuma + "<br/>");
				}*/
			}
			int nowPages = Integer.parseInt(nowPage);
			if (nowPages > 1 && nowPages < pageCount) {
%>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId" value="1" />
<input type="hidden" name="endId" value="<%=pageNum%>" /> <input
	type="hidden" name="select" value="yes" /> <input type="hidden"
	name="nowPage" value="1" /> <input type="submit" value="首页 " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
	method="get"><input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=pageNum%>" /> <input type="hidden" name="endId"
	value="<%=pageNum * (nowPages - 1) - 1%>" /> <input type="hidden"
	name="select" value="yes" /> <input type="hidden" name="nowPage"
	value="<%=nowPages - 1%>" /> <input type="submit" value="上一页 " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=pageNum * nowPages%>" /> <input type="hidden" name="endId"
	value="<%=pageNum%>" /> <input type="hidden" name="select" value="yes" />
<input type="hidden" name="nowPage" value="<%=nowPages + 1%>" /> <input
	type="submit" value="下一页  " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
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
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId"
	value="<%=pageNum * nowPages%>" /> <input type="hidden" name="endId"
	value="<%=pageNum%>" /> <input type="hidden" name="select" value="yes" />
<input type="hidden" name="nowPage" value="<%=nowPages + 1%>" /> <input
	type="submit" value="下一页  " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
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
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
	value="<%=startDate%>" /> <input type="hidden" name="stopDate"
	value="<%=endDate%>" /> <input type="hidden" name="beginId" value="1" />
<input type="hidden" name="endId" value="<%=pageNum%>" /> <input
	type="hidden" name="select" value="yes" /> <input type="hidden"
	name="nowPage" value="1" /> <input type="submit" value="首页  " /></form>
<form
	action="<%=response.encodeURL(path
										+ "/BetingSelectAction/bettingSelect.jspa")%>"
	method="get"> <input type="hidden" name="startDate"
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
	href="<%=response.encodeURL(path + "/BetingSelectAction/bettingSelect.jspa?select=yes&amp;type=JQ")%>">查看近期投注</a>
<br />
<form
	action="<%=response.encodeURL(path + "/BetingSelectAction/bettingSelect.jspa")%>"
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
%> <input
	type="hidden" name="select" value="yes" /> <input type="submit"
	value="查询" /></form>
<br />
<a
	href="<%=response.encodeURL(path + "/wap/userinfo/userCenter.jspx")%>">返回上一页</a>
</body>
