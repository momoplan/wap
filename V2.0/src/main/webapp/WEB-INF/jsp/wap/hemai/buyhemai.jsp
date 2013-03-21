<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String zhushu = request.getAttribute("zhushu")==null?"":(String)request.getAttribute("zhushu");
    String beishu = request.getAttribute("beishu")==null?"":(String)request.getAttribute("beishu");
    String amt = request.getAttribute("amt")==null?"":(String)request.getAttribute("amt");
    String newbetcode = request.getAttribute("newbetcode")==null?"":(String)request.getAttribute("newbetcode");
    String lotno = request.getAttribute("lotno")==null?"":(String)request.getAttribute("lotno");
    String subamount = request.getAttribute("subamount")==null?"1":(String)request.getAttribute("subamount");
    String lowestamt = request.getAttribute("lowestamt")==null?"1":(String)request.getAttribute("lowestamt");
    String baodiamt = request.getAttribute("baodiamt")==null?"0":(String)request.getAttribute("baodiamt");
    String describe = request.getAttribute("describe")==null?"":(String)request.getAttribute("describe");
    String oneMoney = request.getAttribute("oneMoney")==null?"2":(String)request.getAttribute("oneMoney");
    String message = (String)request.getAttribute("message"); 
%>
<h4><a href="/w/wap/wapindex.jspx">首页</a>-<a
	href="/w/orderhm/caseLotLottery.jspx">彩票合买</a>-合买信息</h4>
<form action="<%=request.getContextPath()%>/orderhm/buyhemai.jspa" method="post">
<div class="message">
<c:if test="${message != null}">
  <span style="color: red;"> <%= message%></span> <br/>
</c:if>
</div>
<div class="content">
投注金额：<%=amt %>元<br/>
投注注数：<%=zhushu %>注<br/>
投注倍数：<%=beishu %>倍<br/>
我要认购：<input type="text" name="subamount"  value="<%=subamount%>">元<br/>
最低跟单：<input type="text" name="lowestamt"  value="<%=lowestamt%>">元<br/>
我要保底：<input type="text" name="baodiamt"  value="<%=baodiamt %>">元<br/>
全额保底：<input type="radio" name="allbaodi" value="0">是<input type="radio" checked="checked" name="allbaodi" value="1">否<br/>
我的提成：<select name="commission">
            <option value="1">1 %</option> 
            <option value="2">2 %</option> 
            <option value="3">3 %</option> 
            <option value="4">4 %</option> 
            <option value="5">5 %</option> 
            <option value="6">6 %</option>
            <option value="7">7 %</option> 
            <option value="8">8 %</option> 
            <option value="9">9 %</option> 
            <option value="10" selected="selected">10 %</option> 
           </select><br/>
是否公开：<br/>
<input type="radio" name="isPublic" value="0" checked="checked">对所有人公开<br/>
<input type="radio" name="isPublic" value="2">对所有人截止后公开<br/>
<input type="radio" name="isPublic" value="3">对跟单者立即公开<br/>
<input type="radio" name="isPublic" value="4">对跟单者截止后公开<br/>
<input type="radio" name="isPublic" value="1" >保密<br/>
<br/>
方案描述:
<textarea cols="22" rows="3" name="describe"><%=describe %></textarea>
</div>
<br/>
<input type="hidden" name="token" value="${token }"/>
<input type="hidden" name="zhushu" value="<%=zhushu%>"/>
<input type="hidden" name="betcode" value="<%=newbetcode%>"/>
<input type="hidden" name="beishu" value="<%=beishu%>"/>
<input type="hidden" name="amt" value="<%=amt%>"/>
<input type="hidden" name="lotno" value="<%=lotno%>"/>
<input type="hidden" name="oneMoney" value="<%=oneMoney%>"/>
<input type="submit" value="发起合买"/>
</form>