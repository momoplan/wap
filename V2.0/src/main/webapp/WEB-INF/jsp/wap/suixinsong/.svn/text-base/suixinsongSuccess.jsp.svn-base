<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<title>赠送信息</title>
<body>
		<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/userinfo/userCenter.jspx">用户中心</a>-
		<a href="/w/wap/suixinsong/suixinsongIndex.jspx">赠送彩票</a>-赠送信息<br/>
			${messageError }<br/>
				彩种:${lotType }<br/>
				期号:${term }<br/>
				金额:${amount }<br/>
				<c:if test="${not empty successMobileId }">
				赠送成功的手机号：<br/>
				${successMobileId }<br/>
				</c:if>
				<c:if test="${not empty failMobileId }">
				赠送失败的手机号：
				${failMobileId }<br/>
				</c:if>
		【<a href="/w/wap/suixinsong/selectGift.jspx">赠送查询</a>】<br/><br/>		
		</body>		