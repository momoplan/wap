<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.*,net.sf.json.JSONArray,net.sf.json.JSONObject" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	JSONObject object = request.getAttribute("jsonObject")==null ? new JSONObject():(JSONObject)request.getAttribute("jsonObject");
	String state = ""; //状态
	String flowNo = ""; //流水号
	String orderTime = ""; //定制时间
	String amount = ""; //单期追号金额
	String batchNum = ""; //彩票购买期数
	String lastNum = ""; //剩余购买期数
	String betcode = ""; //注码
	String lotNo = ""; //彩种编号
%>
<body>
<title>追号查询</title>	
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
		<%out.print(CommonUtil.printChar());%>追号查询<br/>
		
		<%
		if (object!=null ) {
			JSONObject jsonObject = (JSONObject)object.get("value");
			JSONObject jsonObject1 = (JSONObject)jsonObject.get("ttransaction");
			if (jsonObject.has("state")) {
				state = jsonObject.getString("state"); 
			}
			if (jsonObject.has("flowno")) {
				flowNo = jsonObject.getString("flowno"); 
			}
			if (jsonObject.has("ordertime")) {
				orderTime = jsonObject.getString("ordertime"); 
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				orderTime= sdf.format(Long.valueOf(orderTime));
			}
			if (jsonObject.has("amount")) {
				amount = jsonObject.getString("amount"); 
			}
			if (jsonObject.has("batchnum")) {
				batchNum = jsonObject.getString("batchnum"); 
			}
			if (jsonObject.has("lastnum")) {
				lastNum = jsonObject.getString("lastnum"); 
			}
			if (jsonObject.has("betcode")) {
				betcode = jsonObject.getString("betcode"); 
			}
			if (jsonObject.has("lotno")) {
				lotNo = jsonObject.getString("lotno"); 
			}
			int batchNumInt = Integer.parseInt(batchNum);
			int amountInt = Integer.parseInt(amount)/100;
			int lastNumInt = Integer.parseInt(lastNum);
			
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("betcode", betcode);
			jsonObject2.put("play_name", lotNo);
			Map map = CommonUtil.getBetCodeContentToModel(jsonObject2);
			
			out.print("上次投注时间:"+orderTime+"<br/>");
			out.print("总金额:"+(batchNumInt*amountInt)+"元<br/>");
			out.print("总期数:"+batchNum+"期<br/>");
			out.print("成功金额:"+(batchNumInt-lastNumInt)*amountInt+"元<br/>");
			out.print("成功期数:"+(batchNumInt-lastNumInt)+"期<br/>");
			out.print("投注方式:"+(String) map.get("bet_code_type")+"<br/>");
			out.print("投注内容:"+"<br/>");
			
			if (((String) map.get("doubleBallType")).equals("S")|| ((String) map.get("doubleBallType"))	.equals("0")) {
				out.println("注码:<br/>");
				for (int j = 0; map.get("bet_code" + j) != null; j++) {
					out.println(map.get("bet_code" + j)	+ "<br/>");
				}
			} else if (((String) map.get("doubleBallType")).equals("QT")) {
				out.println("胆码:<br/>"	+map.get("bet_codeD")+"<br/>");
				out.println("拖码:<br/>"	+map.get("bet_codeT")+"<br/>");
			} else if (((String) map.get("doubleBallType")).equals("QT")) {
				out.println("胆码:<br/>"	+map.get("bet_codeD")+"<br/>");
				out.println("拖码:<br/>"	+map.get("bet_codeT")+"<br/>");
			} else if (((String) map.get("doubleBallType")).equals("WX")) {
				out.println("百位:"+map.get("bai")+"<br/>");
				out.println("十位:"+map.get("shi")+"<br/>");
				out.println("个位:"+map.get("ge")+"<br/>");
			} else if(((String) map.get("doubleBallType")).equals("SSC")) {
				out.println("注码:<br/>"+map.get("bet_code")+"<br/>");
			} else if(((String) map.get("doubleBallType")).equals("DF")) {
				if(map.get("bet_code") != null){
					out.println("注码:<br/>"+map.get("bet_code")	+ "<br/>");
				}else{
					for (int j = 0; map.get("bet_code" + j) != null; j++) {
						out.println(map.get("bet_code" + j)	+ "<br/>");
					}
				}
			}
			
			else {
				out.println("注码:<br/>"+map.get("bet_code")+"<br/>");
			}
			
			if (state.trim().equals("0") && lastNumInt>0) {
				out.print("<br/><a href=\"" + path + "/wap/query/stopAddNumberConfirm.jspx"+"?flowNo="+flowNo+"\">停止追号</a><br/>");
			}
		} else {
			out.print("没有找到追号记录...");
		}
		%><br/>
</body>
