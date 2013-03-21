<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ruyicai.wap.bean.Commentary"%>
<%@page import="com.ruyicai.wap.Global"%>
<%@page import="com.ruyicai.wap.util.WapUtil"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
	String channel = WapUtil.getChannelId(request);
%>
<%
	News nb = (request.getAttribute("nb") == null ? new News()
			: (News) request.getAttribute("nb"));
	List newsNextList = (List)request.getAttribute("newsNextList");
	String type = nb.getTitle().substring(0,3);
	String newType="";
	int start = Integer.parseInt(request.getParameter("start"));
	int count = 0;
	if (type.equals("双色球")||type.equals("福彩3D")||type.equals("七乐彩")) {
		newType = "福彩新闻";
	}else if("大乐透".equals(type)||"排列三".equals(type)||"排列五".equals(type)||"七星彩".equals(type)){
		newType = "体彩新闻";
	}else if("胜负彩".equals(type)){
		newType = "足彩新闻";
	}else if("福彩3D".equals(nb.getTitle().substring(0,4))){
		newType = "福彩新闻";
	}else if("公告".equals(nb.getTitle().substring(0,2))||"公告".equals(nb.getTitle().substring(1,3))){
		newType = "公告信息";
	}else{
		newType = "彩民故事";
	}
%>
<title><%= newType%></title>
	<%
		
	%>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<%=newType%><br/><br/>
		<%=nb.getTitle()%><br/>
		[<%=nb.getPubDate().substring(0, 10)%>]<br/>
		<%=nb.getContent()%>
		<br/>
<%
if(newsNextList!=null){
	for(int i = 0 ; i < newsNextList.size();i ++){
		nb = (News)newsNextList.get(i);
		%>
		<a href="<%=response.encodeURL(path+"/newsAction/getNewsContentById.jspx?categoryId="+nb.getCategoryId()+"&amp;id="+nb.getId())+"&amp;start="+(start+i+1)%>"><%=nb.getTitle() %></a>
		<br/>
		<%} %>
<%} %><br/>
<%if("521".equals(channel)){
String categoryId = request.getParameter("categoryId")==null ? "":(String)request.getParameter("categoryId");
if("".equals(categoryId)){%>
	分享至<a href="<%=response.encodeURL(path+"/wap/news/newsShare.jspx"+"?content="+nb.getTitle()+"&amp;id="+nb.getId()+"&amp;count="+nb.getShareCount()+"&amp;newsId="+nb.getId())%>"><img alt="新浪微博" src="<%=response.encodeURL(path + "/wap/images/sina.png")%>"></a>(分享<%=nb.getShareCount() %>次)
<%}else{
	String start1 = (String)request.getParameter("start");%>
	分享至<a href="<%=response.encodeURL(path+"/wap/news/newsShare.jspx"+"?content="+nb.getTitle()+"&amp;id="+nb.getId()+"&amp;count="+nb.getShareCount()+"&amp;categoryId="+nb.getCategoryId()+"&amp;start="+start1)%>"><img alt="新浪微博" src="<%=response.encodeURL(path + "/wap/images/sina.png")%>"></a>(分享<%=nb.getShareCount() %>次)
	
<% }
%>
<%--<%
	int total = Global.newsDAO.selectNewsCommentaryCount(Integer.valueOf(nb.getId()),1,0);
	int totall = Global.newsDAO.selectNewsCommentaryCountBynewsId(Integer.valueOf(nb.getId()),1,0);
%>
<br/>参与评论<%=total %>人,共<%=totall %>次<br/>
<%
Commentary commentary = null;
List<Commentary> commentaries = Global.newsDAO.selectNewsCommentary(Integer.valueOf(nb.getId()),1,0,0,5);

//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
if(commentaries !=null){
	for(int i =0;i<commentaries.size();i++){
		commentary = commentaries.get(i);
		%>
		<%=i+1 %>.<%=commentary.getCreatetime().substring(0,19)%>  手机尾号<%=commentary.getMobileid().substring(7) %> <br/>
		<%=commentary.getContent() %><br/>
	<%}%>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentaryList.jspx?newsId="+commentary.getNewsid())%>">更多...</a><br/>
		
<%
}
%>
  <form action="<%=response.encodeURL(path + "/newsAction/insertNewsCommentary.jspa" )%>" method="post">
	<input name="content" maxlength="500" value =""/>
	<input type="hidden" name="newsid" value="<%=nb.getId()%>"/>
 	<input	type="submit" value="评论" />
 </form> <br/>--%>
 <br/>
<h1>【频道推荐】</h1>
<%
	List<News> newsList = Global.newsDAO.getNewsByChannelId(channel,"51", 0, 4);
 	if (newsList != null && newsList.size() > 0) {
 	
 		for (int i = 0; i < newsList.size() && i < 4; i++) {
 			nb = (News) newsList.get(i);
 			if (nb.getId() != "" && nb.getId() != null
 					&& !"".equals(nb.getId())) {
 %><a
	href="/newsAction/getNewsContent.jspx?newsId=<%=nb.getId()%>&amp;id=<%=nb.getId()%>"><%=nb.getTitle()%></a><br />
<%
	}
		}
	}
} %>
<a href="<%=response.encodeURL(path + "/wap/wapindex.jspx")%>">返回首页</a>
	</body>
