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
    int contentstate = request.getAttribute("contentstate")==null? 1 : (Integer)request.getAttribute("contentstate");
    int state = request.getAttribute("state")==null? 1 : (Integer)request.getAttribute("state");
%>  
<title>新闻评论</title>
<body>
<a href="/wap/wapindex.jspx">首页</a>-新闻评论审核<br/><br/>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx")%>">恢复默认</a><br/>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx?contentstate=0")%>">已通过</a>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx?contentstate=1")%>">未通过</a><br/>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx?state=0")%>">已删除</a>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx?state=1")%>">未删除</a><br/>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx?contentstate=0&state=0")%>">已删除已通过</a>
	<a href="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx?contentstate=1&state=1")%>">未删除已通过</a><br/>
	
<% if(commentaries!=null){%>
<%for(int i = 0 ; i < commentaries.size();i ++){
	commentary = (Commentary)commentaries.get(i);
	String user = commentary.getMobileid();
	if(user ==null || "".equals(user)){
		user = "****";
	}
%>
<%=(i+1) %>.用户<a style = "color:#DE0201"><%=user %></a>
评论说：<br/>
  <%=commentary.getContent()==null ? "" : commentary.getContent()%>
<br/>
<%if("1".equals(commentary.getContentstate()+"")){
	%>
	
	<a href="<%=response.encodeURL(path+"/newsAction/updateNewsCommentary.jspx?contentstate=0&id="+commentary.getId())%>">通过</a>
	<% 
}else{
%>
	<a href="<%=response.encodeURL(path+"/newsAction/updateNewsCommentary.jspx?contentstate=1&id="+commentary.getId())%>">不通过</a>
<%} %>
<%if("0".equals(commentary.getState()+"")){
	%>
	
	<a href="<%=response.encodeURL(path+"/newsAction/deleteNewsCommentary.jspx?state=1&id="+commentary.getId())%>">恢复</a>
	<% 
}else{
%>
	<a href="<%=response.encodeURL(path+"/newsAction/deleteNewsCommentary.jspx?state=0&id="+commentary.getId())%>">删除</a>
<%} %>
<br/>
<%} %>
<br/>
<%
if (nowPage-1>=0) {
 %> 
 <form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx")%>" method="get">
 	<input type="hidden" name="startPage" value="0"/>
 	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
 	<input type="submit" value="首页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx")%>" method="get">
 	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
	<input type="hidden" name="startPage" value="<%=nowPage-lineNumber %>"/>
 	<input type="submit" value="上一页"/>
 </form>
 <br />
<%
}
if (nowPage+1<maxPage) {
%>
<form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx")%>" method="get">
	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
	<input type="hidden" name="startPage" value="<%=nowPage+lineNumber %>"/>
 	<input type="submit"  value="下一页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/newsAction/selectNewsCommentary.jspx")%>" method="get">
 	<input type="hidden" name="contentstate" value="<%=contentstate%>"/>
	<input type="hidden" name="state" value="<%=state%>"/>
	<input type="hidden" name="startPage" value="<%=(maxPage-1)*10 %>"/>
 	<input type="submit" value="尾页"/>
 </form>
<%
	}
}else{
	out.print("暂无用户评论");
}
%> 

<br/>
	</body>

