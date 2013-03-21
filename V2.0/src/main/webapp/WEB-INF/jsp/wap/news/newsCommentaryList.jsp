<%@page import="com.ruyicai.wap.bean.Commentary"%>
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
    List<Commentary> commentaries = request.getAttribute("commentaries")==null?new ArrayList():(List)request.getAttribute("commentaries");
    Commentary commentary = new Commentary();
    int nowPage = request.getAttribute("startPage") == null ? 0 :(Integer)request.getAttribute("startPage");
    int lineNumber = request.getAttribute("lineNumber") == null ? 10 :(Integer)request.getAttribute("lineNumber");
    int maxPage = request.getAttribute("MaxLine")==null? 0 : (Integer)request.getAttribute("MaxLine");
    int contentstate = request.getAttribute("contentstate")==null? 0 : (Integer)request.getAttribute("contentstate");
    int state = request.getAttribute("state")==null? 1 : (Integer)request.getAttribute("state");
    String newsId = request.getAttribute("newsId")==null? "" : (String)request.getAttribute("newsId");
%>  
<title>新闻评论</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-新闻评论<br/><br/>	
<% if(commentaries!=null){
for(int i =0;i<commentaries.size();i++){
		commentary = commentaries.get(i);
		%>
		<%=i+1 %>.<%=commentary.getCreatetime().substring(0,19)%>  用户<%=commentary.getMobileid().substring(7) %> <br/>
		<%=commentary.getContent() %><br/>
	<%}%>
<%
if (nowPage-1>=0) {
 %> 
 <form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentaryList.jspx")%>" method="get">
 	<input type="hidden" name="startPage" value="0"/>
 	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
	<input type="hidden" name="newsId" value="<%=newsId%>"/>
 	<input type="submit" value="首页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentaryList.jspx")%>" method="get">
 	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
	<input type="hidden" name="startPage" value="<%=nowPage-lineNumber %>"/>
		<input type="hidden" name="newsId" value="<%=newsId%>"/>
	
 	<input type="submit" value="上一页"/>
 </form>
 <br />
<%
}
if (nowPage+1<maxPage) {
%>
<form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentaryList.jspx")%>" method="get">
	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
	<input type="hidden" name="startPage" value="<%=nowPage+lineNumber %>"/>
		<input type="hidden" name="newsId" value="<%=newsId%>"/>
	
 	<input type="submit"  value="下一页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentaryList.jspx")%>" method="get">
 	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
	<input type="hidden" name="startPage" value="<%=(maxPage-1)*10 %>"/>
	<input type="hidden" name="newsId" value="<%=newsId%>"/>
 	<input type="submit" value="尾页"/>
 </form>
<%
	}
}else{
	out.print("暂无用户评论");
}
%> 
<br/>
<a href="<%=response.encodeURL(path+"/wap/wapindex.jspx")%>">返回首页</a>
<br/>
	</body>

