<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.math.BigDecimal" %>
<%@ page import="com.ruyicai.wap.util.*,net.sf.json.JSONArray,net.sf.json.JSONObject" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	String userName = (String)request.getSession().getAttribute("userName");
	ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");//读取配置文件开关
	String HM3DSwitch = rbint.getString("HM3DSwitch");			//取得3D合买 开关
	String HMQlcSwitch = rbint.getString("HMQlcSwitch");			//取得七乐彩合买 开关
	String deadline = CommonUtil.getDeadlineForHM("F47104", 0);
    if (deadline==null) deadline = "";
	String deadline3D="",deadlineQlc="";
	if(HM3DSwitch.equals("1")){
		deadline3D = CommonUtil.getDeadlineForHM("F47103", 0);
    	if (deadline3D==null) deadline3D = "";
	}
	if(HMQlcSwitch.equals("1")){
		deadlineQlc = CommonUtil.getDeadlineForHM("F47102", 0);
    	if (deadlineQlc==null) deadlineQlc = "";
	}
%>
<title>彩票合买</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-彩票合买<br/>		
		【双色球合买】<br/>
		<%=deadline %><br/>
		<a href="<%=response.encodeURL(path+"/orderhm/getHMList.jspx?lotNo=F47104")%>">参与合买</a>  <a href="<%=response.encodeURL(path+"/wap/DoubleBall/issuehm/index.jspx")%>">发起合买</a>  <a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">自购</a><br/>
		热门合买  <a href="<%=response.encodeURL(path+"/orderhm/getHMList.jspx?lotNo=F47104")%>">更多合买</a><br/>
		发起人  金额+进度<br/>
	<%	
	String term = CommonUtil.getTerm("F47104");
Map ssqmap = new HashMap();
	ssqmap.put("startNum", "0");
	ssqmap.put("maxresult", "5");
	ssqmap.put("orderBy", "progress");
	ssqmap.put("orderDir", "");
	ssqmap.put("lotno", "F47104");
	ssqmap.put("batchcode", term);
