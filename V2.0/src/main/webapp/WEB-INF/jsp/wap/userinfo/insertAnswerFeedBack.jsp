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
<%	
    FeedBack feedBack = request.getAttribute("feedBack")==null?new FeedBack():(FeedBack)request.getAttribute("feedBack");
    String user = feedBack.getMobile();
	if(user ==null || "".equals(user)){
		user = "****";
	}
	String message = (String) request.getAttribute("message");
%>  
<title>用户反馈回复</title>
<body>

<a href="/w/wap/wapindex.jspx">首页</a>-用户反馈回复<br/>
<%
	if (message != null) {
%> <a style="color: red"><%
 	out.print(CommonUtil.printWarningMessage(message)+"<br/>");
 %></a> <%
 	}
 %>
<br/>
<% if(feedBack!=null){%>
	*用户<a style = "color:#DE0201"><%=user %></a>
留言说：<br/>
  <%=feedBack.getContent()==null ? "" : feedBack.getContent()%>
<br/><br/>
<%if(feedBack.getAnswer()==null){%>
	<form action="<%=response.encodeURL(path + "/userWap/insertAnswerFeedBack.jspx" )%>" method="post">
	回复:<br/>
	<textarea name="answer" rows="3" cols="20"></textarea><br/>
	<input type="hidden" name="id" value="<%=feedBack.getId() %>" />
 	<input	type="submit" value="提交" />
 </form> <br/>
<%}else{%>
	答：<%=feedBack.getAnswer() %><br/><a href="<%=response.encodeURL(path+"/userWap/selectAnswerFeedBackById.jspx?type=XG&id="+feedBack.getId())%>">修改</a>
<%} %>

<%}else{
	out.print("用户留言不存在");
}
%> 

<br/>
<br/>
<a href="<%=response.encodeURL(path+"/userWap/selectfeedBack.jspx")%>">返回留言列表</a>

	</body>

