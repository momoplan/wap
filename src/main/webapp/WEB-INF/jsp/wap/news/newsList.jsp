<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%
	String CHANNEL = com.ruyicai.wap.util.WapUtil.getChannelId(request);
	String path = CommonUtil.removeTrailingSlash(request
	.getContextPath());
%>
<%
	String method = request.getParameter("method") == null ? "getNewsList"
			: (String) request.getParameter("method");
	List newsList = request.getAttribute("newsList") == null ? new ArrayList()
			: (List) request.getAttribute("newsList");
	News nb = new News();
	int nowPage = request.getAttribute("startPage") == null ? 0
			: (Integer) request.getAttribute("startPage");
	int maxPage = request.getAttribute("MaxLine") == null ? 0
			: (Integer) request.getAttribute("MaxLine");
	String type = (String) request.getAttribute("ListType");
	if (type.equals("数据分析")) {
		type = "专家在线  福彩3D";
	} else if (type.equals("专家荐号")) {
		type = "专家在线  双色球";
	}
%>
<title>最新中奖</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<%=type%><br />
<%
	for (int i = 0; i < newsList.size(); i++) {
		nb = (News) newsList.get(i);
%> <%=i + 1%>. <%
 	if (type.equals("最新中奖")) {
 %> <%=nb.getTitle()%> <%
 	} else {
 %>
<a
	href="<%=response.encodeURL(path + "/newsAction/getNewsContent.jspx?newsId=" + nb.getId()
							+ "&amp;id=" + nb.getId())%>"><%=nb.getTitle()%></a>
<%
	}
%>
<br />
<%
	}
%> <%
String act = "/newsAction/"+method+".jspx";
 	if (nowPage - 1 >= 0) {
 %>
<form action="<%=act %>" method="get">
<input type="hidden" name="nId" value="<%=nb.getId()%>" />
<input type="hidden" name="startPage" value="0" />
<input type="submit" value="首页" />
</form>
<form action="<%=act %>" method="get">
<input type="hidden" name="nId" value="<%=nb.getId()%>" />
<input type="hidden" name="startPage" value="<%=nowPage - 1%>" />
<input type="submit" value="上一页" />
</form><br />
<%
	}
	if (nowPage + 1 < maxPage) {
%> 
<form action="<%=act %>" method="get">
<input type="hidden" name="nId" value="<%=nb.getId()%>" />
<input type="hidden" name="startPage" value="<%=nowPage + 1%>"/>
<input type="submit" value="下一页"/>
</form>
<form action="<%=act %>" method="get">
<input type="hidden" name="nId" value="<%=nb.getId()%>"/>
<input type="hidden" name="startPage" value="<%=maxPage - 1%>"/>
<input type="submit" value="尾页"/>
</form><br/>
<%
	}
%>
<br/>
<a href="<%=response.encodeURL(path + "/wap/wapindex.jspx")%>">返回首页</a>
</body>
