<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ruyicai.wap.util.CommonUtil"%>

<%
	String path = CommonUtil.removeTrailingSlash(request
			.getContextPath());
%>
<title>管理页面</title>

<body>
<a href='wap/wapindex.jsp'>首页</a><br/>
<a href="<%=response.encodeURL(path + "/userWap.do?method=selectfeedBack")%>">互联星空留言回复</a><br/>
<a href="<%=response.encodeURL(path + "/userWap.do?method=selectFeedBackList")%>">互联星空留言回复详情</a><br/>
<a href="<%=response.encodeURL(path + "/newsAction.do?method=selectNewsCommentary")%>">互联星空新闻评论审核</a><br/><br/>
<a href="<%=response.encodeURL(path + "/wap/wapindex.jsp")%>">返回首页</a>
	</body>
