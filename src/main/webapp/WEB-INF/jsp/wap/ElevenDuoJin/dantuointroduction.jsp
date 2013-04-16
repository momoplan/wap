<%@page import="java.util.List"%>
<%@page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>十一运夺金玩法介绍</title>
<%
String path = CommonUtil.removeTrailingSlash(request
		.getContextPath());
%>
</head>
<body>
<div>
<a href="/wap/wapindex.jspx">首页</a>-<a
	href="<%=path %>/wap/buyLottery.jspx">
<%
	out.print(CommonUtil.printHall());
%> </a> 
<%
 	out.print(CommonUtil.printChar());
 %>
<a href="/wap/ElevenDuoJin/index.jspx"> 十一运夺金</a> <%
 	out.print(CommonUtil.printChar());
 %>胆拖玩法
<br />
</div>
<div>
特点：节省资金.收益稳定.确保命中<br/>
原因：1、使用胆拖投注功能可更有效利用资金,增加原始号码的覆盖面,最大化的命中奖号!<br/>
2、以任选五为例,全包11个号码投注任选五,投入资金为924元,与540元奖金相比肯定是无法盈利。利用胆拖功能就大不一样了,设定1个胆码,然后将剩余10个号码作为拖码,那么投入资金将减少为420元,与选择11个号码全包的覆盖率相同,但在投入资金上节省了55%！可见,考虑减少投入保证覆盖范围是最为稳健的投资收益操作.如上案例中,只要胆码正确就能盈利120元!<br/>
操作步骤<br/>
1按要求填写胆码<br/>
2按要求填写拖码<br/>
3填写倍数和追期并确认<br/>
4我们将为您生成胆拖组合，即胆码不变，拖码变换<br/><br/>
赶快体验吧！<br/>
胆拖玩法<a href="/wap/ElevenDuoJin/dantuointroduction.jspx">[胆拖必读]</a><br/>
<a href="/wap/ElevenDuoJin/dantuoR2.jspx">二</a> 
<a href="/wap/ElevenDuoJin/dantuoR3.jspx">三</a> 
<a href="/wap/ElevenDuoJin/dantuoR4.jspx">四</a> 
<a href="/wap/ElevenDuoJin/dantuoR5.jspx">五</a> 
<a href="/wap/ElevenDuoJin/dantuoR6.jspx">六</a> 
<a href="/wap/ElevenDuoJin/dantuoR7.jspx">七</a> <br/>
<a href="/wap/ElevenDuoJin/dantuoGroup2.jspx">前二组选</a> <a href="/wap/ElevenDuoJin/dantuoGroup3.jspx">前三组选</a><br/>
</div>
<a href="/wap/ElevenDuoJin/index.jspx">返回上一页</a>
   <%
	 List<String> list = CommonUtil.getBetCode("T01012","5");
 %> 【最新开奖】<br /> <%
 	String code = "";
 	for (int i = 0; i < list.size(); i++) {
 		code = list.get(i);
 %> <%
 	out.print(code);
 %> <br /> <%
 	}
 %>
</body>
</html>