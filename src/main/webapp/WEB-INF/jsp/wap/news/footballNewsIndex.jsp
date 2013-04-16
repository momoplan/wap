<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ruyicai.wap.controller.*"%>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	News nb;
	Map map = new NewsActionWap().getNewsTypeAndContentListToIndexInMap(request);
	List ZiXunXinWen = (List) map.get("ZiXunXinWen");
	List TCXinWen = (List) map.get("TCXinWen");
	List FuCaiXinWen = (List) map.get("FuCaiXinWen");
	List TouTiaoXinWen = (List) map.get("TouTiaoXinWen");
	List ZhongJiang = (List) map.get("ZhongJiang");
	List ZuCaiZiXun = (List) map.get("ZuCaiZiXun");
	List ZhuanJiaDuanxin = (List) map.get("ZhuanJiaDuanxin");
	List ZhuanJiaHeMai = (List) map.get("ZhuanJiaHeMai");
	List JieRi = (List) map.get("JieRi");
	List ZuCaiFenXi = (List) map.get("ZuCaiFenXi");
	List ZuCaiPeiLv = (List) map.get("ZuCaiPeiLv");
%>
<title>足彩新闻</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-足球新闻<br />
<%
	if ((ZuCaiZiXun != null && ZuCaiZiXun.size() > 0)
			|| (ZuCaiPeiLv != null && ZuCaiPeiLv.size() > 0)
			|| (ZuCaiFenXi != null && ZuCaiFenXi.size() > 0)) {
%>【足彩资讯】<a
	href="<%=response.encodeURL(path + "/newsAction/getNewsList.jspx?nId=ZuCaiZiXun")%>">更多>></a><br />
<%
	for (int i = 0; ZuCaiZiXun != null && i < ZuCaiZiXun.size()
				&& i < 3; i++) {
			nb = (News) ZuCaiZiXun.get(i);
%><a
	href="/newsAction/getNewsContent.jspx?newsId=<%=nb.getId()%>&amp;id=<%=nb.getId()%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <br />
【足彩赔率】<a
	href="<%=response.encodeURL(path + "/newsAction/getNewsList.jspx?nId=ZuCaiPeiLv")%>">更多>></a><br />
<%
	for (int i = 0; ZuCaiPeiLv != null && i < ZuCaiPeiLv.size()
				&& i < 3; i++) {
			nb = (News) ZuCaiPeiLv.get(i);
%><a
	href="/newsAction/getNewsContent.jspx?newsId=<%=nb.getId()%>&amp;id=<%=nb.getId()%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <br />【足彩分析】<a
	href="<%=response.encodeURL(path + "/newsAction/getNewsList.jspx?nId=ZuCaiFenXi")%>">更多>></a><br />
<%
	for (int i = 0; ZuCaiFenXi != null && i < ZuCaiFenXi.size()
				&& i < 3; i++) {
			nb = (News) ZuCaiFenXi.get(i);
%><a
	href="/newsAction/getNewsContent.jspx?newsId=<%=nb.getId()%>&amp;id=<%=nb.getId()%>"><%=nb.getTitle()%></a><br />
<%
	}
%> <%
 	}
 %> <br/>
 <a href="<%=response.encodeURL(path + "/wap/wapindex.jspx")%>">返回首页</a>
</body>
