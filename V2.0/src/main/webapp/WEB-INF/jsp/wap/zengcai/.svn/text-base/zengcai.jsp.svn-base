<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String zhushu = request.getAttribute("zhushu")==null?"":(String)request.getAttribute("zhushu");
    String beishu = request.getAttribute("beishu")==null?"":(String)request.getAttribute("beishu");
    String amt = request.getAttribute("amt")==null?"":(String)request.getAttribute("amt");
    String newbetcode = request.getAttribute("newbetcode")==null?"":(String)request.getAttribute("newbetcode");
    String lotno = request.getAttribute("lotno")==null?"":(String)request.getAttribute("lotno");
    String giftmessages = request.getAttribute("giftmessages")==null?"":(String)request.getAttribute("giftmessages");
    String mobiles = request.getAttribute("mobiles")==null?"":(String)request.getAttribute("mobiles");
    String message = (String)request.getAttribute("message"); 
    String oneMoney = request.getAttribute("oneMoney")==null?"2":(String)request.getAttribute("oneMoney");%>
<h4><a href="/w/wap/wapindex.jspx">首页</a>-<a
	href="/w/wap/buyLottery.jspx">购彩大厅</a>-赠送彩票-传递祝福</h4>
<form action="<%=request.getContextPath() %>/orderhm/buyzengsong.jspa" method="post">
<div class="message">
<c:if test="${message != null}">
  <span style="color: red;"> <%= message%></span> <br/>
</c:if>
</div>
<div class="content">
您的赠言：<br/>
<textarea name="giftmessages" rows="3" cols="26" maxlength="20"><%=giftmessages %></textarea><br/>
朋友联系方式：<br/>(填写电话号码,多个联系人之间用逗号分隔)<br/>
<textarea name="mobiles" rows="3" cols="26" maxlength=""><%=mobiles %></textarea><br/>
赠送注数：<%=zhushu %>注<br/>
赠送倍数：<%=beishu %>倍<br/>
赠送金额：<%=amt %>元<br/>
</div>
<br/>
<input type="hidden" name="zhushu" value="<%=zhushu%>"/>
<input type="hidden" name="token" value="${token }"/>
<input type="hidden" name="betcode" value="<%=newbetcode%>"/>
<input type="hidden" name="beishu" value="<%=beishu%>"/>
<input type="hidden" name="amt" value="<%=amt%>"/>
<input type="hidden" name="oneMoney" value="<%=oneMoney%>"/>
<input type="hidden" name="lotno" value="<%=lotno%>"/>
<input type="submit" value="确认赠送"/>
</form>