String ssqstr= SelectAllUtil.queryCaseOrder(ssqmap);
JSONObject ssqObject = JSONObject.fromObject(ssqstr);
JSONArray ssqHmList = ssqObject.getJSONArray("result");
 	if (ssqHmList != null && ssqHmList.size() > 0) {
 			for (int i = 0; i < ssqHmList.size() && i < 5; i++) {
 				JSONObject json = ssqHmList.getJSONObject(i);
 				int buyAmt = json.containsKey("buyAmt")!=true?0:Integer.parseInt(json.getString("buyAmt"))/100;
 				int totalAmt = json.containsKey("totalAmt")!=true?0:json.getInt("totalAmt")/100;
 				int baodiAmt = json.containsKey("safeAmt")!=true?0:json.getInt("safeAmt")/100;
				BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
			    BigDecimal caseBaodiAmt = new BigDecimal(baodiAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
 %>  [<a
	href="<%=response.encodeURL(path + "/orderhm/hmDetail.jspa?case_caseId="
									+ json.getString("caseLotId"))%>"><%=json.getString("starter")%></a>]<a
	href="<%=response.encodeURL(path + "/orderhm/hmDetail.jspa?case_caseId="
									+ json.getString("caseLotId"))%>">￥<%=totalAmt%>+<%=caseBuyAfterAmt.intValue()%>%+<%=caseBaodiAmt.intValue() %>%(保)</a><br />

<%
	}
		} else {
			out.print("暂无记录！<br/>");
		}
 	%>
        <% if(HM3DSwitch.equals("1")){ %>
        【3D合买】<br/>
		<%=deadline3D %><br/>
		<a href="<%=response.encodeURL(path+"/orderhm/getHMList.jspx?lotNo=F47103")%>">参与合买</a>  <a href="<%=response.encodeURL(path+"/wap/3D/issuehm3D/singleSelectSingleMultipleHm.jspx")%>">发起合买</a>  <a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">自购</a><br/>
		热门合买  <a href="<%=response.encodeURL(path+"/orderhm/getHMList.jspx?lotNo=F47103")%>">更多合买</a><br/>
		发起人  金额+进度<br/>
		<%	
Map d3map = new HashMap();
		d3map.put("startNum", "0");
		d3map.put("maxresult", "5");
		d3map.put("orderBy", "progress");
		d3map.put("orderDir", "");
		d3map.put("lotno", "F47103");
		d3map.put("batchcode", CommonUtil.getTerm("F47103"));
String d3str= SelectAllUtil.queryCaseOrder(d3map);
JSONObject d3Object = JSONObject.fromObject(d3str);
JSONArray D3HmList = d3Object.getJSONArray("result");
 	if (D3HmList != null && D3HmList.size() > 0) {
 			for (int i = 0; i < D3HmList.size() && i < 5; i++) {
 				JSONObject json = D3HmList.getJSONObject(i);
 				int buyAmt = json.containsKey("buyAmt")!=true?0:Integer.parseInt(json.getString("buyAmt"))/100;
 				int totalAmt = json.containsKey("totalAmt")!=true?0:json.getInt("totalAmt")/100;
 				int baodiAmt = json.containsKey("safeAmt")!=true?0:json.getInt("safeAmt")/100;
				BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
			    BigDecimal caseBaodiAmt = new BigDecimal(baodiAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
 %>  [<a
	href="<%=response.encodeURL(path + "/orderhm/hmDetail.jspa?case_caseId="
									+ json.getString("caseLotId"))%>"><%=json.getString("starter")%></a>]<a
	href="<%=response.encodeURL(path + "/orderhm/hmDetail.jspa?case_caseId="
									+ json.getString("caseLotId"))%>">￥<%=totalAmt%>+<%=caseBuyAfterAmt.intValue()%>%+<%=caseBaodiAmt.intValue() %>%(保)</a><br />

<%
	}
		} else {
			out.print("暂无记录！<br/>");
		}
 	}%>
         <% if(HMQlcSwitch.equals("1")){ %>
        【七乐彩合买】<br/>
		<%=deadlineQlc %><br/>
		<a href="<%=response.encodeURL(path+"/orderhm/getHMList.jspx?lotNo=F47102")%>">参与合买</a>  <a href="<%=response.encodeURL(path+"/wap/qilecai/issuehmQlc/QlcDirectSingle.jspx")%>">发起合买</a>  <a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">自购</a><br/>
		热门合买  <a href="<%=response.encodeURL(path+"/orderhm/getHMList.jspx?lotNo=F47102")%>">更多合买</a><br/>
		发起人  金额+进度<br/>
		<%	
Map qlcmap = new HashMap();
		qlcmap.put("startNum", "0");
		qlcmap.put("maxresult", "5");
		qlcmap.put("orderBy", "progress");
		qlcmap.put("orderDir", "");
		qlcmap.put("lotno", "F47102");
		qlcmap.put("batchcode", CommonUtil.getTerm("F47102"));
String qlcstr= SelectAllUtil.queryCaseOrder(qlcmap);
JSONObject qlcObject = JSONObject.fromObject(qlcstr);
JSONArray QlcHmList = qlcObject.getJSONArray("result");
 	if (QlcHmList != null && QlcHmList.size() > 0) {
 			for (int i = 0; i < QlcHmList.size() && i < 5; i++) {
 				JSONObject json = QlcHmList.getJSONObject(i);
 				int buyAmt = json.containsKey("buyAmt")!=true?0:Integer.parseInt(json.getString("buyAmt"))/100;
 				int totalAmt = json.containsKey("totalAmt")!=true?0:json.getInt("totalAmt")/100;
 				int baodiAmt = json.containsKey("safeAmt")!=true?0:json.getInt("safeAmt")/100;
				BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
			    BigDecimal caseBaodiAmt = new BigDecimal(baodiAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
 %>  [<a
	href="<%=response.encodeURL(path + "/orderhm/hmDetail.jspa?case_caseId="
									+ json.getString("caseLotId"))%>"><%=json.getString("starter")%></a>]<a
	href="<%=response.encodeURL(path + "/orderhm/hmDetail.jspa?case_caseId="
									+ json.getString("caseLotId"))%>">￥<%=totalAmt%>+<%=caseBuyAfterAmt.intValue()%>%+<%=caseBaodiAmt.intValue() %>%(保)</a><br />

<%
	}
		} else {
			out.print("暂无记录！<br/>");
		}
 	}%>
        <br/>
        <% if(userName!=null&&!userName.equals("")){ %>
        <a href="<%=response.encodeURL(path+"/orderhm/getHMListByUserno.jspa")%>">合买查询</a><br/>
        <br/><%} %>
<a href="<%=response.encodeURL(path+"/wap/wapindex.jspx")%>">返回上一页</a>
</body>
