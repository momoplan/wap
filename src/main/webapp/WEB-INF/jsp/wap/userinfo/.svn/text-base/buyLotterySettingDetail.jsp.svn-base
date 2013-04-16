<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>用户中心-设置</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-设置<br />
<!--<form action="/w/userWap/cancelUserSetting.jspa" method="post">
	 <label >
		<c:if test="${sendtype2=='1' }">
		<input type="checkbox" name="needToSend" value="" onclick="return false;" checked/>ISO消息<br/>
		<input type="hidden" name="id" value="${id2 }"/>
		(已定制)<br/>
		<input type="submit" value="取消设置"/><br/>
		</c:if>
		<c:if test="${sendtype2=='0' }">
		<input type="checkbox" name="needToSend" value="" onclick="return false;"/>ISO消息<br/>
		(未定制)<br/>
		</c:if>
	</label>
</form> -->
	<label >
		<c:if test="${sendtype0=='1' }">
		<form action="/w/userWap/cancelUserSetting.jspa" method="post">
		<input type="checkbox" name="needToSend" value="" onclick="return false;" checked/>短信提醒<br/>
		(已定制)<br/>
		<input type="hidden" name="id" value="${id0 }"/>
		<input type="submit" value="取消设置"/><br/>
		</form>
		</c:if>
		<c:if test="${sendtype0=='0' }">
		<form action="/w/userWap/updateUserSetting.jspa" method="post">
		<input type="checkbox" name="needToSend" value="" onclick="return false;"/>短信提醒<br/>
		(未定制，定制后你可以通过短信收到购买彩种当前的开奖公告信息)<br/>
		<input type="hidden" name="id" value="${id0 }"/>
		<input type="submit" value="我要定制"/><br/>
		</form>
		</c:if>
	</label>
	<form action="/userWap/cancelUserSetting.jspa" method="post">
	<label >
		<c:if test="${sendtype1=='1' }">
		<input type="checkbox" name="needToSend" value="" onclick="return false;" checked/>邮箱提醒<br/>
		(已定制)<br/>
		<input type="hidden" name="id" value="${id1 }"/>
		<input type="submit" value="取消设置"/><br/>
		</c:if>
		<c:if test="${sendtype1=='0' }">
		<input type="checkbox" name="needToSend" value="" onclick="return false;"/>邮箱提醒<br/>
		(未定制，定制后你可以通过邮箱收到购买彩种当前的开奖公告信息)<br/>
		</c:if>
	</label>
	</form>
<a href="/w/wap/userinfo/userCenter.jspx">返回上一页</a>
</body>
