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
<title>用户反馈信息回复</title>
<body>

<a href="/wap/wapindex.jspx">首页</a>-<a href="/wap/userinfo/userCenter.jspx">用户中心</a> <%
 	out.print(CommonUtil.printChar());
 %>问题回复<br />
 *尊敬的用户，欢迎您访问彩票频道，下面是针对用户的提问进行的回复，如还有其它疑问可以到<a href="/wap/userinfo/feedBack.jspx">留言页面</a>留言给我们,我们会统一在此页面给大家回复！<br/>
<br/>
<% if(feedBacks!=null){%>
<%for(int i = 0 ; i < feedBacks.size();i ++){
	feedBack = (FeedBack)feedBacks.get(i);
	if(feedBack.getAnswer()==null){
		continue;
	}
	String user = feedBack.getMobile();
	if(user ==null || "".equals(user)){
		user = "*****";
	}else{
		user = user.substring(0,7)+"****";
	}
%><ul>
 <li>
*用户<a style = "color:#DE0201"><%=user %></a>
留言说：<br/>
  <%=feedBack.getContent()==null ? "" : feedBack.getContent()%>
<br/><br/>
<%if(feedBack.getAnswer()==null){
	%>
	<% 
}else{
%>客服解答：<a style = "color:#DE0201"><%=feedBack.getAnswer() %></a><br/>
<%} %>
<br/><br/>
 <li/><ul/>
<%} %>
<br/>
<%
if (nowPage>0) {
 %> 
 <form action="<%=response.encodeURL(path+"/userWap/selectFeedBackList.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="0"/>
 	<input type="submit" value="首页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/userWap/selectFeedBackList.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="<%=nowPage-lineNumber %>"/>
 	<input type="submit" value="上一页"/>
 </form>
 <br />
<%
}
if (nowPage/10+1<maxPage) {
%>
<form action="<%=response.encodeURL(path+"/userWap/selectFeedBackList.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="<%=nowPage+lineNumber %>"/>
 	<input type="submit"  value="下一页"/>
 </form>
 <form action="<%=response.encodeURL(path+"/userWap/selectFeedBackList.jspx")%>" method="get">
	<input type="hidden" name="startPage" value="<%=(maxPage-1)*10 %>"/>
 	<input type="submit" value="尾页"/>
 </form>
<%
	}
}else{
	out.print("暂无用户留言");
}
%> <br/>
<a href="/wap/userinfo/feedBack.jspx">返回上一页</a>
<br/>
	</body>

