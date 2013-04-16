<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>彩票走势</title>
<body>
<a href="/w/wap/wapindex.jspx">首页</a>-<a href="/w/wap/trendChart/trendChart.jspx">彩票走势</a>-<a href="/w/wap/trendChart/trendChartSSQ.jspx">双色球</a><br />
<c:if test="${param.type eq 'R1'}">
【双色球红球一区号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/ssqRed1.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R2'}">
【双色球红球二区号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/ssqRed2.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'R3'}">
【双色球红球三区号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/ssqRed3.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'B1'}">
【双色球蓝球一区号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/ssqBlue1.jpeg"></img>
</c:if>
<c:if test="${param.type eq 'B2'}">
【双色球蓝球二区号码走势】<br/>
	<img alt="" src="/w/wap/trendChartImage/ssqBlue2.jpeg"></img>
</c:if>
<br/><a href="/w/wap/wapindex.jspx">返回首页</a>
</body>
