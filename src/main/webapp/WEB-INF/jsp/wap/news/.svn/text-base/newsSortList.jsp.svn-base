<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ruyicai.wap.bean.News"%>
<title>专家荐号</title>
<%
	String CHANNEL=com.ruyicai.wap.util.WapUtil.getChannelId(request);
	String path = CommonUtil.removeTrailingSlash(request
	.getContextPath());
 
	News nb;
	//取得新闻内容
	int start = 0;
	List shuangseqiu = (List) request.getAttribute("shuangseqiu");
	List fucai3d = (List) request.getAttribute("fucai3d");
	List daletou = (List) request.getAttribute("daletou");
	List pailiesan = (List) request.getAttribute("pailiesan");
	List qixingcai = (List) request.getAttribute("qixingcai");
	List qilecai = (List) request.getAttribute("qilecai");
	List shengfucai = (List) request.getAttribute("shengfucai");
	List pailiewu = (List) request.getAttribute("pailiewu");
	if (shuangseqiu == null && fucai3d == null && daletou == null
	&& pailiesan == null && qixingcai == null
	&& qilecai == null && shengfucai == null) {
		shuangseqiu.equals("");
	}
%>
<body>
<a href="/wap/wapindex.jspx">首页</a>-专家荐号<br />
<br />
<!-- 彩票新闻信息 --> <%
 	if (shuangseqiu != null && shuangseqiu.size() != 0) {
 %> 【双色球】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=38")%>">更多</a><br />
<%
	for (int i = 0; shuangseqiu != null && i < shuangseqiu.size()
				&& i < 3; i++) {
			nb = (News) shuangseqiu.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <%
 	if (fucai3d != null && fucai3d.size() != 0) {
 %> 【福彩3D】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=39")%>">更多</a><br />
<%
	for (int i = 0; fucai3d != null && i < fucai3d.size() && i < 3; i++) {
			nb = (News) fucai3d.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <%
 	if (daletou != null && daletou.size() != 0) {
 %> 【大乐透】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=40")%>">更多</a><br />
<%
	for (int i = 0; daletou != null && i < daletou.size() && i < 3; i++) {
			nb = (News) daletou.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <%
 	if (pailiesan != null && pailiesan.size() != 0) {
 %> 【排列三】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=41")%>">更多</a><br />
<%
	for (int i = 0; pailiesan != null && i < pailiesan.size()
				&& i < 3; i++) {
			nb = (News) pailiesan.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <%
 	if (pailiewu != null && pailiewu.size() != 0) {
 %> 【排列五】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=45")%>">更多</a><br />
<%
	for (int i = 0; pailiewu != null && i < pailiewu.size()
				&& i < 3; i++) {
			nb = (News) pailiewu.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <%
 	if (qixingcai != null && qixingcai.size() != 0) {
 %> 【七星彩】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=42")%>">更多</a><br />
<%
	for (int i = 0; qixingcai != null && i < qixingcai.size()
				&& i < 3; i++) {
			nb = (News) qixingcai.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <%
 	if (qilecai != null && qilecai.size() != 0) {
 %> 【七乐彩】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=43")%>">更多</a><br />
<%
	for (int i = 0; qilecai != null && i < qilecai.size() && i < 3; i++) {
			nb = (News) qilecai.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <%
 	if (shengfucai != null && shengfucai.size() != 0) {
 %> 【胜负彩】<a
	href="<%=response
										.encodeURL(path
												+ "/newsAction/getNewsInfo.jspx?flag=page&amp;categoryId=44")%>">更多</a><br />
<%
	for (int i = 0; shengfucai != null && i < shengfucai.size()
				&& i < 3; i++) {
			nb = (News) shengfucai.get(i);
%> <a
	href="<%=path%>/newsAction/getNewsContentById.jspx?categoryId=<%=nb.getCategoryId()%>&amp;id=<%=nb.getId()%>&amp;start=<%=i + 1%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %>
<br/>
<a href="<%=response.encodeURL(path + "/wap/wapindex.jspx")%>">返回首页</a>	
</body>

