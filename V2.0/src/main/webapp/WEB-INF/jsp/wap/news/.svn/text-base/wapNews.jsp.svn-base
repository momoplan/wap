<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>新闻资讯</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-
<c:choose>
	<c:when test="${type eq '6' }">
		${typeName }<br />
	</c:when>
	<c:otherwise>
		<a href="/news/wapNewsList.jspx?type=${type }">新闻资讯</a>-${typeName }<br />
	</c:otherwise>
</c:choose>

<c:if test="${not empty news}">
	<b>${news.vol_title }</b><br/>
	${news.updatetime }<br/>
	${news.vol_content }<br/>
</c:if>
<a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
