<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>彩票走势</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/trendChart/trendChart.jspx">彩票走势</a>-<a href="/w/wap/trendChart/trendChartQXC.jspx">七星彩</a><br />
<c:if test="${param.type eq 'R1'}">
【七星彩第一位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/qxcRed1.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R2'}">
【七星彩第二位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/qxcRed2.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R3'}">
【七星彩第三位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/qxcRed3.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R4'}">
【七星彩第四位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/qxcRed4.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R5'}">
【七星彩第五位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/qxcRed5.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R6'}">
【七星彩第六位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/qxcRed6.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R7'}">
【七星彩第七位号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/qxcRed7.jpeg"></img>
</c:if>

<br/><a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
