<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>用户中心-设置</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-设置<br />
<form action="/w/userWap/deleteTuserloginfo.jspa" method="post">
	<label >
		<c:if test="${check=='1' }">
		<input type="checkbox" name="android" value="" onclick="return false;" checked/>客户端自动登录<br/>
		(已设置)<br/>
		<input type="submit" value="取消设置"/><br/>
		</c:if>
		<c:if test="${check=='0' }">
		<input type="checkbox" name="android" value="" onclick="return false;"/>客户端自动登录<br/>
		(未设置)<br/>
		</c:if>
	</label>
</form>
</body>
