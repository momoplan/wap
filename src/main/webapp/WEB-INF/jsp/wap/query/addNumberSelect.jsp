<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat,net.sf.json.JSONObject,net.sf.json.JSONArray" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	JSONObject object = request.getAttribute("jsonObject")==null ? new JSONObject():(JSONObject)request.getAttribute("jsonObject");
	String ordertime = request.getAttribute("ordertime")==null?"":(String)request.getAttribute("ordertime");
	String lotno = request.getAttribute("lotno")==null?"":(String)request.getAttribute("lotno");
	String endDate = request.getAttribute("endDate")==null?"":(String)request.getAttribute("endDate");
	String nowPage = request.getAttribute("nowPage")==null?"1":(String) request.getAttribute("nowPage");
	Integer nowPageInt = Integer.parseInt(nowPage); //当前页
	Integer maxLine = 0; //最大记录数
	Integer pageNum = 10; //每页显示条数 
	Integer pageCount = 0; //总页数
	String flowNo = ""; //流水号
%>
<title>追号查询</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
		<%out.print(CommonUtil.printChar());%>追号查询<br/>
		
		<%
		JSONObject value = object.getJSONObject("value");
		JSONArray jsonArray = value.getJSONArray("list");
			if(jsonArray != null && jsonArray.size() > 0) {
				JSONObject jsonObject = null;
				maxLine = Integer.parseInt(value.getString("totalResult")); //最大记录数
				pageCount=maxLine%pageNum>0?maxLine/pageNum+1:maxLine/pageNum; //总页数
				for(int i =0; i<jsonArray.size(); i++){
					jsonObject = (JSONObject)jsonArray.get(i);
					flowNo = jsonObject.getString("flowno");
					String lotName = CommonUtil.getLotnameByLotno((String)jsonObject.get("lotno")); //彩种名称
					Integer batchNum = (Integer)jsonObject.get("batchnum"); //购买期数
					Integer lastNum = (Integer)jsonObject.get("lastnum"); //剩余购买期数
					Integer state = (Integer)jsonObject.get("state"); //状态
					String stateStr = "";
					if (state==0) {
						if (lastNum>0) {
							stateStr = "进行中";
						} else {
							stateStr = "已追完";
						}
					}
					if (state==2) {
						if (lastNum>0) {
							stateStr = "已撤销";
						}else {
							stateStr = "已追完";
						} 
					}
					if(state==3){
						stateStr="已追完";
					}
					out.println("<a href=\""+path+"/BetingSelectAction/addNumberDetailByTorders.jspa?endLine = "+batchNum+"&flowNo="+flowNo+"\">"+(i+1)+"."+lotName+",追"+batchNum+"期,已成功"+(batchNum-lastNum)+"期&nbsp;"+stateStr+"</a><br/>");
				}
			} else {
				out.print("没有找到追号记录...");
			}
		%>
		<br/>
		<% if(nowPageInt>1 && nowPageInt<pageCount) { %>
		<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden" name="ordertime" value="<%=ordertime %>" />
				<input type="hidden" name="lotno" value="<%=lotno %>" />
				<input type="hidden" name="beginId" value="0" />
				<input type="hidden" name="endId" value="<%=pageNum %>" />
				<input type="hidden" name="nowPage" value="1" />
			<input type="submit" value="首页 " />
			</form>
			<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden" name="ordertime" value="<%=ordertime %>" />
				<input type="hidden" name="lotno" value="<%=lotno %>" />
				<input type="hidden" name="beginId" value="<%=pageNum*(nowPageInt-2)%>" />
				<input type="hidden" name="endId" value="<%=pageNum %>" />
				<input type="hidden" name="nowPage" value="<%=nowPageInt-1 %>" />
			<input type="submit" value="上一页 " />
			</form>
			<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden" name="ordertime" value="<%=ordertime %>" />
				<input type="hidden" name="lotno" value="<%=lotno %>" />
				<input type="hidden" name="beginId" value="<%=pageNum*nowPageInt%>" />
				<input type="hidden" name="endId" value="<%=pageNum %>" />
				<input type="hidden" name="nowPage" value="<%=nowPageInt+1 %>" />
			<input type="submit" value="下一页  " />
			</form>
			<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden"  name="ordertime" value="<%=ordertime %>" />
				<input type="hidden"  name="lotno" value="<%=lotno %>" />
				<input type="hidden"  name="beginId" value="<%=(pageCount-1)*pageNum %>" />
				<input type="hidden"  name="endId" value="<%=pageNum%>" />
				<input type="hidden"  name="nowPage" value="<%=pageCount %>" />
			<input type="submit" value="尾页   " />
			</form>
		<% } else if (nowPageInt==1 && pageCount>1) { %>
		<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden"  name="ordertime" value="<%=ordertime %>" />
				<input type="hidden"  name="lotno" value="<%=lotno %>" />
				<input type="hidden"  name="beginId" value="<%=pageNum*nowPageInt %>" />
				<input type="hidden"  name="endId" value="<%=pageNum %>" />
				<input type="hidden"  name="nowPage" value="<%=nowPageInt+1 %>" />
			<input type="submit" value="下一页    " />
			</form>
			<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden"  name="ordertime" value="<%=ordertime %>" />
				<input type="hidden"  name="lotno" value="<%=lotno %>" />
				<input type="hidden"  name="beginId" value="<%=(pageCount-1)*pageNum%>" />
				<input type="hidden"  name="endId" value="<%=pageNum %>" />
				<input type="hidden"  name="nowPage" value="<%=pageCount %>" />
			<input type="submit" value="尾页    " />
			</form>
		<%} else if (nowPageInt==pageCount && nowPageInt>1) { %>
		<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden"  name="ordertime" value="<%=ordertime %>" />
				<input type="hidden"  name="lotno" value="<%=lotno %>" />
				<input type="hidden"  name="beginId" value="0" />
				<input type="hidden"  name="endId" value="<%=pageNum %>" />
				<input type="hidden"  name="nowPage" value="1" />
			<input type="submit" value="首页     " />
			</form>
			<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
				<input type="hidden" name="ordertime" value="<%=ordertime %>" />
				<input type="hidden" name="lotno" value="<%=lotno %>" />
				<input type="hidden" name="beginId" value="<%=pageNum*(nowPageInt-2) %>" />
				<input type="hidden" name="endId" value="<%=pageNum %>" />
				<input type="hidden" name="nowPage" value="<%=nowPageInt-1 %>" />
			<input type="submit" value="上一页      " />
			</form>
		<%} %>
		<br/>
		<form action="<%=response.encodeURL(path+"/BetingSelectAction/addNumberSelect.jspa")%>" method="get">
		日期:
		<select name="ordertime">
			<%if (ordertime.trim().equals("fifteenth")) { %>
				<option value="fifteenth">近15天</option>
				<option value="seven">近7天</option>
				<option value="thirty">近30天</option>
				<option value="all">全部</option>
			<%} else if (ordertime.trim().equals("thirty")) { %>
				<option value="thirty">近30天</option>
				<option value="seven">近7天</option>
				<option value="fifteenth">近15天</option>
				<option value="all">全部</option>
			<%} else if (ordertime.trim().equals("seven")){ %>
				<option value="seven">近7天</option>
				<option value="fifteenth">近15天</option>
				<option value="thirty">近30天</option>
				<option value="all">全部</option>
			<%} else {%>
				<option value="all">全部</option>
				<option value="seven">近7天</option>
				<option value="fifteenth">近15天</option>
				<option value="thirty">近30天</option>
			<%} %>
		</select><br/>
		彩种:
		<select name="lotno">
			<%if (lotno.trim().equals("F47104")) { %>
				<option value="F47104">双色球</option>
				<option value="all">全部</option>
				<option value="F47103">福彩3D</option>
				<option value="F47102">七乐彩</option>
				<option value="T01001">大乐透</option>
				<option value="T01002">排列三</option>
				<option value="T01009">七星彩</option>
				<option value="T01007">时时彩</option>
				<option value="T01011">排列五</option>
				<option value="T01010">江西11选5</option>
				<option value="T01012">十一运夺金</option>
				<option value="T01013">22选5</option>
				<option value="T01014">广东11选5</option>
				<option value="T01015">广东快乐十分</option>
			<%} else if (lotno.trim().equals("F47103")) { %>
				<option value="F47103">福彩3D</option>
				<option value="all">全部</option>
				<option value="F47104">双色球</option>
				<option value="F47102">七乐彩</option>
				<option value="T01001">大乐透</option>
				<option value="T01002">排列三</option>
				<option value="T01009">七星彩</option>
				<option value="T01007">时时彩</option>
				<option value="T01011">排列五</option>
				<option value="T01010">江西11选5</option>
				<option value="T01012">十一运夺金</option>
				<option value="T01013">22选5</option>
				<option value="T01014">广东11选5</option>
				<option value="T01015">广东快乐十分</option>
			<%} else if (lotno.trim().equals("F47102")) { %>
				<option value="F47102">七乐彩</option>
				<option value="all">全部</option>
				<option value="F47104">双色球</option>
				<option value="F47103">福彩3D</option>
				<option value="T01001">大乐透</option>
				<option value="T01002">排列三</option>
				<option value="T01009">七星彩</option>
				<option value="T01007">时时彩</option>
				<option value="T01011">排列五</option>
				<option value="T01010">江西11选5</option>
				<option value="T01012">十一运夺金</option>
				<option value="T01013">22选5</option>
				<option value="T01014">广东11选5</option>
				<option value="T01015">广东快乐十分</option>
			<%} else if (lotno.trim().equals("T01001")) { %>
				<option value="T01001">大乐透</option>
				<option value="all">全部</option>
				<option value="F47104">双色球</option>
				<option value="F47103">福彩3D</option>
				<option value="F47102">七乐彩</option>
				<option value="T01002">排列三</option>
				<option value="T01009">七星彩</option>
				<option value="T01007">时时彩</option>
				<option value="T01011">排列五</option>
				<option value="T01010">江西11选5</option>
				<option value="T01012">十一运夺金</option>
				<option value="T01013">22选5</option>
				<option value="T01014">广东11选5</option>
				<option value="T01015">广东快乐十分</option>
			<%} else if (lotno.trim().equals("T01002")) { %>
				<option value="T01002">排列三</option>
				<option value="T01001">大乐透</option>
				<option value="all">全部</option>
				<option value="F47104">双色球</option>
				<option value="F47103">福彩3D</option>
				<option value="F47102">七乐彩</option>
				<option value="T01009">七星彩</option>
				<option value="T01007">时时彩</option>
				<option value="T01011">排列五</option>
				<option value="T01010">江西11选5</option>
				<option value="T01012">十一运夺金</option>
				<option value="T01013">22选5</option>
				<option value="T01014">广东11选5</option>
				<option value="T01015">广东快乐十分</option>
			<%}else if (lotno.trim().equals("T01007")) { %>
			<option value="T01007">时时彩</option>
			<option value="T01009">七星彩</option>
			<option value="T01001">大乐透</option>
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01009">排列三</option>
			<option value="T01011">排列五</option>
			<option value="T01010">江西11选5</option>
			<option value="T01012">十一运夺金</option>
			<option value="T01013">22选5</option>
			<option value="T01014">广东11选5</option>
			<option value="T01015">广东快乐十分</option>
			<%} else if (lotno.trim().equals("T01009")) { %>
			<option value="T01009">七星彩</option>
			<option value="T01001">大乐透</option>
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01002">排列三</option>
			<option value="T01007">时时彩</option>
			<option value="T01011">排列五</option>
			<option value="T01010">江西11选5</option>
			<option value="T01012">十一运夺金</option>
			<option value="T01013">22选5</option>
			<option value="T01014">广东11选5</option>
			<option value="T01015">广东快乐十分</option>
			<%}else if (lotno.trim().equals("T01011")) { %>
			<option value="T01011">排列五</option>
			<option value="T01001">大乐透</option>
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01002">排列三</option>
			<option value="T01007">时时彩</option>
			<option value="T01009">七星彩</option>
			<option value="T01010">江西11选5</option>
			<option value="T01012">十一运夺金</option>
			<option value="T01013">22选5</option>
			<option value="T01014">广东11选5</option>
			<option value="T01015">广东快乐十分</option>
			<%}else if (lotno.trim().equals("T01010")) { %>
			<option value="T01010">江西11选5</option>
			<option value="T01001">大乐透</option>
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01002">排列三</option>
			<option value="T01007">时时彩</option>
			<option value="T01009">七星彩</option>
			<option value="T01011">排列五</option>
			<option value="T01012">十一运夺金</option>
			<option value="T01013">22选5</option>
			<option value="T01014">广东11选5</option>
			<option value="T01015">广东快乐十分</option>
			<%}else if (lotno.trim().equals("T01012")) { %>
			<option value="T01012">十一运夺金</option>
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01002">排列三</option>
			<option value="T01007">时时彩</option>
			<option value="T01009">七星彩</option>
			<option value="T01011">排列五</option>
			<option value="T01001">大乐透</option>
			<option value="T01010">江西11选5</option>
			<option value="T01013">22选5</option>
			<option value="T01014">广东11选5</option>
			<option value="T01015">广东快乐十分</option>
			<%}else if (lotno.trim().equals("T01013")) { %>
			<option value="T01013">22选5</option>
			
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01002">排列三</option>
			<option value="T01007">时时彩</option>
			<option value="T01009">七星彩</option>
			<option value="T01011">排列五</option>
			<option value="T01001">大乐透</option>
			<option value="T01012">十一运夺金</option>
			<option value="T01010">江西11选5</option>
			<option value="T01014">广东11选5</option>
			<option value="T01015">广东快乐十分</option>
			<%}else if (lotno.trim().equals("T01014")) { %>
			<option value="T01014">广东11选5</option>
			<option value="T01010">江西11选5</option>
			<option value="T01001">大乐透</option>
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01002">排列三</option>
			<option value="T01007">时时彩</option>
			<option value="T01009">七星彩</option>
			<option value="T01011">排列五</option>
			<option value="T01012">十一运夺金</option>
			<option value="T01013">22选5</option>
			<option value="T01015">广东快乐十分</option>

			<%}else if (lotno.trim().equals("T01015")) { %>
			<option value="T01015">广东快乐十分</option>
			<option value="T01014">广东11选5</option>
			<option value="T01010">江西11选5</option>
			<option value="T01001">大乐透</option>
			<option value="all">全部</option>
			<option value="F47104">双色球</option>
			<option value="F47103">福彩3D</option>
			<option value="F47102">七乐彩</option>
			<option value="T01002">排列三</option>
			<option value="T01007">时时彩</option>
			<option value="T01009">七星彩</option>
			<option value="T01011">排列五</option>
			<option value="T01012">十一运夺金</option>
			<option value="T01013">22选5</option>

			<%} else { %>
				<option value="all">全部</option>
				<option value="T01001">大乐透</option>
				<option value="F47104">双色球</option>
				<option value="F47103">福彩3D</option>
				<option value="F47102">七乐彩</option>
				<option value="T01002">排列三</option>
				<option value="T01009">七星彩</option>
				<option value="T01007">时时彩</option>
				<option value="T01011">排列五</option>
			<option value="T01010">江西11选5</option>
				<option value="T01012">十一运夺金</option>
				<option value="T01013">22选5</option>
				<option value="T01014">广东11选5</option>
				<option value="T01015">广东快乐十分</option>
			<%} %>
			
		</select><br/>
		<input type="submit" value="查询" />
		</form>
		<br />
		<a href="<%=response.encodeURL(path + "/wap/userinfo/userCenter.jspx"
								)%>">返回上一页</a>
</body>
