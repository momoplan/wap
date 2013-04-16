<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.LotteryAlgorithmUtil"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil,net.sf.json.JSONArray,net.sf.json.JSONObject"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	JSONArray jsonDetails = request.getAttribute("JsonArray") == null ? new JSONArray()
			: (JSONArray) request.getAttribute("JsonArray");
	JSONObject jsonObject = request.getAttribute("jsonObject") == null ? new JSONObject()
	: (JSONObject) request.getAttribute("jsonObject");
	String errorCode = jsonObject.getString("errorCode");
	JSONObject jsonObject2 = null;
	JSONArray jsonArray = null;
	if("400003".equals(errorCode)){
		jsonObject2 = null;
	}else{
		jsonObject2 = jsonObject.getJSONObject("value")== null ? new JSONObject()
		: jsonObject.getJSONObject("value");
		jsonArray = jsonObject2.getJSONArray("list");
	}
	
	String begintime = request.getParameter("begintime"); //开始时间
	String endtime = request.getParameter("endtime"); //结束时间
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	JSONObject jsons = null;
	String nowpage = request.getAttribute("nowpage") == null ? "1"
			: (String) request.getAttribute("nowpage");
	int pageSize = 15;
	int maxline = pageSize;
	if (jsonObject2 != null ) {

		maxline = jsonObject2.containsKey("totalResult") != true ? pageSize : jsonObject2
				.getInt("totalResult");
	}

	int pageCount = maxline % pageSize > 0 ? maxline / pageSize + 1
			: maxline / pageSize;
	int nowPages = Integer.parseInt(nowpage);
%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
	<title>账户明细</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a><%
			out.print(CommonUtil.printChar());
		%>账户明细<br/>
		<%
			if(jsonObject2!=null)
			{
				JSONObject dsonDetail=null;
				String newAmt ="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(int i=0;i<jsonArray.size()&&i<pageSize;i++){
					dsonDetail= (JSONObject)jsonArray.get(i);
					int blsign = dsonDetail.getInt("blsign");
					float balance=Long.valueOf(""+dsonDetail.get("balance"));
					//float drawamtBalance=Long.valueOf(""+dsonDetail.get("drawamtBalance"));
					//float drawamt=Long.valueOf(""+dsonDetail.get("drawamt"));
					float amt=Long.valueOf(""+dsonDetail.get("amt"));
					String memo=dsonDetail.get("memo").toString();
					if(!"".equals(memo) && memo != null){
						if(memo.indexOf("gyj001")!=-1){
							memo = memo.substring(6);
						}
					}
					
					String memo2=java.net.URLDecoder.decode(memo,"UTF-8");
					if(amt>0){
					if(blsign==-1)
					{
					%>
					账户变动: <%= "-"+(amt/100)  %>　元<br/>
					<%}else{ %>
					账户变动: <%= (amt/100)  %>　元<br/>
					<%} %>
					变动原因: <%= memo  %><br/>
					变动时间: <%= sdf.format(dsonDetail.get("plattime")) %><br/>
					----------------------------<br/>
					<%
					}}
				if(nowPages>0&&nowPages<pageCount-1){
					%>
					<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
						<input type="hidden" name="beginId" value="0" />
						<input type="hidden" name="endId" value="10" />
						<input type="hidden" name="nowPage" value="0" />
						<input type="hidden" name="begintime" value="<%=begintime %>" />
						<input type="hidden" name="endtime" value="<%=endtime %>" />
						<input type="submit" value="首页" />						
					</form>
					
					
					<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
						<input type="hidden" name="beginId" value="<%=pageSize*(nowPages-2)+1 %>" />
						<input type="hidden" name="endId" value="<%=pageSize*(nowPages-1)+1 %>" />
						<input type="hidden" name="nowPage" value="<%=nowPages-1 %>" />
						<input type="hidden" name="begintime" value="<%=begintime %>" />
						<input type="hidden" name="endtime" value="<%=endtime %>" />
						<input type="submit" value="上一页" />		
					</form><br/>
					
					<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
						<input type="hidden" name="beginId" value="<%=pageSize*nowPages+1 %>" />
						<input type="hidden" name="endId" value="<%=pageSize*(nowPages+1)+1 %>" />
						<input type="hidden" name="nowPage" value="<%=nowPages+1 %>" />
						<input type="hidden" name="begintime" value="<%=begintime %>" />
						<input type="hidden" name="endtime" value="<%=endtime %>" />
						<input type="submit" value="下一页" />	
					</form>
				
					<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
						<input type="hidden" name="beginId" value="<%=(pageCount-1)*pageSize %>" />
						<input type="hidden" name="endId" value="<%=pageSize*pageCount+1 %>" />
						<input type="hidden" name="nowPage" value="<%=pageCount-1 %>" />
						<input type="hidden" name="begintime" value="<%=begintime %>" />
						<input type="hidden" name="endtime" value="<%=endtime %>" />
						<input type="submit" value="尾页" />	
					</form>
				<%}else if(nowPages==0&&pageCount>1){ %>	
				<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
					<input type="hidden" name="beginId" value="<%=pageSize*nowPages+1 %>" />
					<input type="hidden" name="endId" value="<%=pageSize*(nowPages+1)+1 %>" />
					<input type="hidden" name="nowPage" value="<%=nowPages+1 %>" />
					<input type="hidden" name="begintime" value="<%=begintime %>" />
					<input type="hidden" name="endtime" value="<%=endtime %>" />
					<input type="submit" value="下一页" />	
				</form>
				<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
					<input type="hidden" name="beginId" value="<%=(pageCount-1)*pageSize+1 %>" />
					<input type="hidden" name="endId" value="<%=pageSize*pageCount %>" />
					<input type="hidden" name="nowPage" value="<%=pageCount-1 %>" />
					<input type="hidden" name="begintime" value="<%=begintime %>" />
					<input type="hidden" name="endtime" value="<%=endtime %>" />
					<input type="submit" value="尾页" />					
				</form>
			<%}else if(nowPages==pageCount-1&&nowPages>0){
				%>
				<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
					<input type="hidden" name="beginId" value="0" />
					<input type="hidden" name="endId" value="10" />
					<input type="hidden" name="nowPage" value="0" />
					<input type="hidden" name="begintime" value="<%=begintime %>" />
					<input type="hidden" name="endtime" value="<%=endtime %>" />
					<input type="submit" value="首页" />		
				</form>
				
				<form action="/w/touzhu/accountDetailSelect.jspa" method="post" style="margin:0px;display:inline;">
					<input type="hidden" name="beginId" value="<%=pageSize*(nowPages-2)+1 %>" />
					<input type="hidden" name="endId" value="<%=pageSize*(nowPages-1)+1 %>" />
					<input type="hidden" name="nowPage" value="<%=nowPages-1 %>" />
					<input type="hidden" name="begintime" value="<%=begintime %>" />
					<input type="hidden" name="endtime" value="<%=endtime %>" />
					<input type="submit" value="上一页" />	
				</form>
				<%
			}
			} else{
				%>
					未查询到记录!
				<%
			}
		%>
</body>
