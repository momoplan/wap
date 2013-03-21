<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>用户积分</title>
<body>

<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-我的积分<br/><br/>
${messageError }<br/>
<c:if test="${errorCode eq '0' }">
恭喜您兑换了<a style="color: red"><b>${money }</b></a> 元彩金，剩余<a style="color: red"><b>${score }</b></a>积分
</c:if>
<br/>
	</body>

