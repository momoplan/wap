<%@page import="com.ruyicai.wap.Global"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.URLEncoder,com.ruyicai.wap.util.CommonUtil,java.util.*"%>
<%@ page import="com.ruyicai.wap.util.*"%>

<title>微博分享</title>
<body>
<!-- 互联星空新闻微博分享 -->
<%
String categoryId = request.getParameter("categoryId")==null ? "" : (String)request.getParameter("categoryId");
String content = request.getParameter("content");
String id = request.getParameter("id");
content = StringUtils.encodeUrlString(content);
String shareUrl ="";
if("".equals(categoryId)||categoryId == null){
	String newsId = request.getParameter("newsId");
	shareUrl = "http://hlxk.ruyicai.com/w/newsAction/getNewsContent.jspx;jsessionid=645B5DD2F790C0DAE51DE637C49A2DAC?newsId="+newsId+"&id="+id+"&cn=521";

}else{
	String start1 = request.getParameter("start");
	shareUrl = "http://hlxk.ruyicai.com/w/newsAction/getNewsContentById.jspx;jsessionid=645B5DD2F790C0DAE51DE637C49A2DAC?categoryId="+categoryId+"&id="+id+"&start="+start1+"&cn=521";

}
System.out.println("shareUrl:"+shareUrl);
shareUrl = StringUtils.encodeUrlString(shareUrl);

String count = request.getParameter("count");
int result = Global.newsDAO.updateShareCount((Integer.parseInt(count)+1)+"",id);
response.sendRedirect("http://t.21cn.com/vnet/v/show.do?share="+content+"&sendUrl="+shareUrl);

%>

</body>
