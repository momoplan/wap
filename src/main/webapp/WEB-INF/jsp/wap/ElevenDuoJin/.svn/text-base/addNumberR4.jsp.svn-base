<%@page import="com.buybal.lot.lottype.AddNumberUtil"%>
<%@page import="com.ruyicai.wap.bean.AddNumberAmtBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ruyicai.wap.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>十一运夺金特别追号</title>
<body>
<%
String deadline = CommonUtil.getDeadline("T01012", 0);
String betcode =(String) request.getAttribute("betcode")==null?"":(String) request.getAttribute("betcode");
String addnumber =(String) request.getAttribute("addnumber")==null?"5":(String) request.getAttribute("addnumber");
String flag = (String)request.getAttribute("flag");
String zhushu = (String)request.getAttribute("zhushu");
String message = (String)request.getAttribute("message");
String count = (String)request.getAttribute("count");
String newaddnumber = (String)request.getSession().getAttribute("newaddnumber");
String newaddnumbernull= (String)request.getAttribute("newaddnumbernull");
String addnumberError = (String)request.getAttribute("addnumberError");
String random = (String)request.getAttribute("random");
List<String> beishuarr = new ArrayList<String>();
List<AddNumberAmtBean> listAdd = null ;
if(addnumber==null || "".equals(addnumber)||"newaddnumbernull".equals(newaddnumbernull)){
	if(random!=null && !"".equals(random)){
		for(int i = 0 ; i< Integer.parseInt(random) ; i++){
			String beishus =(String) request.getAttribute("beishu"+i);
			beishuarr.add(beishus);
		}
		 listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr,zhushu,random,flag);
	}else{
		for(int i = 0 ; i< 5 ; i++){
			String beishus =(String) request.getAttribute("beishu"+i);
			beishuarr.add(beishus);
		}
		 listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr,zhushu,"5",flag);
	}
	}else if("count".equals(count)){
	int add= 0;
	if(random!=null && !"".equals(random)){
		add=Integer.parseInt(random);
	}else if(Integer.parseInt(newaddnumber)>Integer.parseInt(addnumber)){
		add=Integer.parseInt(addnumber);
	}else{
		add=Integer.parseInt(newaddnumber);
	}
	for(int i = 0 ; i< add ; i++){
		String beishus =(String) request.getAttribute("beishu"+i);
		beishuarr.add(beishus);
	}
	listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr, zhushu, add+"",flag);}else if(addnumberError!=null&&!"".equals(addnumberError)){
	for(int i = 0 ; i< Integer.valueOf(random) ; i++){
		String beishus =(String) request.getAttribute("beishu"+i);
		beishuarr.add(beishus);
	}
	listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr, zhushu, random,flag);
}else if("0".equals(addnumber) || MessageUtil.ADDNUMBER_IS_ERROR.equals(message)){
	if(random!=null&&!"".equals(random)){
		for(int i = 0 ; i< Integer.parseInt(random) ; i++){
			String beishus =(String) request.getAttribute("beishu"+i);
			beishuarr.add(beishus);
		}
		listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr, zhushu, random,flag);
	}else{
		for(int i = 0 ; i< 5 ; i++){
			String beishus =(String) request.getAttribute("beishu"+i);
			beishuarr.add(beishus);
		}
		listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr, zhushu, "5",flag);
	}}else{
	for(int i = 0 ; i< Integer.valueOf(addnumber) ; i++){
		String beishus =(String) request.getAttribute("beishu"+i);
		beishuarr.add(beishus);
	}
	listAdd = new AddNumberUtil().getAddAmtByParam(beishuarr, zhushu, addnumber,flag);
}


%>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="/wap/buyLottery.jspx">
<%
	out.print(CommonUtil.printHall());
%> </a> 
<%
 	out.print(CommonUtil.printChar());
%> 
 十一运夺金<br />
<a href="/wap/ElevenDuoJin/optionOne.jspx">任选</a>|<a 
		   href="/wap/ElevenDuoJin/ForwardTwoZX.jspx">直选</a>|<a 
		   href= "/wap/ElevenDuoJin/ForwardTwoGroup.jspx">组选</a>|<a 
		   href="/wap/ElevenDuoJin/dantuoR2.jspx">胆拖</a>|特别追号<br/>
<a href="/w/add/toAddNumberBet.jspx?flag=rx1">任一</a>|<a 
   href="/w/add/toAddNumberBet.jspx?flag=rx2">任二</a>|<a 
   href="/w/add/toAddNumberBet.jspx?flag=rx3">任三</a>|任四|<a 
   href="/w/add/toAddNumberBet.jspx?flag=rx5">任五</a>|<a 
   href="/w/add/toAddNumberBet.jspx?flag=rx6">任六</a>|<a 
   href="/w/add/toAddNumberBet.jspx?flag=rx7">任七</a>|<a 
   href="/w/add/toAddNumberBet.jspx?flag=rx8">任八</a> <br/>
 <% 	if (deadline != null) { %> 
 <% 	out.print(deadline); %> <br/> <%}%>

从1~11选择,注码个数≥4，号码间无分隔符,10之前号码前面补0,如1要输入01<br/>
	 <%if(message != null){ %>
		<span style="color: red;">   提示： <%out.println(message); %><br/></span>
	 <%} %>
<form action="/add/ToActionByAddNUmber.jspx" method="post">
注码：<input name="betcode" type="text"  size="20"  value="<%=betcode %>" /><br /> 
追号：<input name="addnumber" type="text"  size="2" maxlength="2" value="<%=addnumber %>" /> (最高20期)
<input type="hidden" name="flag"  value="<%=flag %>"><br/>
<input type="hidden" name="random" value="<%=beishuarr.size()%>"/>
<input type="submit" name="plan"  value="生成追号计划">
(<a href="/wap/ElevenDuoJin/addnumberIntroduction.jspx">玩法介绍</a>)<br/>
<%for(int i = 0 ; i<listAdd.size() ; i++){ %>
<%= i+1%>-<input type="text" name="<%="beishu"+i%>" size="2" maxlength="2" value="<%=String.valueOf(listAdd.get(i).getBeishu()) %>">倍
 ,投<%= String.valueOf( listAdd.get(i).getBetAmt()) %>
     累<%= String.valueOf(listAdd.get(i).getAddAmt())  %>
     赚<%= String.valueOf( listAdd.get(i).getProfAmt())%>
<br/>
<%} %>
<div>[中奖即停止追号]</div>
<input type="submit" name="bet" value="确认投注 ">
<input type="submit" name="count" value="计算赔赚 ">
 </form><br/>
任选四：与开奖号码中的任四个一致即中78元<br/>
<%
    List<String> list = CommonUtil.getBetCode("T01012","5");
 %> 【最新开奖】<br /> <%
 	String code = "";
 	for (int i = 0; i < list.size(); i++) {
 		code = list.get(i);
 %>
 <%
 	out.print(code);
 %> <br /> <%
 	}
 %>
</body>
