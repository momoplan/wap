<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ruyicai.wap.bean.News"%>
<%
	String CHANNEL=com.ruyicai.wap.util.WapUtil.getChannelId(request);
    String path = CommonUtil.removeTrailingSlash(request.getContextPath());
	//渠道统计
%>
<%	int start = 0;	
	String method = request.getParameter("method")==null?"getNewsByCaiZhong":(String)request.getParameter("method");
    List newsList = request.getAttribute("newsList")==null?new ArrayList():(List)request.getAttribute("newsList");
    News nb = new News();
    int nowPage = request.getAttribute("startPage") == null ? 0 :(Integer)request.getAttribute("startPage");
    int lineNumber = request.getAttribute("lineNumber") == null ? 20 :(Integer)request.getAttribute("lineNumber");
    int maxPage = request.getAttribute("MaxLine")==null? 0 : (Integer)request.getAttribute("MaxLine");
    String type=(String)request.getAttribute("ListType");
    String flag = (String)request.getAttribute("flag");	  
    String pageTitle=(String)request.getAttribute("pageTitle");
    if(type.equals("")){
    	
    }
%>  
<title><%=type %></title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-<% 
if("公告信息".equals(type)){%>
	<%=type %><br/>
<%}else if("彩民故事".equals(type)){%>
<%=type %><br/>
<%}else{
%><a href="<%=path%>/newsAction/getNewsInfo.jspx?flag=index"><%=pageTitle %></a><%out.print(CommonUtil.printChar());%><%=type %><br/><%} %>
<%for(int i = 0 ; i < newsList.size();i ++){
nb = (News)newsList.get(i);
%>
<%=i+1 %>.
<a href="<%=response.encodeURL(path+"/newsAction/getNewsContentById.jspx?categoryId="+nb.getCategoryId()+"&amp;id="+nb.getId()+"&amp;start="+(nowPage+i+1))%>"><%=nb.getTitle() %></a>
<br/>
<%} %>
<br/>
<%
String act = "/newsAction/"+method+".jspx";
if (nowPage-1>=0) {
 %> 
 <form action="<%=response.encodeURL(path+act)%>" method="get">
	<input type="hidden" name="flag" value="<%=flag %>"/>
	<input type="hidden" name="categoryId" value="<%=nb.getCategoryId()%>"/>
	<input type="hidden" name="startPage" value="0"/>
 	<input type="submit" value="首页"/>
 </form>
 <form action="<%=response.encodeURL(path+act)%>" method="get">
	<input type="hidden" name="flag" value="<%=flag %>"/>
	<input type="hidden" name="categoryId" value="<%=nb.getCategoryId()%>"/>
	<input type="hidden" name="startPage" value="<%=nowPage-lineNumber %>"/>
 	<input type="submit" value="上一页"/>
 </form>
 <br />
<%
}
if (nowPage+1<maxPage) {
%>
<form action="<%=response.encodeURL(path+act)%>" method="get">
	<input type="hidden" name="flag" value="<%=flag %>"/>
	<input type="hidden" name="categoryId" value="<%=nb.getCategoryId()%>"/>
	<input type="hidden" name="startPage" value="<%=nowPage+lineNumber %>"/>
 	<input type="submit"  value="下一页"/>
 </form>
 <form action="<%=response.encodeURL(path+act)%>" method="get">
	<input type="hidden" name="flag" value="<%=flag %>"/>
	<input type="hidden" name="categoryId" value="<%=nb.getCategoryId()%>"/>
	<input type="hidden" name="startPage" value="<%=maxPage %>"/>
 	<input type="submit" value="尾页"/>
 </form>
<%
	}
%> 
<br/>
	<a href="<%=response.encodeURL(path + "/wap/wapindex.jspx")%>">返回首页</a>
	</body>

