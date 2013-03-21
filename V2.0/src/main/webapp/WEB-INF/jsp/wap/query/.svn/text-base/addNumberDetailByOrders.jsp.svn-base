<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*"%>
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
	int allAmount = 0;//总金额
	int addAmount = 0;//已追金额
	int allNum = 0;//总期数
	int addNum = 0;//已追期数
	String orderstate = "";
	
%>
<body>
<title>追号查询</title>	
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>
		<%out.print(CommonUtil.printChar());%>追号查询<br/>
		
		<%
		if (object!=null ) {
			JSONObject jsonObject = (JSONObject)object.get("value");
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			if(jsonArray!=null && jsonArray.size()>1){
				JSONObject jsonObject1 = null;
				JSONObject jsonObject2 = null;
				if(jsonArray!=null&&jsonArray.size()>0){
					for(int i = 0 ;i <jsonArray.size();i++){
						jsonObject1 = (JSONObject)jsonArray.get(i);
						jsonObject2 = jsonObject1.getJSONObject("torder");
						orderstate = jsonObject2.getString("orderstate");
						
						if("1".equals(orderstate)){
							orderTime = jsonObject2.getString("modifytime");
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
							orderTime= sdf.format(Long.valueOf(orderTime));
							addAmount += Integer.parseInt(jsonObject2.getString("orderamt"));
							addNum++;
						}
						allAmount += Integer.parseInt(jsonObject2.getString("orderamt"));
						allNum++;
					}
					betcode = jsonObject2.getString("orderinfo");
					flowNo = jsonObject2.getString("tsubscribeflowno");
					lotNo = jsonObject2.getString("lotno");	
				}
				JSONObject jsonObject3 = new JSONObject();
				jsonObject3.put("orderinfo", betcode);
				jsonObject3.put("play_name", lotNo);
				Map map = SelectAllUtil.getBetcodeFormat(jsonObject2);
				
				out.print("上次投注时间:"+orderTime+"<br/>");
				out.print("总金额:"+(allAmount/100)+"元<br/>");
				out.print("总期数:"+allNum+"期<br/>");
				out.print("成功金额:"+(addAmount/100)+"元<br/>");
				out.print("成功期数:"+addNum+"期<br/>");
				out.print("投注方式:"+(String) map.get("betType")+"<br/>");
				out.print("投注内容:"+"<br/>");
				out.println(""+map.get("betCodeView"));
				if (orderstate.trim().equals("0") && allNum-addNum>0) {
					out.print("<br/><a href=\"" + path + "/wap/query/stopAddNumberConfirm.jspx"+"?flowNo="+flowNo+"\">停止追号</a><br/>");
				}
			}else{
				out.print("追号信息已过期...");
			}
			
		} else {
			out.print("没有找到追号记录...");
		}
		%><br/>
</body>
