<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>彩票走势</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/trendChart/trendChart.jspx">彩票走势</a>-<a href="/w/wap/trendChart/trendChart3D.jspx">福彩3D</a><br />
<c:if test="${param.type eq 'R1'}">
【福彩3D百位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/3dRed1.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R2'}">
【福彩3D十位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/3dRed2.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R3'}">
【福彩3D个位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/3dRed3.jpeg"></img>
</c:if>
<br/><a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
