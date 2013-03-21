<%@page import="com.ruyicai.wap.bean.FeedBack"%>
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
    List<FeedBack> feedBacks = request.getAttribute("feedBacks")==null?new ArrayList():(List)request.getAttribute("feedBacks");
    FeedBack feedBack = new FeedBack();
    int nowPage = request.getAttribute("startPage") == null ? 0 :(Integer)request.getAttribute("startPage");
    int lineNumber = request.getAttribute("lineNumber") == null ? 10 :(Integer)request.getAttribute("lineNumber");
    int maxPage = request.getAttribute("MaxLine")==null? 0 : (Integer)request.getAttribute("MaxLine");

%>  
<title>用户反馈信息</title>
<body>

<a href="/wap/wapindex.jspx">首页</a>-用户留言<br/><br/>
<% if(feedBacks!=null){%>
<%for(int i = 0 ; i < feedBacks.size();i ++){
	feedBack = (FeedBack)feedBacks.get(i);
	String user = feedBack.getMobile();
	if(user ==null || "".equals(user)){
		user = "****";
	}
%>
*用户<a style = "color:#DE0201"><%=user %></a>
留言说：<br/>
  <%=feedBack.getContent()==null ? "" : feedBack.getContent()%>
<br/>
留言日期：<%=feedBack.getCreateTime().substring(0,10) %>
<br/>
<%if(feedBack.getAnswer()==null){
	%>
	<a href="<%=response.encodeURL(path+"/userWap/selectAnswerFeedBackById.jspx?type=HF&id="+feedBack.getId())%>">回复</a>
	
	<% 
}else{
%>客服解答：<%=feedBack.getAnswer() %><br/>
	<a href="<%=response.encodeURL(path+"/userWap/selectAnswerFeedBackById.jspx?type=XG&id="+feedBack.getId())%>">修改</a>
<%} %>
<br/><br/>
<%} %>
<br/>
<%
if (nowPage>0) {
 %> 
 <form action="<%=response.encodeURL(path+"/userWap/selectfeedBack.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="0"/>
 	<input type="submit" value="首页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/userWap/selectfeedBack.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="<%=nowPage-lineNumber %>"/>
 	<input type="submit" value="上一页"/>
 </form>
 <br />
<%
}
if (nowPage/10+1<maxPage) {
%>
<form action="<%=response.encodeURL(path+"/userWap/selectfeedBack.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="<%=nowPage+lineNumber %>"/>
 	<input type="submit"  value="下一页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/userWap/selectfeedBack.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="<%=(maxPage-1)*10 %>"/>
 	<input type="submit" value="尾页"/>
 </form>
<%
	}
}else{
	out.print("暂无用户留言");
}
%> 

<br/>
	</body>

