<%@page import="com.ruyicai.wap.bean.*"%>
<%@page import="java.util.List"%>
<%@page import="com.ruyicai.wap.Global"%>
<%@page import="com.ruyicai.wap.util.*"%>
<%@page import="java.util.*"%>
<%@ page pageEncoding="UTF-8"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	TuserInfoBean userInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
	String userno = "";
	if(userInfoBean !=null){
		userno = userInfoBean.getUserno();
	}
	String error_code = (String)request.getAttribute("err_msg");
	if("投注申请已被受理"==error_code||"投注申请已被受理".equals(error_code))
	{
		request.setAttribute("err_msg","投注申请已被受理");
	}
	
%>

<title>投注信息</title>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/buyLottery.jspx">购彩大厅</a>-投注信息<br />
<%
			String err_msg = (String)request.getAttribute("err_msg");
		String err_certid = (String)request.getAttribute("err_certid");
		%> <% if(err_msg!=null){
			%> <% out.print(CommonUtil.printWarningMessage(err_msg));%> <br /><% }
			%>
<% if(err_certid!=null){
			%> <% out.print(CommonUtil.printWarningMessage(err_certid));%><br /> <% }
			%> 
			
<a href="<%=response.encodeURL(path+"/wap/buyLottery.jspx")%>">继续投注</a> <a
	href="<%=response.encodeURL(path + "/wap/query/betingOrdersSelect.jspx"
							)%>">查看记录详细</a><br />
 <% 
 				Map<String,String> map = new HashMap<String,String>();
				if(userno!=null && !"".equals(userno)){
					map = CommonUtil.getBalance(userno);
					String AbleToBet = map.get("AbleToBet");
					String nofreebalance = map.get("FreezeBalanceFloat");
					if(Float.valueOf(nofreebalance)>0){
					out.print("冻结金额："+nofreebalance+"元");
					}
					out.print("<br/>可用金额："+AbleToBet);
				}	
			%> 元<br />
马上充值：<br/>
<%
			Channel channel=WapUtil.getChannel(request);
			List<ChargeMode> list=Global.newsDAO.getChargeMode( channel.getChannelId());
		    if(list!=null)	for(ChargeMode cm:list){
				out.println("★ <a href=\"" +response.encodeURL(cm.getVal() )  +"\">"+cm.getName()+"</a><br/>");	
			}
			%>
			
</body>
