<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%
	String path = CommonUtil.removeTrailingSlash(request.getContextPath());
%>
<title>问题回复</title>
<body>
<%
	String message = (String) request.getAttribute("message");
%>
 <a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jsp">用户中心</a> <%
 	out.print(CommonUtil.printChar());
 %>问题回复<br />
 *尊敬的用户，欢迎您访问彩票频道，下面是针对用户的提问进行的回复，如还有其它疑问可以到<a href="/wap/userinfo/feedBack.jsp">留言页面</a>留言给我们,我们会统一在此页面给大家回复！<br/>
 <br/>
 1.购买彩票时能否直接从话费中扣除费用？<br/>
 	答：本网站暂时不支持话费支付业务，如金额不足，需要点击“充值”选择您方便的充值方式对如意彩账户进行充值，之后每次购彩都会从您充值的金额中扣除。<br/><br/>
 	
 2.中大奖金额直接返还给指定帐户吗？还是需要本人亲自到指定地点领取？<br/>
 	答：单注中奖1万以内的奖金会自动返还到如意彩账户内，1万以上的大奖需要本人携带身份证及手机卡到内蒙福彩中心领取(中大奖后客服会通知您，联系好后，工作人员会陪同您前往兑奖)<br/><br/>
 3.如何购彩？<br/>
 	答：在充值页面按照步骤充值成功之后，可以在购彩页面选择自己想购买的彩种，然后根据提示购买即可。<br/><br/>
 4.如何确认购彩成功？<br/><br/>
 	答：在点击确认投注，看到页面提示：投注受理！之后，可以到用户中心进行查询！<br/>
 5.关于专家荐号<br/><br/>
 	答：用户可以点击彩票频道首页的专家分析，进入专家荐号页面，根据自己需要查看相关信息！<br/>
 6.关于充值<br/><br/>
           答：用户充值(手机卡充值用户务必认真填写卡号和密码，否则会充值失败)，5分钟后查询余额，如果仍然没有充值金额，可以联系客服：4006651000。<br/>
 7.关于修改用户信息<br/><br/>
 	答：用户修改信息，可以到用户中心修改页面进行修改。<br/>
 8.为什么看不到购彩、充值及导航区的入口了？<br/><br/>
 	答：彩票栏目只支持ctwap模式，对于异网和ctnet模式上网的用户只能看到咨询内容，无法进行购彩、充值等交易。您可以在手机设置的“接入点名称”选择“中国电信WAP设置CTWAP”就可以进行购彩等交易了！<br/>
 
<a href="/w/wap/userinfo/feedBack.jspx">返回上一页</a>
</body>
