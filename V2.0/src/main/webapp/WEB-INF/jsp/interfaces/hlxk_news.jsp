<%@page import="com.ruyicai.wap.Global"%>
<%@page import="com.ruyicai.wap.controller.*"%>
<%@page import="com.ruyicai.wap.bean.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html;charset=utf-8"%>
<%
List<WapNews> wapnews = new ArrayList<WapNews>();
wapnews = new NewsController().selectWapNewsList("1",0,5);
String url = "";

out.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
out.println("<Response>\n");
out.println("\t<Platform id=\"00001\">\n");
out.println("\t\t<Type value=\"sports\">\n");
for (int i = 0; i < wapnews.size(); i++) {
	WapNews news = wapnews.get(i);
	url = "http://hlxk.ruyicai.com/w/news/wapNews.jspx;jsessionid=E6E4B95C04CE9F2009802F54AA924AD5?id="
			+ news.getId() + "&type=" + news.getVol_typeid_fk() + "&cn=521";
	String str = "000"+(i+1);
	out.println("\t\t\t<Info id=\""+str+"\">\n");
	out.println("\t\t\t\t<Title>"+news.getVol_title()+"</Title>\n");
	out.println("\t\t\t\t<URL>"+url+"</URL>\n");
	out.println("\t\t\t</Info>\n");
}
out.println("\t\t</Type>\n");
out.println("\t</Platform>\n");
out.println("</Response>\n");
%>