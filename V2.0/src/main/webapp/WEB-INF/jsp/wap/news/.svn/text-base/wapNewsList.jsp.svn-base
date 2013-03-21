<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>新闻资讯</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-新闻资讯<br />
<c:choose>
	<c:when test="${typeName =='彩民趣闻' }">
		彩民趣闻|<a href="/news/wapNewsList.jspx?type=2">专家推荐</a>|<a href="/news/wapNewsList.jspx?type=3">足彩天地</a>|<a href="/news/wapNewsList.jspx?type=4">如意活动</a><br/>
		<c:if test="${not empty wapNews }">
			<c:forEach items="${wapNews}" var="news">
				·<a href="/news/wapNews.jspx?id=${news.id }&type=${news.vol_typeid_fk}">${news.vol_title }</a><br/>
			</c:forEach>
		</c:if>
	</c:when>
	<c:when test="${typeName =='专家推荐' }">
		<a href="/news/wapNewsList.jspx?type=1">彩民趣闻</a>|专家推荐|<a href="/news/wapNewsList.jspx?type=3">足彩天地</a>|<a href="/news/wapNewsList.jspx?type=4">如意活动</a><br/>
		<c:if test="${not empty wapNews }">
			<c:forEach items="${wapNews}" var="news">
				·<a href="/news/wapNews.jspx?id=${news.id }&type=${news.vol_typeid_fk}">${news.vol_title }</a><br/>
			</c:forEach>
		</c:if>
	</c:when>
	<c:when test="${typeName =='足彩天地' }">
		<a href="/news/wapNewsList.jspx?type=1">彩民趣闻</a>|<a href="/news/wapNewsList.jspx?type=2">专家推荐</a>|足彩天地|<a href="/news/wapNewsList.jspx?type=4">如意活动</a><br/>
		<c:if test="${not empty wapNews }">
			<c:forEach items="${wapNews}" var="news">
				·<a href="/news/wapNews.jspx?id=${news.id }&type=${news.vol_typeid_fk}">${news.vol_title }</a><br/>
			</c:forEach>
		</c:if>
	</c:when>
	<c:when test="${typeName =='如意活动' }">
		<a href="/news/wapNewsList.jspx?type=1">彩民趣闻</a>|<a href="/news/wapNewsList.jspx?type=2">专家推荐</a>|<a href="/news/wapNewsList.jspx?type=3">足彩天地</a>|如意活动<br/>
		<c:if test="${not empty wapNews }">
			<c:forEach items="${wapNews}" var="news">
				·<a href="/news/wapNews.jspx?id=${news.id }&type=${news.vol_typeid_fk}">${news.vol_title }</a><br/>
			</c:forEach>
		</c:if>
	</c:when>
</c:choose>
<c:if test="${upFlag }">
<a href="/news/wapNewsList.jspx?type=${type }&nowPage=1&startLine=0&endLine=${maxLine}">首页</a> <a 
   href="/news/wapNewsList.jspx?type=${type }&nowPage=${nowPage-1 }&startLine=${startLine-maxLine }&endLine=${maxLine}">上一页</a>
</c:if>
<c:if test="${nextFlag }">
<a href="/news/wapNewsList.jspx?type=${type }&nowPage=${nowPage+1 }&startLine=${startLine+maxLine }&endLine=${maxLine}">下一页</a> <a 
   href="/news/wapNewsList.jspx?type=${type }&nowPage=${totalPage }&startLine=${(totalPage-1)*maxLine }&endLine=${maxLine}">尾页</a>
</c:if>
<br/><a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
