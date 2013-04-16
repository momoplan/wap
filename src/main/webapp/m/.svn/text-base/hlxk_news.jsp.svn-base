<%@page import="com.ruyicai.wap.Global"%>
<%@page import="com.ruyicai.wap.controller.NewsActionWap"%>
<%@page import="com.ruyicai.wap.bean.News"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=utf-8"%>
<%
List<News> newsList = NewsActionWap.getHLXKNewsList("ZiXunXinWen", 0, 8);

String url = "";

out.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
out.println("<Response>\n");
out.println("\t<Platform id=\"00001\">\n");
out.println("\t\t<Type value=\"sports\">\n");
for (int i = 0; i < newsList.size(); i++) {
	News nb = newsList.get(i);
	url = "http://hlxk.ruyicai.com/w/newsAction/getNewsContent.jspx;jsessionid=E6E4B95C04CE9F2009802F54AA924AD5?newsId="
			+ nb.getId() + "&amp;id=" + nb.getId() + "&amp;cn=521";
	News contentNews = Global.newsDAO.getNewsContent(nb.getId());
	String str = "000"+(i+1);
	out.println("\t\t\t<Info id=\""+str+"\">\n");
	out.println("\t\t\t\t<Title>"+nb.getTitle()+"</Title>\n");
	out.println("\t\t\t\t<URL>"+url+"</URL>\n");
	out.println("\t\t\t</Info>\n");
}
out.println("\t\t</Type>\n");
out.println("\t</Platform>\n");
out.println("</Response>\n");
%>